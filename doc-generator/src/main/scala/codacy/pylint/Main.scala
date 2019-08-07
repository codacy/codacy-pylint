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

  val version: String = {
    val source = Source.fromFile("../src/main/resources/docs/patterns.json")
    val patterns = source.mkString
    val json = ujson.read(patterns)
    val res = json("version").str
    source.close()
    res
  }

  val htmlString = {
    val source = Source.fromURL(s"https://pylint.readthedocs.io/en/pylint-$version/technical_reference/features.html")
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

  val docsPath = "../src/main/resources/docs"

  val files = rulesNamesTitlesBodiesMarkdown.map {
    case (r, t, b) =>
      (s"$docsPath/description/$r.md", s"# $t${System.lineSeparator}$b")
  }

  val patterns = ujson.write(
    Obj(
      "name" -> "PyLint (Python 3)",
      "version" -> version,
      "patterns" -> Arr.from(rulesNamesTitlesBodies.map {
        case (ruleName, _, _) =>
          Obj("patternId" -> ruleName, "level" -> {
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
          }, "category" -> "CodeStyle")
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
    files.foreach {
      case (filename, content) =>
        val pw = new PrintWriter(new File(filename))
        pw.println(content.trim())
        pw.close()
    }
  }
}
