package codacy.dockerApi

import play.api.libs.json.Json

import scala.util.{Failure, Success}

abstract class DockerEngine(Tool: Tool) extends DockerEnvironment {

  def main(args: Array[String]): Unit = {
    spec.flatMap { implicit spec =>
      config.flatMap { case maybeConfig =>
        //search for our config
        val maybePatterns = maybeConfig.flatMap(_.tools.collectFirst { case config if config.name == spec.name => config.patterns })
        val maybeFiles = maybeConfig.flatMap(_.files.map(_.map { case path =>
          sourcePath.resolve(path.value)
        }))

        Tool(
          path = sourcePath,
          conf = maybePatterns,
          files = maybeFiles
        )
      }
    } match {
      case Success(results) =>
        results.foreach { result =>
          println(Json.stringify(Json.toJson(result)))
        }

      case Failure(error) =>
        error.printStackTrace(Console.err)
        System.exit(1)
    }
  }
}
