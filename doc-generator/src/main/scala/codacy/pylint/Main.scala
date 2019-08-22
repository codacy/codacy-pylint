package codacy.pylint

import java.io.{File, IOException, PrintWriter}
import java.nio.file.attribute.BasicFileAttributes
import java.nio.file.{FileVisitResult, Files, Path, SimpleFileVisitor}

import scala.xml._
import scala.io.Source
import ujson._

import sys.process._

object Main {
  private val deleteRecursivelyVisitor = new SimpleFileVisitor[Path] {
    override def visitFile(file: Path, attrs: BasicFileAttributes): FileVisitResult = {
      Files.delete(file)
      FileVisitResult.CONTINUE
    }

    override def postVisitDirectory(dir: Path, exc: IOException): FileVisitResult = {
      Files.delete(dir)
      FileVisitResult.CONTINUE
    }
  }

  implicit class NodeOps(val node: Node) extends AnyVal {
    def hasClass(cls: String): Boolean = node \@ "class" == cls
  }

  def toMarkdown(html: String): String = {
    val directory = Files.createTempDirectory("pylintDoc")
    try {
      val file = Files.createTempFile(directory, "pylint-doc", ".html")
      Files.write(file, html.getBytes())
      Seq("pandoc", "-f", "html", "-t", "markdown", file.toString).!!
    } finally {
      Files.walkFileTree(directory, deleteRecursivelyVisitor)
    }
  }

  val docsPath = "../docs"

  val version: String = {
    val source = Source.fromFile(s"$docsPath/patterns.json")
    val patterns = source.mkString
    val json = ujson.read(patterns)
    val res = json("version").str
    source.close()
    res
  }

  val htmlString = {
    val source = Source.fromURL(
      s"http://pylint.pycqa.org/en/${version.split('.').init.mkString(".")}/technical_reference/features.html"
    )
    val res = source.mkString
    source.close()
    res
  }

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
    case (rule @ pattern(ruleName), body) =>
      (ruleName, rule.stripSuffix(":"), body)
  }

  val rulesNamesTitlesBodiesMarkdown = rulesNamesTitlesBodies.map {
    case (name, title, body) => (name, title, toMarkdown(body.toString))
  }

  val rulesNamesTitlesBodiesPlainText = rulesNamesTitlesBodies.map {
    case (name, title, body) => (name, title, body.text)
  }

  val files = rulesNamesTitlesBodiesMarkdown.map {
    case (r, t, b) =>
      (s"$docsPath/description/$r.md", s"# $t${System.lineSeparator}$b")
  }

  // retro compatibility parameters
  def addCustomParameters(obj: Obj, ruleName: String): Unit = {
    def addParams(params: Obj*): Unit = obj("parameters") = params
    ruleName match {
      case "R0914" =>
        addParams(Obj("name" -> "max-locals", "default" -> 15))
      case "C0301" =>
        addParams(Obj("name" -> "max-line-length", "default" -> 120))
      case "C0102" =>
        addParams(Obj("name" -> "bad-names", "default" -> "foo,bar,baz,toto,tutu,tata"))
      case "C0103" =>
        addParams(
          Obj("name" -> "argument-rgx", "default" -> "[a-z_][a-z0-9_]{2,30}$"),
          Obj("name" -> "attr-rgx", "default" -> "[a-z_][a-z0-9_]{2,30}$"),
          Obj("name" -> "class-rgx", "default" -> "[A-Z_][a-zA-Z0-9]+$"),
          Obj("name" -> "const-rgx", "default" -> "(([A-Z_][A-Z0-9_]*)|(__.*__))$"),
          Obj("name" -> "function-rgx", "default" -> "[a-z_][a-z0-9_]{2,30}$"),
          Obj("name" -> "method-rgx", "default" -> "[a-z_][a-z0-9_]{2,30}$"),
          Obj("name" -> "module-rgx", "default" -> "(([a-z_][a-z0-9_]*)|([A-Z][a-zA-Z0-9]+))$"),
          Obj("name" -> "variable-rgx", "default" -> "[a-z_][a-z0-9_]{2,30}$"),
          Obj("name" -> "inlinevar-rgx", "default" -> "[A-Za-z_][A-Za-z0-9_]*$"),
          Obj("name" -> "class-attribute-rgx", "default" -> "([A-Za-z_][A-Za-z0-9_]{2,30}|(__.*__))$")
        )
    }
  }

  val patterns = ujson.write(
    Obj(
      "name" -> "PyLint",
      "version" -> version,
      "patterns" -> Arr.from(rulesNamesTitlesBodies.map {
        case (ruleName, _, _) =>
          val result = Obj(
            "patternId" -> ruleName,
            "level" -> {
              ruleName.headOption
                .map {
                  case 'C' => "Info" // "Convention" non valid
                  case 'R' => "Info" // "Refactor" non valid
                  case 'W' | 'I' => "Warning"
                  case 'E' => "Error"
                  case 'F' => "Error" // "Fatal" non valid
                  case _ => throw new Exception(s"Unknown error type for $ruleName")
                }
                .getOrElse(throw new Exception(s"Empty rule name"))
            },
            "category" -> "CodeStyle"
          )
          addCustomParameters(result, ruleName)
          result
      })
    ),
    indent = 2
  )

  val description = ujson.write(Arr.from(rulesNamesTitlesBodiesPlainText.map {
    case (ruleName, title, body) =>
      Obj("patternId" -> ruleName, "title" -> title, "description" -> body)
  }), indent = 2)

  def writeToFile(file: String, content: String): Unit = {
    val patternsPW = new PrintWriter(new File(file))
    patternsPW.println(content)
    patternsPW.close()
  }

  def main(args: Array[String]): Unit = {
    writeToFile(s"$docsPath/patterns.json", patterns)
    writeToFile(s"$docsPath/description/description.json", description)
    files
      .map { case (n, c) => (n, c.trim) }
      .foreach((writeToFile _).tupled)
  }
}
