package codacy.pylint

import java.nio.file.{Paths, Files, Path}
import codacy.dockerApi._
import play.api.libs.json.{JsError, JsSuccess, Json, JsString}
import scala.sys.process._
import scala.util.{Failure, Success, Properties, Try}

object Pylint extends Tool {

  override def apply(path: Path, conf: Option[Seq[PatternDef]], files: Option[Set[Path]])(implicit spec: Spec): Try[Iterable[Result]] = {
    lazy val enabledPatterns = conf.map(_.map(_.patternId)).getOrElse(spec.patterns.map(_.patternId)).toSet[PatternId]

    commandFor(path, conf, files).flatMap{ case cmd =>
      val wrappedCmd = Seq("bash","-c", cmd.mkString(" "))

      Try(wrappedCmd.lineStream_!(ProcessLogger(_ => ()))).flatMap{ case lines =>
        val output = lines.mkString(Properties.lineSeparator)
        Try(Json.parse(output)).flatMap{ case json =>
          json.validate[Seq[PylintResult]] match{
            case JsSuccess(results,_) =>
              Success(results.flatMap(asResult(_,path,enabledPatterns)))
            case JsError(err) =>
              Failure(new Throwable(Json.stringify(JsError.toFlatJson(err))))
          }
        }
      }
    }
  }

  private[this] def asResult(pylintResult: PylintResult, rootPath:Path, enabledPatterns:Set[PatternId]):Option[Result] = {
    import pylintResult._
    enabledPatterns.find(_ == PatternId(module) ).flatMap{ case patternId =>
      toRelativePath(rootPath,path).map{ case fileName =>
        Result(
          filename = fileName,
          message = ResultMessage(message),
          patternId = patternId,
          line = ResultLine(line)
        )
      }
    }
  }

  private[this] def toRelativePath(rootDirectory: Path, path: String): Option[SourcePath] = {
    val absolutePath = Paths.get(path)
    Try(rootDirectory.relativize(absolutePath)).map{ case relativePaths => SourcePath(relativePaths.toString )}.toOption
  }

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
      Seq("pylint") ++ configPart ++ Seq(
        "-f json"
      ) ++ rulesPart ++ filesPart
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


