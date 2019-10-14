import Dependencies._

name := "root3-example"

version := "0.1-snapshot"

//scalaVersion := "2.12.4"
scalaVersion := "2.11.12"

lazy val root = (project in file("."))

sources in(Compile, doc) := Seq.empty
publishArtifact in(Compile, packageDoc) := false

scalacOptions ++= Seq("-unchecked", "-deprecation")

javaOptions in Test := Seq("-Dlogger.resource=logback-test.xml")

libraryDependencies += "com.github.pathikrit" %% "better-files" % "2.17.1"