import Dependencies._

name := "docGenerator"

version := "0.1-snapshot"

//scalaVersion := "2.12.4"
scalaVersion := "2.11.12"

scapegoatVersion in ThisBuild := "1.3.8"

lazy val root = (project in file(".")).
  configs(IntegrationTest).
  settings(
    scalafmtOnCompile := true,
    Defaults.itSettings,
    libraryDependencies += scalatest % "it,test"
  )

scalacOptions ++= Seq(
  "-Ypartial-unification",
  "-deprecation", // Emit warning and location for usages of deprecated APIs.
  "-feature", // Emit warning and location for usages of features that should be imported explicitly.
  "-unchecked", // Enable additional warnings where generated code depends on assumptions.
  //"-Xfatal-warnings", // Fail the compilation if there are any warnings.
  //"-Xlint", // Enable recommended additional warnings.
  "-Ywarn-adapted-args", // Warn if an argument list is modified to match the receiver.
  "-Ywarn-dead-code", // Warn when dead code is identified.
  "-Ywarn-inaccessible", // Warn about inaccessible types in method signatures.
  "-Ywarn-nullary-override", // Warn when non-nullary overrides nullary, e.g. def foo() over def foo.
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

logBuffered in Test := false

fork in Test := true
fork in IntegrationTest := true
