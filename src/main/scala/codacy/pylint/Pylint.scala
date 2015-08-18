package codacy.pylint

import java.nio.file.{Paths, Files, Path}
import codacy.dockerApi._
import play.api.libs.json.{Json, JsString, JsValue}
import scala.sys.process._
import scala.util.{Properties, Try}

object Pylint extends Tool {

  override def apply(path: Path, conf: Option[Seq[PatternDef]], files: Option[Set[Path]])(implicit spec: Spec): Try[Iterable[Result]] = {
    commandFor(path, conf, files).flatMap{ case cmd =>
      Try(cmd.lineStream_!(discardingLogger)).map{ case lines =>
        lines.flatMap{ case line =>
          parseLine(path,line)
        }
      }
    }
  }

  private[this] def parseLine(rootDir: Path, line: String): Iterable[Result] = {
    val RegMatch = """(.+):([0-9]+): \[(.+)\((.+)\), .*?\] (.*)""".r
    val ErrorMatch = """^Traceback.*""".r

    Seq(line).flatMap{
      case RegMatch(file, line, id, verboseId, message) =>
        toRelativePath(rootDir, file).map{ case filename =>
          Result(filename, ResultMessage(message), PatternId(id), ResultLine(line.toInt))
        }
      case _ => Seq.empty
    }
  }

  private[this] def toRelativePath(rootDirectory: Path, path: String): Option[SourcePath] = {
    val absolutePath = Paths.get(path)
    Try(rootDirectory.relativize(absolutePath)).map{ case relativePaths => SourcePath(relativePaths.toString )}.toOption
  }

  private[this] lazy val discardingLogger = ProcessLogger((_: String) => ())

  //TODO: the default should be all?? i really don't think so
  private[this] def defaultConf(implicit spec: Spec) = {
    spec.patterns.map{ case patternSpec =>
      val params = patternSpec.parameters.map{ case paramSpecs =>
        paramSpecs.map{ case paramSpec =>
          ParameterDef(paramSpec.name, paramSpec.default)
        }
      }
      PatternDef(patternSpec.patternId, params)
    }.toSeq
  }

  private[this] def commandFor(path: Path, conf: Option[Seq[PatternDef]], files: Option[Set[Path]])(implicit spec: Spec): Try[Seq[String]] = {

    val configuration: Seq[PatternDef] = conf.getOrElse(defaultConf)
    lazy val rulesString = configuration.map(_.patternId.toString).mkString(",")
    lazy val filesCmd = files.getOrElse(Set(path.toAbsolutePath)).map(_.toString).toSeq

    writeConfigFile(configuration).map{ case confPath =>
      Seq(
        "pylint",
        s"--rcfile=$confPath",
        "--msg-template='{path}:{line}: [{msg_id}({symbol}), {obj}] {msg}'",
        "--reports=no",
        "--disable=all",
        "-e",
        rulesString
      ) ++ filesCmd
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


