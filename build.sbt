import com.typesafe.sbt.packager.docker._

resolvers += "Typesafe Repo" at "http://repo.typesafe.com/typesafe/releases/"

name := """codacy-engine-pylint"""

version := "1.0-SNAPSHOT"

val languageVersion = "2.11.7"

scalaVersion := languageVersion

libraryDependencies ++= Seq(
  "com.typesafe.play" %% "play-json" % "2.3.8" withSources(),
  "org.scala-lang.modules" %% "scala-xml" % "1.0.4" withSources()
)

enablePlugins(JavaAppPackaging)

enablePlugins(DockerPlugin)

version in Docker := "1.0"

val installAll =
  s"""apk update && apk add bash curl && pip install pylint==1.4.1 --upgrade --ignore-installed --no-cache-dir""".stripMargin.replaceAll(System.lineSeparator()," ")

mappings in Universal <++= (resourceDirectory in Compile) map { (resourceDir: File) =>
  val src = resourceDir / "docs"
  val dest = "/docs"

  for {
      path <- (src ***).get
      if !path.isDirectory
    } yield path -> path.toString.replaceFirst(src.toString, dest)
}

daemonUser in Docker := "docker"

dockerBaseImage := "frolvlad/alpine-python2"

dockerCommands := dockerCommands.value.take(3) ++
  List(Cmd("RUN", installAll), Cmd("RUN", "mv /opt/docker/docs /docs")) ++
  List(Cmd("RUN", "adduser -u 2004 -D docker")) ++
  dockerCommands.value.drop(3)
