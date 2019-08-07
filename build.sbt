import com.typesafe.sbt.packager.docker.Cmd

name := "codacy-pylint"

scalaVersion := "2.12.8"

resolvers := Seq("Sonatype OSS Snapshots".at("https://oss.sonatype.org/content/repositories/releases")) ++ resolvers.value

libraryDependencies ++= Seq("com.codacy" %% "codacy-engine-scala-seed" % "3.0.296")

lazy val `doc-generator` = project
  .settings(
    libraryDependencies ++= Seq("org.scala-lang.modules" %% "scala-xml" % "1.2.0", "com.lihaoyi" %% "ujson" % "0.7.5"),
    scalaVersion := "2.13.0",
    Compile / fork := true,
    scalacOptions := scalacOptions.value.filterNot(o => o.startsWith("-Ywarn"))
  )

enablePlugins(JavaAppPackaging)

enablePlugins(DockerPlugin)

organization := "com.codacy"

val python3 = "python3.7"

val pylintVersion = "2.3.1"

val djangoVersion = "2.2.3"

val pylintDjangoVersion = "2.0.11"

val flaskVersion = "1.1.1"

val pylintFlaskVersion = "0.6"

val flaskWtfVersion = "0.14.2"

val pylintCommonVersion = "0.2.5"

val pylintCeleryVersion = "0.3"

val saltPyLintVersion = "2019.6.7"

val installAll =
  s"""apt-get -y update &&
     |apt-get install -y $python3 python3-pip openjdk-8-jre-headless &&
     |pip3 install django==$djangoVersion pylint-django==$pylintDjangoVersion flask==$flaskVersion pylint-flask==$pylintFlaskVersion 
     |flask-wtf==$flaskWtfVersion --upgrade --ignore-installed --no-cache-dir &&
     |pip3 install pylint-common==$pylintCommonVersion &&
     |pip3 install pylint-celery==$pylintCeleryVersion &&
     |pip3 install SaltPyLint==$saltPyLintVersion &&
     |pip3 install pylint==$pylintVersion --upgrade --ignore-installed --no-cache-dir &&
     |apt-get purge -y python3-pip &&
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

dockerEntrypoint := Seq(s"/opt/docker/bin/${name.value}")

dockerCommands := dockerCommands.value.flatMap {
  case Cmd("WORKDIR", _) => Seq(Cmd("WORKDIR", "/src"))
  case cmd @ Cmd("ADD", _) =>
    Seq(
      Cmd("RUN", installAll),
      Cmd("RUN", "adduser -u 2004 docker"),
      cmd,
      Cmd("RUN", "mv /opt/docker/docs /docs"),
      Cmd("RUN", s"chown -R $dockerUser:$dockerGroup /docs")
    )
  case other => Seq(other)
}
