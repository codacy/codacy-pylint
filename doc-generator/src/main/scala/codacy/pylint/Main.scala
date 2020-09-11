package codacy.pylint

import better.files.File

import scala.xml._
import scala.io.Source
import ujson._

import sys.process._
import scala.util.Using
import scala.util.chaining._

object Main {
  val blacklist = Set("E0401")

  implicit class NodeOps(val node: Node) extends AnyVal {
    def hasClass(cls: String): Boolean = node \@ "class" == cls
  }

  def toMarkdown(html: String): String = {
    val result =
      for {
        file <- File.temporaryFile()
        _ = file.write(html)
        res = Seq("pandoc", "-f", "html", "-t", "commonmark", file.pathAsString).!!
      } yield res
    result.get()
  }

  val docsPath = "../docs"

  val version: String = {
    val file = File("../requirements.txt")
    file.lines.collectFirst {
      case s"pylint==$version" => version
    }.get
  }

  val htmlString = Using.resource {
    val url = new java.net.URL(
      s"https://pylint.pycqa.org/en/${version.split('.').init.mkString(".")}/technical_reference/features.html"
    )
    val connection = url.openConnection.asInstanceOf[java.net.HttpURLConnection]
    connection.setRequestProperty("User-agent", "Mozilla/5.0")
    Source.fromInputStream(connection.getInputStream)
  }(_.mkString)

  val html = XML.loadString(htmlString)

  val rules = for {
    ths <- html \\ "th"
    th <- ths
    name <- th.find(_.hasClass("field-name"))
  } yield name.text

  val bodies = for {
    tds <- html \\ "td"
    td <- tds
    name <- td.find(_.hasClass("field-body"))
  } yield name

  val pattern = """.*\((.+)\).*""".r

  val rulesNamesTitlesBodies = rules.zip(bodies).collect {
    case (rule @ pattern(ruleName), body) if !blacklist.contains(ruleName) =>
      (ruleName, rule.stripSuffix(":"), body)
  }

  val rulesNamesTitlesBodiesMarkdown = rulesNamesTitlesBodies.map {
    case (name, title, body) => (name, title, toMarkdown(body.toString))
  }

  def makePlainText(title: String, body: String): (String, String) = {
    val newLines = body.linesIterator.toList match {
      case title :: secondLine :: rest =>
        title.stripSuffix(".") + "." :: secondLine.capitalize :: rest
      case lines => lines
    }
    val descriptionText = newLines.mkString(" ")
    (title, descriptionText)
  }

  val rulesNamesTitlesBodiesPlainText = rulesNamesTitlesBodies.map {
    case (name, title, body) =>
      val (newTitle, newBody) = makePlainText(title, body.text)
      (name, newTitle, newBody)
  }

  val files = rulesNamesTitlesBodiesMarkdown.map {
    case (r, t, b) =>
      (
        File(docsPath) / "description" / s"$r.md",
        s"""# $t
           |
           |$b""".stripMargin
      )
  }

  final case class Parameter(name: String, description: String, default: Value)

  val parameters = Map[String, Seq[Parameter]](
    "R0914" -> Seq(
      Parameter(
        "max-locals",
        "Maximum number of locals for function / method body.",
        15
      )
    ),
    "C0301" -> Seq(
      Parameter(
        "max-line-length",
        "Maximum number of characters on a single line.",
        120
      )
    ),
    "C0102" -> Seq(
      Parameter(
        "bad-names",
        "Bad variable names which should always be refused, separated by a comma.",
        "foo,bar,baz,toto,tutu,tata"
      )
    ),
    "C0103" ->
      Seq(
        Parameter(
          "argument-rgx",
          "Regular expression matching correct argument names. Overrides argument- naming-style.",
          "[a-z_][a-z0-9_]{2,30}$"
        ),
        Parameter(
          "attr-rgx",
          "Regular expression matching correct attribute names.",
          "[a-z_][a-z0-9_]{2,30}$"
        ),
        Parameter(
          "class-rgx",
          "Regular expression matching correct class names.",
          "[A-Z_][a-zA-Z0-9]+$"
        ),
        Parameter(
          "const-rgx",
          "Regular expression matching correct constant names.",
          "(([A-Z_][A-Z0-9_]*)|(__.*__))$"
        ),
        Parameter(
          "function-rgx",
          "Regular expression matching correct function names.",
          "[a-z_][a-z0-9_]{2,30}$"
        ),
        Parameter(
          "method-rgx",
          "Regular expression matching correct method names.",
          "[a-z_][a-z0-9_]{2,30}$"
        ),
        Parameter(
          "module-rgx",
          "Regular expression matching correct module names.",
          "(([a-z_][a-z0-9_]*)|([A-Z][a-zA-Z0-9]+))$"
        ),
        Parameter(
          "variable-rgx",
          "Regular expression matching correct variable names.",
          "[a-z_][a-z0-9_]{2,30}$"
        ),
        Parameter(
          "inlinevar-rgx",
          "Regular expression matching correct inline iteration names.",
          "[A-Za-z_][A-Za-z0-9_]*$"
        ),
        Parameter(
          "class-attribute-rgx",
          "Regular expression matching correct class attribute names.",
          "([A-Za-z_][A-Za-z0-9_]{2,30}|(__.*__))$"
        )
      )
  )

