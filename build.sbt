import com.typesafe.sbt.packager.docker.{Cmd, ExecCmd}

name := """codacy-engine-pylint"""

version := "1.0-SNAPSHOT"

val languageVersion = "2.12.7"

scalaVersion := languageVersion

resolvers := Seq("Sonatype OSS Snapshots".at("https://oss.sonatype.org/content/repositories/releases")) ++ resolvers.value

libraryDependencies ++= Seq(
  "com.typesafe.play" %% "play-json" % "2.7.3",
  "com.codacy" %% "codacy-engine-scala-seed" % "3.0.9"
)

enablePlugins(JavaAppPackaging)

enablePlugins(DockerPlugin)

version in Docker := "1.0"

organization := "com.codacy"

val installAll =
  s"""apt-get -y update &&
      |apt-get install -y software-properties-common &&
      |add-apt-repository -y ppa:deadsnakes/ppa &&
      |apt-get -y update &&
      |apt-get install -y python2.7 python3.6 ca-certificates wget openjdk-8-jre-headless &&
      |wget "https://bootstrap.pypa.io/get-pip.py" -O /dev/stdout | python2.7 &&
      |wget "https://bootstrap.pypa.io/get-pip.py" -O /dev/stdout | python3.6 &&
      |python2.7 -m  pip install django==1.9.2 pylint-django==0.9.0 flask==0.10.1 pylint-flask==0.1 flask-wtf==0.12 --upgrade --ignore-installed --no-cache-dir &&
      |python3.6 -m pip install django==1.9.2 pylint-django==0.9.0 flask==0.10.1 pylint-flask==0.1 flask-wtf==0.12 --upgrade --ignore-installed --no-cache-dir &&
      |python2.7 -m  pip install pylint-common==0.2.2 &&
      |python3.6 -m pip install pylint-common==0.2.2 &&
      |python2.7 -m  pip install pylint-celery==0.3 &&
      |python3.6 -m pip install pylint-celery==0.3 &&
      |python2.7 -m  pip install SaltPyLint==2017.12.15 &&
      |python3.6 -m pip install SaltPyLint==2017.12.15 &&
      |python2.7 -m  pip install pylint==1.8.2 --upgrade --ignore-installed --no-cache-dir &&
      |python3.6 -m pip install pylint==1.8.2 --upgrade --ignore-installed --no-cache-dir &&
      |python2.7 -m  pip uninstall -y pip &&
      |python3.6 -m pip uninstall -y pip &&
      |apt-get clean &&
      |rm -rf /var/lib/apt/lists/* &&
      |rm -rf /root/.cache/pip &&
      |rm -rf /root/.pip/cache &&
      |rm -rf /var/lib/apt/lists/* &&
      |rm -rf /var/cache/apt &&
      |rm -rf /var/cache/oracle-jdk8-installer &&
      |rm -rf /tmp/*""".stripMargin.replaceAll(System.lineSeparator(), " ")

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

dockerBaseImage := "ubuntu:16.04"

dockerCommands := dockerCommands.value.flatMap {
  case cmd@Cmd("WORKDIR", _) => List(cmd,
    Cmd("RUN", installAll)
  )
  case cmd@(Cmd("ADD", "opt /opt")) => List(cmd,
    Cmd("RUN", "mv /opt/docker/docs /docs"),
    Cmd("RUN", "adduser --uid 2004 --disabled-password --gecos \"\" docker"),
    ExecCmd("RUN", Seq("chown", "-R", s"$dockerUser:$dockerGroup", "/docs"): _*)
  )
  case other => List(other)
}

//dockerBuildOptions ++= Seq("--squash")
