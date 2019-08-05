import com.typesafe.sbt.packager.docker.{Cmd, ExecCmd}

name := "codacy-engine-pylint"

scalaVersion := "2.12.8"

resolvers := Seq("Sonatype OSS Snapshots".at(
  "https://oss.sonatype.org/content/repositories/releases")) ++ resolvers.value

libraryDependencies ++= Seq(
  "com.codacy" %% "codacy-engine-scala-seed" % "3.0.296"
)

enablePlugins(JavaAppPackaging)

enablePlugins(DockerPlugin)

version in Docker := "1.0"

organization := "com.codacy"

val python2 = "2.7"
val python3 = "3.7"

val pylintPython2Version = "1.9.5"
val pylintPython3Version = "2.3.1"

val djangoPython2Version = "1.11.22"
val djangoPython3Version = "2.2.3"

val pylintDjangoPython2Version = "0.11.1"
val pylintDjangoPython3Version = "2.0.11"

val flaskVersion = "1.1.1"

val pylintFlaskVersion = "0.6"

val flaskWtfVersion = "0.14.2"

val pylintCommonVersion = "0.2.5"

val pylintCeleryVersion = "0.3"

val saltPyLintVersion = "2019.6.7"

val installAll =
  s"""apt-get -y update &&
      |apt-get install -y software-properties-common &&
      |add-apt-repository -y ppa:deadsnakes/ppa &&
      |apt-get -y update &&
      |apt-get install -y $python2 $python3 ca-certificates wget openjdk-8-jre-headless &&
      |wget "https://bootstrap.pypa.io/get-pip.py" -O /dev/stdout | $python2 &&
      |wget "https://bootstrap.pypa.io/get-pip.py" -O /dev/stdout | $python3 &&
      |$python2 -m pip install django==$djangoPython2Version pylint-django==$pylintDjangoPython2Version flask==$flaskVersion pylint-flask==$pylintFlaskVersion flask-wtf==$flaskWtfVersion --upgrade --ignore-installed --no-cache-dir &&
      |$python3 -m pip install django==$djangoPython3Version pylint-django==$pylintDjangoPython3Version flask==$flaskVersion pylint-flask==$pylintFlaskVersion flask-wtf==$flaskWtfVersion --upgrade --ignore-installed --no-cache-dir &&
      |$python2 -m pip install pylint-common==$pylintCommonVersion &&
      |$python3 -m pip install pylint-common==$pylintCommonVersion &&
      |$python2 -m pip install pylint-celery==$pylintCeleryVersion &&
      |$python3 -m pip install pylint-celery==$pylintCeleryVersion &&
      |$python2 -m pip install SaltPyLint==$saltPyLintVersion &&
      |$python3 -m pip install SaltPyLint==$saltPyLintVersion &&
      |$python2 -m pip install pylint==$pylintPython2Version --upgrade --ignore-installed --no-cache-dir &&
      |$python3 -m pip install pylint==$pylintPython3Version --upgrade --ignore-installed --no-cache-dir &&
      |$python2 -m pip uninstall -y pip &&
      |$python3 -m pip uninstall -y pip &&
      |apt-get clean &&
      |rm -rf /var/lib/apt/lists/* &&
      |rm -rf /root/.cache/pip &&
      |rm -rf /root/.pip/cache &&
      |rm -rf /var/lib/apt/lists/* &&
      |rm -rf /var/cache/apt &&
      |rm -rf /var/cache/oracle-jdk8-installer &&
      |rm -rf /tmp/*""".stripMargin.replaceAll(System.lineSeparator(), " ")

mappings in Universal ++= {
  (resourceDirectory in Compile) map { resourceDir: File =>
    val src = resourceDir / "docs"
    val dest = "/docs"

    for {
      path <- src.allPaths.get
      if !path.isDirectory
    } yield path -> path.toString.replaceFirst(src.toString, dest)
  }
}.value

val dockerUser = "docker"
val dockerGroup = "docker"

daemonUser in Docker := dockerUser

daemonGroup in Docker := dockerGroup

dockerBaseImage := "ubuntu:18.04"

dockerCommands := dockerCommands.value.flatMap {
  case cmd @ Cmd("WORKDIR", _) => Seq(cmd, Cmd("RUN", installAll))
  case cmd @ Cmd("ADD", "opt /opt") =>
    Seq(
      cmd,
      Cmd("RUN", "mv /opt/docker/docs /docs"),
      Cmd("RUN", "adduser --uid 2004 --disabled-password --gecos \"\" docker"),
      ExecCmd("RUN",
              Seq("chown", "-R", s"$dockerUser:$dockerGroup", "/docs"): _*)
    )
  case other => Seq(other)
}

lazy val `doc-generator` = project
  .settings(
    libraryDependencies ++= Seq(
      "org.scala-lang.modules" %%  "scala-xml" % "1.2.0",
      "com.lihaoyi" %% "ujson" % "0.7.5"),
    scalaVersion := "2.13.0",
    Compile / fork := true
  )
