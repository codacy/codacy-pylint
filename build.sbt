import com.typesafe.sbt.packager.docker.{Cmd, ExecCmd}

name := """codacy-engine-pylint"""

version := "1.0-SNAPSHOT"

val languageVersion = "2.11.7"

scalaVersion := languageVersion

resolvers ++= Seq(
  "Typesafe Repo" at "http://repo.typesafe.com/typesafe/releases/",
  "Sonatype OSS Snapshots" at "https://oss.sonatype.org/content/repositories/releases"
)

libraryDependencies ++= Seq(
  "com.typesafe.play" %% "play-json" % "2.3.8",
  "com.codacy" %% "codacy-engine-scala-seed" % "2.7.1"
)

enablePlugins(JavaAppPackaging)

enablePlugins(DockerPlugin)

version in Docker := "1.0"

organization := "com.codacy"

val installAll =
  s"""apk update && apk add bash curl &&
     |apk add --update python &&
     |apk add --update python3 &&
     |apk add wget ca-certificates &&
     |apk add git &&
     |wget "https://bootstrap.pypa.io/get-pip.py" -O /dev/stdout | python &&
     |wget "https://bootstrap.pypa.io/get-pip.py" -O /dev/stdout | python3 &&
     |apk del wget ca-certificates &&
     |rm /var/cache/apk/* &&
     |python -m pip install django==1.9.2 flask==0.10.1 pylint-flask==0.1 flask-wtf==0.12 &&
     |python3 -m pip install django==1.9.2 flask==0.10.1  pylint-flask==0.1 flask-wtf==0.12 &&
     |python -m pip install git+https://github.com/landscapeio/pylint-django@93fd04120d0690189c35b7b2eaace23117f388c5 &&
     |python3 -m pip install git+https://github.com/landscapeio/pylint-django@93fd04120d0690189c35b7b2eaace23117f388c5 &&
     |python -m pip install pylint==1.5.4 --upgrade --ignore-installed --no-cache-dir &&
     |python3 -m pip install pylint==1.5.4 --upgrade --ignore-installed --no-cache-dir""".stripMargin.replaceAll(System.lineSeparator()," ")

mappings in Universal <++= (resourceDirectory in Compile) map { (resourceDir: File) =>
  val src = resourceDir / "docs"
  val dest = "/docs"

  for {
    path <- (src ***).get
    if !path.isDirectory
  } yield path -> path.toString.replaceFirst(src.toString, dest)
}

val dockerUser = "docker"
val dockerGroup = "docker"

daemonUser in Docker := dockerUser

daemonGroup in Docker := dockerGroup

dockerBaseImage := "frolvlad/alpine-oraclejdk8"

dockerCommands := dockerCommands.value.flatMap {
  case cmd@Cmd("WORKDIR", _) => List(cmd,
    Cmd("RUN", installAll)
  )
  case cmd@(Cmd("ADD", "opt /opt")) => List(cmd,
    Cmd("RUN", "mv /opt/docker/docs /docs"),
    Cmd("RUN", "adduser -u 2004 -D docker"),
    ExecCmd("RUN", Seq("chown", "-R", s"$dockerUser:$dockerGroup", "/docs"): _*)
  )
  case other => List(other)
}
