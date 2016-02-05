package codacy.pylint

import java.nio.file.{Files, Path, Paths}

import codacy.dockerApi._
import codacy.dockerApi.utils.{FileHelper, CommandRunner, ToolHelper}
import play.api.libs.json._

import scala.collection.immutable.Iterable
import scala.sys.process._
import scala.util.{Properties, Success, Try}

object Pylint extends Tool {

   override def apply(path: Path, conf: Option[List[PatternDef]], files: Option[Set[Path]])(implicit spec: Spec): Try[List[Result]] = {
    val completeConf = ToolHelper.getPatternsToLint(conf)

    def isEnabled(issue: Result) = {
       issue match {
          case Issue(_, _, patternId, _) => completeConf.map(item => item.exists(_.patternId == patternId)).getOrElse(true)
          case _ => true
       }
    }

    def buildFileCommands(files: Map[String, Array[String]]) = {
      files.map { case (key, values) => commandFor(key, path, completeConf, values)}
           .flatMap( item => item.toOption).toList
    }

    def getStdout(command: List[String]): Try[List[String]] = {
      Try {
          CommandRunner.exec(command) match {
            case Right(resultFromTool) =>
              resultFromTool.stdout
            case Left(failure) => {
              throw failure}
          }
      }
    }

    val collectedFiles = collectFiles(files, path)
    val classified = classifyFiles(collectedFiles)
    val commands = classified.map { case item => buildFileCommands(item) }
    val lines_iterable = commands.map { item => item.map(getStdout) }
    val lines = lines_iterable.map {
      case iterable => iterable.flatMap {
          case item => item.toOption
      }.flatten
    }
    lines.map { case line => line.flatMap(parseLine).flatten.filter(isEnabled)}
  }


  private implicit lazy val writer = Json.reads[Issue]
  
  private def parseLine(line: String) = {
     val LineRegex = """(.*?)###(.*?)###(.*?)###(.*?)""".r
     line match {
         case LineRegex(filename, lineNumber, message, patternId) if message.contains("invalid syntax") =>
           val fileError = FileError(SourcePath(filename),
                                     Option(ErrorMessage(message)))
           val issue = Issue(SourcePath(filename),
                              ResultMessage(message),
                              PatternId(patternId),
                              ResultLine(lineNumber.toInt))
           Option(List(fileError, issue))
         case LineRegex(filename, lineNumber, message, patternId) =>
           Option(List(Issue(SourcePath(filename),
                              ResultMessage(message),
                              PatternId(patternId),
                              ResultLine(lineNumber.toInt))))
         case _ =>
            Option.empty
     }
  }

  private val msgTemplate = "{path}###{line}###{msg}###{msg_id}"
  private val classifyScript = s"""
         |import os
         |import sys
         |import ast
         |current = sys.version_info[0]
         |other = 2 if current == 3 else 3
         |def _classify_file(path):
         |    try:
         |        with open(path, 'r') as stream:
         |            try:
         |                ast.parse(stream.read())
         |            except (ValueError, TypeError, UnicodeError):
         |                # Assume it's the current interpreter.
         |                return current
         |            except SyntaxError:
         |                # the other version or an actual syntax error on current interpreter
         |                return other
         |            else:
         |                return current
         |    except Exception:
         |        # Shouldn't happen, but if it does, just assume there's
         |        # something inherently wrong with the file.
         |        return current
         |def classify_file(path):
         |    interpreter = _classify_file(path)
         |    return path + "###" + str(interpreter)
         |def flatten_files(folder):
         |    for path, _, files in os.walk(folder):
         |        for file in files:
         |            if file.endswith(".py"):
         |                yield os.path.join(path, file)
         |def walk_items(items):
         |    for item in items:
         |        if os.path.isfile(item): yield item
         |        elif os.path.isdir(item):
         |            for file in flatten_files(item): yield file
         |def classify(items):
         |    for file in walk_items(items):
         |        print(classify_file(file))
         |items = filter(None, sys.argv[1].split("###"))
         |classify(items)    
       """.stripMargin

  private def collectFiles(files: Option[Set[Path]], path: Path) = {
    files.collect { case files if files.nonEmpty => files.map(_.toString) }.getOrElse {
      //if files is empty, let the classification script to find them.
      List(path.toString)
    }.toList
  }

  def generateClassification(files: List[String]) = {
    val scriptArgs = files.mkString("###")
    val tmp = FileHelper.createTmpFile(classifyScript, "pylint", "")
    List("python", tmp.toAbsolutePath.toString, scriptArgs).!!
  }

  private def classifyFiles(files: List[String]) = {
      Try {
        val output = generateClassification(files)
        val lines = output.split(System.lineSeparator())
        val parsed  = lines.map { case line =>
             val splitted = line.split("###")
             (splitted(0), splitted(1))
        }
        parsed.groupBy { case (path, version) => version}
              .map { case (key, pairs) => (key, pairs map { case (file, version) => file})}
      }
  }

  private def commandFor(interpreter: String, path: Path, conf: Option[List[PatternDef]], files: Array[String])(implicit spec: Spec): Try[List[String]] = {

    val rulesPart = conf.toList.flatMap { conf =>
      val rules = conf.map(_.patternId.toString()).mkString(",")
      List("--disable=all", "-e", rules)
    }

    val configPart = conf.map { case configuration =>
      val confFile = writeConfigFile(configuration)
      confFile.map { case confPath =>
        List(s"--rcfile=$confPath")
      }
    }.getOrElse(Success(List.empty[String]))

    configPart.map { configPart =>
      List("python" + interpreter, "-m", "pylint") ++
          configPart ++ List(s"--msg-template=$msgTemplate") ++
          rulesPart ++ files
    }
  }

  private def writeConfigFile(configuration: List[PatternDef]): Try[Path] = {

    val parameters = configuration.flatMap { case pattern =>
      pattern.parameters.getOrElse(Set.empty).map { case param =>
        ParameterHeader.get(param.name) -> param
      }
    }.groupBy { case (header, _) => header }

    val paramsToPrint = parameters.map { case (header, params) =>

      lazy val paramString: String = params.map { case (_, pvalue) =>
        generateParameter(pvalue)
      }.mkString(Properties.lineSeparator)

      s"""
         |[$header]
         |$paramString
       """.stripMargin

    }.mkString(Properties.lineSeparator)
    write(paramsToPrint)
  }

  private def generateParameter(parameter: ParameterDef): String = {
    val parameterValue = parameter.value match {
      case JsString(value) => value
      case other => Json.stringify(other)
    }
    s"${parameter.name}=$parameterValue"
  }

  private def randomFile(extension: String = "conf") = Try(
    Files.createTempFile("codacy-", s".$extension")
  )

  private def write(content: String): Try[Path] = {
    randomFile().map { case confFile =>
      Files.write(confFile, content.getBytes)
    }
  }
}

case class PyLintParserException(message: String) extends Exception(message)
