import Dependencies._

name := "docGenerator"

version := "0.1-snapshot"

//scalaVersion := "2.12.13"
scalaVersion := "2.13.5"

lazy val root = (project in file(".")).
  configs(IntegrationTest).
  settings(
    scalafmtOnCompile := true,
    Defaults.itSettings,
    libraryDependencies += scalatest % "it,test"
  )

scalacOptions in Test ++= Seq("-Yrangepos")

scalacOptions ++= Seq(
  "-deprecation", // Emit warning and location for usages of deprecated APIs.
  "-feature", // Emit warning and location for usages of features that should be imported explicitly.
  "-unchecked", // Enable additional warnings where generated code depends on assumptions.
  //"-Xfatal-warnings", // Fail the compilation if there are any warnings.
  //"-Xlint", // Enable recommended additional warnings.
  "-Ywarn-dead-code", // Warn when dead code is identified.
  "-Ywarn-numeric-widen", // Warn when numerics are widened.
  //"-Xlog-implicit-conversions",
)

sources in(Compile, doc) := Seq.empty
publishArtifact in(Compile, packageDoc) := false

scalacOptions ++= Seq("-unchecked", "-deprecation")

javaOptions in Test := Seq("-Dlogger.resource=logback-test.xml")

libraryDependencies ++= 
  scalaz ++ 
  tests ++
  logger ++
  Seq(
    scalameta,
    better_files
  )
libraryDependencies += specs2 % "test"

logBuffered in Test := false

fork in Test := true
fork in IntegrationTest := true
