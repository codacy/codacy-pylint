package codacy.pylint

import java.io.File
import java.nio.file.{Files, Path}

import com.codacy.plugins.api.{ErrorMessage, Options, Source}
import com.codacy.plugins.api.results.{Parameter, Pattern, Result, Tool}
import com.codacy.tools.scala.seed.utils.ToolHelper._
import com.codacy.tools.scala.seed.utils.{CommandRunner, FileHelper}
import play.api.libs.json._

import scala.sys.process._
import scala.util.{Properties, Success, Try}

object Pylint extends Tool {

  private val pythonVersionKey = Options.Key("python_version")
  private val python3 = "3"

  def apply(
      source: Source.Directory,
      configuration: Option[List[Pattern.Definition]],
      files: Option[Set[Source.File]],
      options: Map[Options.Key, Options.Value]
  )(implicit specification: Tool.Specification): Try[List[Result]] = {
    val completeConf = configuration.withDefaultParameters

    def isEnabled(issue: Result) = {
      issue match {
        case Result.Issue(_, _, patternId, _) => completeConf.forall(item => item.exists(_.patternId == patternId))
        case _ => true
      }
    }

    def buildFileCommands(files: Map[String, Array[String]]) = {
      files
        .map { case (key, values) => commandFor(key, completeConf, values) }
        .flatMap(item => item.toOption)
        .toList
    }

    def getStdout(command: List[String]): Try[List[String]] = {
      Try {
        CommandRunner.exec(command, Option(new File(source.path))) match {
          case Right(resultFromTool) =>
            resultFromTool.stdout
          case Left(failure) =>
            throw failure
        }
      }
    }

    val collectedFiles = collectFiles(files, source)
    val classified = options
      .get(pythonVersionKey)
      .fold {
        classifyFiles(collectedFiles)
      } { pythonVersion =>
        val validPythonVersion = Option(pythonVersion: JsValue)
          .collect {
            case JsNumber(version) => version
            case JsString(version) => Try(version.toInt)
          }
          .map(_.toString)
          .getOrElse(python3)
        Try(Map(validPythonVersion -> collectedFiles.toArray))
      }
    val commands = classified.map { item =>
      buildFileCommands(item)
    }
    val lines_iterable = commands.map { item =>
      item.map(getStdout)
    }
    val linesTry: Try[List[String]] = lines_iterable.map { iterable =>
      iterable.flatMap { item =>
        item.toOption
      }.flatten
    }
    linesTry.map { line =>
      line.flatMap(parseLine).flatten.filter(isEnabled)
    }
  }

  private def parseLine(line: String): Option[List[Result]] = {
    val LineRegex = """(.*?)###(\d*?)###(.*?)###(.*?)""".r

    def createIssue(filename: String, lineNumber: String, message: String, patternId: String) = {
      // If the pylint returns no line put the issue in the first line
      val issueLine = if (lineNumber.nonEmpty) lineNumber.toInt else 1
      Result.Issue(Source.File(filename), Result.Message(message), Pattern.Id(patternId), Source.Line(issueLine))
    }

    line match {
      case LineRegex(filename, lineNumber, patternId, message) if message.contains("invalid syntax") =>
        val fileError = Result.FileError(Source.File(filename), Option(ErrorMessage(message)))
        val issue = createIssue(filename, lineNumber, message, patternId)
        Option(List(fileError, issue))
      case LineRegex(filename, lineNumber, patternId, message) =>
        Option(List(createIssue(filename, lineNumber, message, patternId)))
      case _ =>
        Option.empty
    }
  }

  private val msgTemplate = "{abspath}###{line}###{msg_id}###{msg}"
  private val classifyScript =
    """
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

  private def collectFiles(filesOpt: Option[Set[Source.File]], source: Source.Directory) = {
    filesOpt
      .collect { case files if files.nonEmpty => files.map(_.path) }
      .getOrElse {
        //if files is empty, let the classification script to find them.
        List(source.path)
      }
      .toList
  }

  def generateClassification(files: List[String]): String = {
    val scriptArgs = files.mkString("###")
    val tmp = FileHelper.createTmpFile(classifyScript, "pylint", "")
    List("python3.7", tmp.toAbsolutePath.toString, scriptArgs).!!
  }

  private def classifyFiles(files: List[String]): Try[Map[String, Array[String]]] = {
    Try {
      val output = generateClassification(files)
      val lines = output.split(System.lineSeparator())
      val parsed = lines.map { line =>
        val splitted = line.split("###")
        (splitted(0), splitted(1))
      }
      parsed
        .groupBy { case (path, version) => version }
        .map { case (key, pairs) => (key, pairs map { case (file, version) => file }) }
    }
  }

  private def commandFor(
      interpreter: String,
      conf: Option[List[Pattern.Definition]],
      files: Array[String]
  ): Try[List[String]] = {

    val rulesPart = conf.toList.flatMap { conf =>
      val rules = conf.map(_.patternId.toString()).mkString(",")
      List("--disable=all", "-e", rules)
    }

    val configPart = conf
      .map { configuration =>
        val confFile = writeConfigFile(configuration)
        confFile.map { confPath =>
          List(s"--rcfile=$confPath")
        }
      }
      .getOrElse(Success(List.empty[String]))

    //Additional plugins
    val django = Seq("--load-plugins=pylint_django", "--disable=django-installed-checker,django-model-checker")
    val flask = Seq("--load-plugins=pylint_flask")
    val additionalPlugins = django ++ flask

    configPart.map { configPart =>
      List("python" + realInterpreterVersion(interpreter), "-m", "pylint") ++
        configPart ++ List(s"--msg-template=$msgTemplate", "--output-format=parseable") ++
        rulesPart ++ additionalPlugins ++ files
    }
  }

  def realInterpreterVersion(interpreter: String): String = {
    interpreter match {
      case "2" => "2.7"
      case "3" => "3.7"
    }
  }

  private def writeConfigFile(configuration: List[Pattern.Definition]): Try[Path] = {

    val parameters = configuration
      .flatMap { pattern =>
        pattern.parameters.getOrElse(Set.empty).map { param =>
          ParameterHeader.get(param.name) -> param
        }
      }
      .groupBy { case (header, _) => header }

    val paramsToPrint = parameters
      .map {
        case (header, params) =>
          lazy val paramString: String = params
            .map {
              case (_, pvalue) =>
                generateParameter(pvalue)
            }
            .mkString(Properties.lineSeparator)

          s"""
         |[$header]
         |$paramString
       """.stripMargin

      }
      .mkString(Properties.lineSeparator)
    write(paramsToPrint)
  }

  private def generateParameter(parameter: Parameter.Definition): String = {
    val parameterValue = (parameter.value: JsValue) match {
      case JsString(value) => value
      case other => Json.stringify(other)
    }
    s"${parameter.name}=$parameterValue"
  }

  private def randomFile(extension: String = "conf") = Try(Files.createTempFile("codacy-", s".$extension"))

  private def write(content: String): Try[Path] = {
    randomFile().map { confFile =>
      Files.write(confFile, content.getBytes)
    }
  }
}

case class PyLintParserException(message: String) extends Exception(message)
