import sbt._

object Dependencies {
	val logger = Seq(
			"ch.qos.logback" % "logback-classic" % "1.2.3",
            "com.typesafe.scala-logging" %% "scala-logging" % "3.9.2"
	)
    val scalaz = Seq(
            "org.scalaz" %% "scalaz-core" % "7.2.28",
    )

    val zio = Seq(
            "dev.zio" %% "zio" % "1.0.5"
    )

    val better_files = "com.github.pathikrit" %% "better-files" % "3.9.1"

    val scalameta = "org.scalameta" %% "scalameta" % "4.4.11"

    val specs2 = "org.specs2" %% "specs2-core" % "4.10.0"
    val scalatest = "org.scalatest" %% "scalatest" % "3.2.5"

    val tests = Seq(
            "org.scalactic" %% "scalactic" % "3.2.5"
    )
}
