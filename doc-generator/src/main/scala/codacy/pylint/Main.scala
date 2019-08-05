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

  def main(args: Array[String]): Unit = {
    val version: String = {
      val source = Source.fromFile("../src/main/resources/docs/patterns.json")
      val patterns = source.mkString
      val json = ujson.read(patterns)
      val res = json("version").str
      source.close()
      res
    }

    val htmlString = {
      val source = Source.fromURL(
        s"https://pylint.readthedocs.io/en/pylint-$version/technical_reference/features.html")
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
    } yield toMarkdown(name.toString)

    val pattern = """.*\((.+)\).*""".r
    val rulesNamesTitlesBodies = rules.zip(bodies).collect {
      case (rule @ pattern(ruleName), body) =>
        (ruleName, rule.stripSuffix(":"), body)
    }

    val files = rulesNamesTitlesBodies.map {
      case (r, t, b) =>
        ( /*s"../src/main/resources/docs/description/$r.md"*/ s"rules/$r.md",
         s"# $t${System.lineSeparator}$b")
    }
    val patterns = Obj(
      "name" -> "PyLint",
      "version" -> version,
      "patterns" -> Arr.from(rulesNamesTitlesBodies.map { case (ruleName, title, body) => Obj("patternId" -> ruleName) })
    )
    println(ujson.write(patterns))

    files.foreach { case (filename, content) =>
      val pw = new PrintWriter(new File(filename))
      pw.println(content)
      pw.close()
    }
  }

  implicit class NodeOps(val node: Node) extends AnyVal {
    def hasClass(cls: String): Boolean = node \@ "class" == cls
  }
}
