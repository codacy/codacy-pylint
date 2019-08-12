lazy val `doc-generator` = project
  .settings(
    libraryDependencies ++= Seq(
      "org.scala-lang.modules" %% "scala-xml" % "1.2.0",
      "com.lihaoyi" %% "ujson" % "0.7.5"),
    scalaVersion := "2.13.0",
    Compile / fork := true,
    scalacOptions := scalacOptions.value.filterNot(o => o.startsWith("-Ywarn"))
  )
