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
  "com.codacy" %% "codacy-engine-scala-seed" % "2.7.8"
)

enablePlugins(JavaAppPackaging)

enablePlugins(DockerPlugin)

version in Docker := "1.0"

organization := "com.codacy"

val installAll =
  s"""echo "http://dl-cdn.alpinelinux.org/alpine/edge/main" >> /etc/apk/repositories &&
     |apk --no-cache add bash wget ca-certificates git &&
     |apk --no-cache add python &&
     |apk --no-cache add 'python3>3.6.1' &&
     |wget "https://bootstrap.pypa.io/get-pip.py" -O /dev/stdout | python &&
     |wget "https://bootstrap.pypa.io/get-pip.py" -O /dev/stdout | python3 &&
     |python -m pip install django==1.9.2 pylint-django==0.9.0 flask==0.10.1 pylint-flask==0.1 flask-wtf==0.12 --upgrade --ignore-installed --no-cache-dir &&
     |python3 -m pip install django==1.9.2 pylint-django==0.9.0 flask==0.10.1  pylint-flask==0.1 flask-wtf==0.12 --upgrade --ignore-installed --no-cache-dir &&
     |python -m pip install pylint-common==0.2.2 &&
     |python3 -m pip install pylint-common==0.2.2 &&
     |python -m pip install pylint-celery==0.3 &&
     |python3 -m pip install pylint-celery==0.3 &&
     |python -m pip install pylint==1.7.5 --upgrade --ignore-installed --no-cache-dir &&
     |python3 -m pip install pylint==1.7.5 --upgrade --ignore-installed --no-cache-dir &&
     |python -m pip uninstall -y pip &&
     |python3 -m pip uninstall -y pip &&
     |apk del wget ca-certificates git &&
     |rm -rf /tmp/* &&
     |rm -rf /var/cache/apk/*""".stripMargin.replaceAll(System.lineSeparator()," ")

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

dockerBaseImage := "develar/java"

dockerCommands := dockerCommands.value.flatMap {
  case cmd@Cmd("WORKDIR", _) => List(cmd,
    Cmd("RUN", installAll)
  )
  case cmd@(Cmd("ADD", "opt /opt")) => List(cmd,
    Cmd("RUN", "mv /opt/docker/docs /docs"),
    Cmd("RUN", s"adduser -u 2004 -D $dockerUser"),
    ExecCmd("RUN", Seq("chown", "-R", s"$dockerUser:$dockerGroup", "/docs"): _*)
  )
  case other => List(other)
}

//dockerBuildOptions ++= Seq("--squash")
