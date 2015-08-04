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
  s"""apk update && apk add bash curl &&
    |cd /tmp &&
    |curl -L -o pmd-bin-5.3.2.zip "http://sourceforge.net/projects/pmd/files/pmd/5.3.2/pmd-bin-5.3.2.zip/download" &&
    |unzip pmd-bin-5.3.2.zip &&
    |mv pmd-bin-5.3.2/ /usr/local/ &&
    |rm /tmp/pmd-bin-5.3.2.zip""".stripMargin.replaceAll(System.lineSeparator()," ")

mappings in Universal <++= (resourceDirectory in Compile) map { (resourceDir: File) =>
  val src = resourceDir / "docs"
  val dest = "/docs"

  for {
      path <- (src ***).get
      if !path.isDirectory
    } yield path -> path.toString.replaceFirst(src.toString, dest)
}

daemonUser in Docker := "docker"

dockerBaseImage := "frolvlad/alpine-oraclejdk8"

dockerCommands := dockerCommands.value.take(3) ++
  List(Cmd("RUN", installAll), Cmd("RUN", "mv /opt/docker/docs /docs")) ++
  List(Cmd("RUN", "adduser -u 2004 -D docker")) ++
  dockerCommands.value.drop(3)
