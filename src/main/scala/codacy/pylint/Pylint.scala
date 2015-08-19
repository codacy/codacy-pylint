package codacy.pylint

import java.nio.file.{Paths, Files, Path}
import codacy.dockerApi._
import play.api.libs.json._
import scala.sys.process._
import scala.util.{Failure, Success, Properties, Try}

object Pylint extends Tool {

  override def apply(path: Path, conf: Option[Seq[PatternDef]], files: Option[Set[Path]])(implicit spec: Spec): Try[Iterable[Result]] = {
    def isEnabled(result: Result) = conf.map(_.exists(_.patternId == result.patternId)).getOrElse(true)

    commandFor(path, conf, files).flatMap{ case cmd =>
      val wrappedCmd = Seq("bash","-c", cmd.mkString(" "))

      Try(wrappedCmd.lineStream_!(ProcessLogger(_ => ()))).map{ case lines =>
        lines.flatMap(parseLine).filter( isEnabled ).flatMap{ case result =>
          toRelativePath(path, result.filename).map{ case fileName =>
            result.copy(filename = fileName)
          }
        }
      }
    }
  }

  private[this]implicit lazy val writer = Json.reads[Result]

  private[this] def parseLine(line:String) = Try(Json.parse(line)).toOption.flatMap( _.asOpt[Result] )

  private[this] def toRelativePath(rootDirectory: Path, path: String): Option[SourcePath] = {
    val absolutePath = Paths.get(path)
    Try(rootDirectory.relativize(absolutePath)).map{ case relativePaths => SourcePath(relativePaths.toString )}.toOption
  }

  private[this] val msgTemplate = """'{{"filename":"{path}","line":{line},"message":"{msg}","patternId":"{msg_id}"}}'"""

  private[this] def commandFor(path: Path, conf: Option[Seq[PatternDef]], files: Option[Set[Path]])(implicit spec: Spec): Try[Seq[String]] = {

    val rulesPart = conf.toList.flatMap{ case conf =>
      val rules = conf.map(_.patternId.toString).mkString(",")
      Seq("--disable=all","-e",rules)
    }
    //get the list of files workaround
    val filesPart: Iterable[String] = files.collect{ case files if files.nonEmpty => files.map(_.toString) }.getOrElse{
      //if files is empty check if we have a __init__.py if not we do a find...
      //do we have a file called __init__.py ?
      val isPackage = Files.exists(path.resolve(Paths.get("__init__.py")))
      if(isPackage){
        Seq(path.toString)
      }
      else {
        Seq(s"`find $path -type f -name *.py`")
      }
    }

    val configPart = conf.map{ case configuration =>
      val confFile = writeConfigFile(configuration)
      confFile.map{ case confPath =>
        Seq(s"--rcfile=$confPath")
      }
    }.getOrElse(Success(Seq.empty[String]))

    configPart.map{ case configPart =>
      Seq("pylint") ++ configPart ++ Seq(s"--msg-template=$msgTemplate") ++ rulesPart ++ filesPart
    }
  }

  private[this] def writeConfigFile(configuration: Seq[PatternDef]): Try[Path] = {

    val parameters = configuration.flatMap{ case pattern =>
      pattern.parameters.getOrElse(Set.empty).map{ case param =>
        ParameterHeader.get(param.name)->param
      }
    }.groupBy{ case (header,_) => header }

    val paramsToPrint = parameters.map{ case (header, params) =>

      lazy val paramString: String = params.map { case (_, pvalue) =>
        generateParameter(pvalue)
      }.mkString( Properties.lineSeparator )

      s"""[$header]
         |$paramString
       """.stripMargin

    }.mkString(Properties.lineSeparator)
    write(paramsToPrint)
  }

  private[this] def generateParameter(parameter: ParameterDef): String = {
    val parameterValue = parameter.value match {
      case JsString(value) => value
      case other => Json.stringify(other)
    }
    s"${parameter.name}=$parameterValue"
  }

  private[this] def randomFile(extension: String = "conf") = Try(
    Files.createTempFile("codacy-", s".$extension")
  )

  private[this] def write(content: String): Try[Path] = {
    randomFile().map{ case confFile =>
      Files.write(confFile,content.getBytes())
    }
  }
}

case class PyLintParserException(message: String) extends Exception(message)


