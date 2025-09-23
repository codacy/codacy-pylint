lazy val `doc-generator` = project
  .settings(
    libraryDependencies ++= Seq(
      "org.scala-lang.modules" %% "scala-xml" % "1.2.0",
      "com.github.pathikrit" %% "better-files" % "3.9.2",
      "com.lihaoyi" %% "ujson" % "4.3.2"
    ),
    scalaVersion := "2.13.16",
    Compile / fork := true
  )
