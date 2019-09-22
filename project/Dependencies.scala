import sbt._

object Dependencies {
	val logger = Seq(
			"ch.qos.logback" % "logback-classic" % "1.2.3",
            "com.typesafe.scala-logging" %% "scala-logging" % "3.9.2"
	)
    val scalaz = Seq(
            "org.scalaz" %% "scalaz-core" % "7.2.28",
    )

    val better_files = "com.github.pathikrit" %% "better-files" % "2.17.1"

    val scalameta = "org.scalameta" %% "scalameta" % "4.2.3"

    val scalatest = "org.scalatest" %% "scalatest" % "3.0.5"

    val tests = Seq(
            "org.scalactic" %% "scalactic" % "3.0.5"
    )
}
