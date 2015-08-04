package codacy.pmdjava

import java.nio.charset.StandardCharsets
import java.nio.file.{Files, Path, Paths, StandardOpenOption}

import codacy.dockerApi._
import play.api.libs.json.{JsString, Json}

import scala.sys.process._
import scala.util.{Properties, Success, Try}
import scala.xml.{Elem, XML}

object PmdJava extends Tool {

  override def apply(path: Path, conf: Option[Seq[PatternDef]], files: Option[Set[Path]])(implicit spec: Spec): Try[Iterable[Result]] = {
    getCommandFor(path, conf, files, spec, resultFilePath).flatMap { case cmd =>
      cmd.!(discardingLogger)
      Try(XML.loadFile(resultFilePath.toFile)).map(outputParsed)
    }
  }

  private[this] lazy val ruleSetsDefault = Seq(
    "android", "basic", "braces", "clone", "codesize", "comments", "controversial",
    "coupling", "design", "empty", "finalizers", "imports", "junit", "migrating",
    "naming", "optimizations", "sunsecure", "strictexception", "strings",
    "typeresolution", "unnecessary", "unusedcode").map { case group => s"java-$group" }.mkString(",")

  //we are using an output file we don't care for stdout or err...
  private[this] lazy val discardingLogger = ProcessLogger((_: String) => ())

  private[this] lazy val resultFilePath = Paths.get(Properties.tmpDir, "pmd-result.xml")

  private[this] def getCommandFor(path: Path, conf: Option[Seq[PatternDef]], files: Option[Set[Path]], spec: Spec, outputFilePath: Path): Try[Seq[String]] = {

    val configPath = conf.map(getConfigFile(_).map(_.toAbsolutePath.toString)).getOrElse(Success(ruleSetsDefault))

    configPath.map { case configuration =>
      val configurationCmd = Seq("-rulesets", configuration)

      val filesCmd = files.map(_.mkString(",")).getOrElse(path.toAbsolutePath.toString)
      //maybe someone want's to create a symlink for this in build.sbt
      Seq("/usr/local/pmd-bin-5.3.2/bin/run.sh", "pmd", "-d", filesCmd, "-f", "xml", "-r", outputFilePath.toAbsolutePath.toString) ++ configurationCmd
    }
  }

  private[this] def xmlLocation(ruleName: String, ruleSet: String): Option[String] = {
    RuleType.get(ruleSet).map { ruleString =>
      s"$ruleString$ruleName"
    }
  }

  private[this] def patternIdByRuleNameAndRuleSet(ruleName: String, ruleSet: String)(implicit spec: Spec): Option[PatternId] = {
    spec.patterns.collectFirst { case pattern if xmlLocation(ruleName, ruleSet).contains(pattern.patternId.value) =>
      pattern.patternId
    }
  }

  private[this] def outputParsed(outputXml: Elem)(implicit spec: Spec) = {
    (outputXml \ "file").flatMap { case file =>
      lazy val fileName = SourcePath(file \@ "name")

      (file \ "violation").flatMap { case violation =>
        patternIdByRuleNameAndRuleSet(
          ruleName = violation \@ "rule",
          ruleSet = violation \@ "ruleset"
        ).flatMap { case patternId =>
          Try(
            Result(
              filename = fileName,
              message = ResultMessage(violation.text.trim),
              patternId = patternId,
              line = ResultLine((violation \@ "beginline").toInt)
            )
          ).toOption
        }
      }
    }
  }

  private[this] def getConfigFile(conf: Seq[PatternDef]): Try[Path] = {
    val rules = for {
      pattern <- conf
      patternConfiguration <- generateRule(pattern.patternId, pattern.parameters)
    } yield patternConfiguration

    val xmlConfiguration =

      <ruleset name="All Java Rules"
               xmlns="http://pmd.sourceforge.net/ruleset/2.0.0"
               xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
               xsi:schemaLocation="http://pmd.sourceforge.net/ruleset/2.0.0 http://pmd.sourceforge.net/ruleset_2_0_0.xsd">
        {rules}
      </ruleset>

    fileForConfig(xmlConfiguration)
  }

  private[this] def fileForConfig(config: Elem) = tmpfile(config.toString())

  private[this] def tmpfile(content: String, prefix: String = "ruleset", suffix: String = ".xml"): Try[Path] = {
    Try(Files.write(
      Files.createTempFile(prefix, suffix),
      content.getBytes(StandardCharsets.UTF_8),
      StandardOpenOption.CREATE
    ))
  }

  private[this] def getPatternNameById(patternId: PatternId): String = {
    patternId.value.replace('_', '/')
  }

  private[this] def generateRule(patternId: PatternId, parameters: Option[Set[ParameterDef]]): Elem = {
    val xmlPorperties = parameters.map(_.map(generateParameter)).getOrElse(Set.empty)

    <rule ref={getPatternNameById(patternId)}>
      <properties>
        {xmlPorperties}
      </properties>
    </rule>
  }

  private[this] def generateParameter(parameter: ParameterDef): Elem = {
    val parameterValue = parameter.value match {
      case JsString(value) => value
      case other => Json.stringify(other)
    }
      <property name={parameter.name} value={parameterValue}/>
  }

  private object RuleType {
    val values = Map(
      "Android" -> "rulesets_java_android.xml_",
      "Basic" -> "rulesets_java_basic.xml_",
      "Braces" -> "rulesets_java_braces.xml_",
      "Clone Implementation" -> "rulesets_java_clone.xml_",
      "Code Size" -> "rulesets_java_codesize.xml_",
      "Comments" -> "rulesets_java_comments.xml_",
      "Controversial" -> "rulesets_java_controversial.xml_",
      "Coupling" -> "rulesets_java_coupling.xml_",
      "Design" -> "rulesets_java_design.xml_",
      "Empty Code" -> "rulesets_java_empty.xml_",
      "Finalizer" -> "rulesets_java_finalizers.xml_",
      "Import Statements" -> "rulesets_java_imports.xml_",
      "J2EE" -> "rulesets_java_j2ee.xml_",
      "JUnit" -> "rulesets_java_junit.xml_",
      "JavaBeans" -> "rulesets_java_javabeans.xml_",
      "Jakarta Commons Logging" -> "rulesets_java_logging-jakarta-commons.xml_",
      "Java Logging" -> "rulesets_java_logging-java.xml_",
      "Migration" -> "rulesets_java_migrating.xml_",
      "Migration13" -> "rulesets_java_migrating.xml_",
      "Migration14" -> "rulesets_java_migrating.xml_",
      "Migration15" -> "rulesets_java_migrating.xml_",
      "MigratingToJUnit4" -> "rulesets_java_migrating.xml_",
      "Naming" -> "rulesets_java_naming.xml_",
      "Optimization" -> "rulesets_java_optimizations.xml_",
      "Strict Exceptions" -> "rulesets_java_strictexception.xml_",
      "String and StringBuffer" -> "rulesets_java_strings.xml_",
      "Security Code Guidelines" -> "rulesets_java_sunsecure.xml_",
      "Type Resolution" -> "rulesets_java_typeresolution.xml_",
      "Unnecessary" -> "rulesets_java_unnecessary.xml_",
      "Unused Code" -> "rulesets_java_unusedcode.xml_"
    )

    def get(value: String): Option[String] = values.get(value)
  }

}
