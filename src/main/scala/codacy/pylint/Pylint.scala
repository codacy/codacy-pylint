package codacy.pylint

import java.io
import java.io.{FileWriter, File}
import java.nio.file.{Files, Path, Paths, StandardOpenOption}

import codacy.dockerApi._
import play.api.libs.json.{JsString, Json}

import scala.sys.process._
import scala.util.{Properties, Success, Try}

object Pylint extends Tool {


  override def apply(path: Path, conf: Option[Seq[PatternDef]], files: Option[Set[Path]])(implicit spec: Spec): Try[Iterable[Result]] = {
    getCommandFor(path, conf, files, spec).flatMap { case cmd =>
      Try(parseResult(path.toString, cmd.lineStream_!(discardingLogger)))
    }
  }

  def parseResult(rootDirPath: String, lines: Seq[String]): Iterable[Result] = {
    val RegMatch = """(.+):([0-9]+): \[(.+)\((.+)\), .*?\] (.*)""".r
    val ErrorMatch = """^Traceback.*""".r

    lines.collect {
      case RegMatch(file, line, id, verboseId, message) =>
        val filename = toRelativePath(rootDirPath, Seq(file)).head
        Result(SourcePath(filename), ResultMessage(message), PatternId(id), ResultLine(line.toInt))

      case ErrorMatch() =>
        throw PyLintParserException("PyLint crashed: " + lines.mkString("\n"))
    }
  }

  protected def toRelativePath(rootDirectory: String, paths: Seq[String]): Seq[String] =
    paths.map(_.stripPrefix(rootDirectory).stripPrefix("/"))

  //we are using an output file we don't care for stdout or err...
  private[this] lazy val discardingLogger = ProcessLogger((_: String) => ())

  private[this] def getCommandFor(path: Path, conf: Option[Seq[PatternDef]], files: Option[Set[Path]], spec: Spec): Try[Seq[String]] = {

    val rulesToApply = spec.patterns.map(_.patternId).mkString(",")
    val configurationCmd = writeConfigFile(conf).map(f => Seq("--rcfile=" + f.getAbsolutePath)).getOrElse(Seq.empty)
    val filesCmd = files.getOrElse(Set(path.toAbsolutePath)).map(_.toString).toSeq

    Try(Seq("pylint") ++
      configurationCmd ++
      Seq("--msg-template='{path}:{line}: [{msg_id}({symbol}), {obj}] {msg}'",
        "--reports=no",
        "--disable=all",
        "-e", rulesToApply
      ) ++
      filesCmd)
  }

  private def writeConfigFile(configuration: Option[Seq[PatternDef]]): Option[io.File] = {

    val parameters = (for {
      patterns <- configuration.toSeq
      pattern <- patterns
      params <- pattern.parameters.toSeq
      parameter <- params
    } yield {
        (ParameterHeader.get(parameter.name.value), parameter)
      }).groupBy { case (header, _) => header }

    val paramsToPrint = parameters.map {
      case (header, params) =>
        s"[$header]\n" + params.map {
          case (_, pvalue) =>
            s"${pvalue.name.value}=${pvalue.value}}"
        }.mkString(Properties.lineSeparator)
    }.mkString(Properties.lineSeparator)

    write(paramsToPrint)
  }

  //TODO: One line for each parameter (get from parsing --> pylint --generate-rcfile > out.txt )
  private object ParameterHeader {
    val values = Map(
      "max-line-length" -> "FORMAT"
    )

    def get(value: String): String = values.getOrElse(value, "MASTER")
  }


  private def randomFile(extension: String = "conf"): File = {
    Files.createTempFile("codacy-", s".$extension").toFile
  }

  def write(content: String, extension: String = "conf"): Option[File] = {
    Try {
      val file = randomFile(extension)
      val writer = new FileWriter(file)
      writer.write(content)
      writer.flush()
      writer.close()

      file.setReadable(true, false)
      file.setWritable(true, false)

      file
    }.toOption
  }
}

case class PyLintParserException(message: String) extends Exception(message)
