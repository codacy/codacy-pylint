package codacy.pylint

import java.io
import java.io.{FileWriter, File}
import java.nio.file.{Files, Path}

import codacy.dockerApi._
import play.api.libs.json.{Json, JsString, JsValue}

import scala.sys.process._
import scala.util.{Properties, Try}

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

    lazy val rulesFromSpec = spec.patterns.map(_.patternId).toSeq

    val rulesToApply = conf.fold(rulesFromSpec)(patterns => patterns.map(_.patternId))
    val configurationCmd = writeConfigFile(conf).map(f => Seq("--rcfile=" + f.getAbsolutePath)).getOrElse(Seq.empty)
    val filesCmd = files.getOrElse(Set(path.toAbsolutePath)).map(_.toString).toSeq

    Try(Seq("pylint") ++
      configurationCmd ++
      Seq("--msg-template='{path}:{line}: [{msg_id}({symbol}), {obj}] {msg}'",
        "--reports=no",
        "--disable=all",
        "-e", rulesToApply.mkString(",")
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
            generateParameter(pvalue)
        }.mkString(Properties.lineSeparator)
    }.mkString(Properties.lineSeparator)

     write(paramsToPrint)
  }

  private[this] def generateParameter(parameter: ParameterDef): String = {
    val parameterValue = parameter.value match {
      case JsString(value) => value
      case other => Json.stringify(other)
    }
    s"${parameter.name.value}=$parameterValue"
  }

  private object ParameterHeader {
    val values = Map(
      "required-attributes" -> "BASIC",
      "bad-functions" -> "BASIC",
      "good-names" -> "BASIC",
      "bad-names" -> "BASIC",
      "name-group" -> "BASIC",
      "include-naming-hint" -> "BASIC",
      "function-rgx" -> "BASIC",
      "function-name-hint" -> "BASIC",
      "variable-rgx" -> "BASIC",
      "variable-name-hint" -> "BASIC",
      "const-rgx" -> "BASIC",
      "const-name-hint" -> "BASIC",
      "attr-rgx" -> "BASIC",
      "attr-name-hint" -> "BASIC",
      "argument-rgx" -> "BASIC",
      "argument-name-hint" -> "BASIC",
      "class-attribute-rgx" -> "BASIC",
      "class-attribute-name-hint" -> "BASIC",
      "inlinevar-rgx" -> "BASIC",
      "inlinevar-name-hint" -> "BASIC",
      "class-rgx" -> "BASIC",
      "class-name-hint" -> "BASIC",
      "module-rgx" -> "BASIC",
      "module-name-hint" -> "BASIC",
      "method-rgx" -> "BASIC",
      "method-name-hint" -> "BASIC",
      "no-docstring-rgx" -> "BASIC",
      "docstring-min-length" -> "BASIC",
      "spelling-dict" -> "SPELLING",
      "spelling-ignore-words" -> "SPELLING",
      "spelling-private-dict-file" -> "SPELLING",
      "spelling-store-unknown-words" -> "SPELLING",
      "min-similarity-lines" -> "SIMILARITIES",
      "ignore-comments" -> "SIMILARITIES",
      "ignore-docstrings" -> "SIMILARITIES",
      "ignore-imports" -> "SIMILARITIES",
      "logging-modules" -> "LOGGING",
      "max-line-length" -> "FORMAT",
      "ignore-long-lines" -> "FORMAT",
      "single-line-if-stmt" -> "FORMAT",
      "no-space-check" -> "FORMAT",
      "max-module-lines" -> "FORMAT",
      "indent-string" -> "FORMAT",
      "indent-after-paren" -> "FORMAT",
      "expected-line-ending-format" -> "FORMAT",
      "notes" -> "MISCELLANEOUS",
      "ignore-mixin-members" -> "TYPECHECK",
      "ignored-modules" -> "TYPECHECK",
      "ignored-classes" -> "TYPECHECK",
      "zope" -> "TYPECHECK",
      "generated-members" -> "TYPECHECK",
      "ignore-iface-methods" -> "CLASSES",
      "defining-attr-methods" -> "CLASSES",
      "valid-classmethod-first-arg" -> "CLASSES",
      "valid-metaclass-classmethod-first-arg" -> "CLASSES",
      "exclude-protected" -> "CLASSES",
      "max-args" -> "DESIGN",
      "ignored-argument-names" -> "DESIGN",
      "max-locals" -> "DESIGN",
      "max-returns" -> "DESIGN",
      "max-branches" -> "DESIGN",
      "max-statements" -> "DESIGN",
      "max-parents" -> "DESIGN",
      "max-attributes" -> "DESIGN",
      "min-public-methods" -> "DESIGN",
      "max-public-methods" -> "DESIGN",
      "deprecated-modules" -> "IMPORTS",
      "import-graph" -> "IMPORTS",
      "ext-import-graph" -> "IMPORTS",
      "int-import-graph" -> "IMPORTS",
      "overgeneral-exceptions" -> "EXCEPTIONS"
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