  val defaultPatterns = Set(
    "C0123",
    "C0200",
    "C0303",
    "C1001",
    "E0100",
    "E0101",
    "E0102",
    "E0103",
    "E0104",
    "E0105",
    "E0106",
    "E0107",
    "E0108",
    "E0110",
    "E0112",
    "E0113",
    "E0114",
    "E0115",
    "E0116",
    "E0117",
    "E0202",
    "E0203",
    "E0211",
    "E0236",
    "E0238",
    "E0239",
    "E0240",
    "E0241",
    "E0301",
    "E0302",
    "E0601",
    "E0603",
    "E0604",
    "E0701",
    "E0702",
    "E0703",
    "E0704",
    "E0710",
    "E0711",
    "E0712",
    "E1003",
    "E1102",
    "E1111",
    "E1120",
    "E1121",
    "E1123",
    "E1124",
    "E1125",
    "E1126",
    "E1127",
    "E1132",
    "E1200",
    "E1201",
    "E1205",
    "E1206",
    "E1300",
    "E1301",
    "E1302",
    "E1303",
    "E1304",
    "E1305",
    "E1306",
    "R0201",
    "R0202",
    "R0203",
    "W0101",
    "W0102",
    "W0104",
    "W0105",
    "W0106",
    "W0107",
    "W0108",
    "W0109",
    "W0110",
    "W0120",
    "W0122",
    "W0124",
    "W0150",
    "W0199",
    "W0221",
    "W0222",
    "W0233",
    "W0404",
    "W0410",
    "W0601",
    "W0602",
    "W0604",
    "W0611",
    "W0612",
    "W0622",
    "W0623",
    "W0702",
    "W0705",
    "W0711",
    "W1300",
    "W1301",
    "W1302",
    "W1303",
    "W1305",
    "W1306",
    "W1307"
  )

  def addPatternsParameters(obj: Obj, ruleName: String): Unit = {
    addParameters(
      obj,
      ruleName,
      param => Obj("name" -> param.name, "default" -> param.default)
    )
  }

  def addDescriptionParameters(obj: Obj, ruleName: String): Unit = {
    addParameters(
      obj,
      ruleName,
      param => Obj("name" -> param.name, "description" -> param.description)
    )
  }

  def addParameters(
      obj: Obj,
      ruleName: String,
      f: Parameter => Obj
  ): Unit = {
    for {
      params <- parameters.get(ruleName)
    } {
      obj("parameters") = params.map(f)
    }
  }

  val patterns = ujson.write(
    Obj(
      "name" -> "pylint",
      "version" -> version,
      "patterns" -> Arr.from(rulesNamesTitlesBodies.map {
        case (ruleName, _, _) =>
          val (category, subcategory) = getCategory(ruleName)

          Obj(
            "patternId" -> ruleName,
            "level" -> {
              ruleName.headOption
                .map {
                  case 'C'       => "Info" // "Convention" non valid
                  case 'R'       => "Info" // "Refactor" non valid
                  case 'W' | 'I' => "Warning"
                  case 'E'       => "Error"
                  case 'F'       => "Error" // "Fatal" non valid
                  case _ =>
                    throw new Exception(s"Unknown error type for $ruleName")
                }
                .getOrElse(throw new Exception(s"Empty rule name"))
            },
            "category" -> category,
            "enabled" -> defaultPatterns.contains(ruleName)
          ).tap { result =>
            subcategory.foreach(x => result("subcategory") = x)
            addPatternsParameters(result, ruleName)
          }
      })
    ),
    indent = 2
  )

  private def getCategory(patternId: String) = {
    val commandInjection = ("Security", Some("CommandInjection"))
    patternId match {
      case "W0123" => commandInjection
      case "W0122" => commandInjection
      case _       => ("CodeStyle", None)
    }
  }

  val description = ujson.write(
    Arr.from(rulesNamesTitlesBodiesPlainText.map {
      case (ruleName, title, body) =>
        val result =
          Obj("patternId" -> ruleName, "title" -> title, "description" -> body)
        addDescriptionParameters(result, ruleName)
        result
    }),
    indent = 2
  )

  def writeToFile(file: File, string: String): Unit = {
    file.write(s"${string}${System.lineSeparator}")
  }

  def main(args: Array[String]): Unit = {
    writeToFile(File(docsPath) / "patterns.json", patterns)
    writeToFile(
      File(docsPath) / "description" / "description.json",
      description
    )
    files
      .map { case (n, c) => (n, c.trim) }
      .foreach { case (file, content) => writeToFile(file, content) }
  }
}
