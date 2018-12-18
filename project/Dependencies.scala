import sbt._

object Dependencies {
  
  object Version {
    val http4s = "0.20.0-M4"
  }
  
  lazy val scalaTest = "org.scalatest" %% "scalatest" % "3.0.5"
  lazy val http4s = "org.http4s" %% "http4s-core" % Version.http4s
  lazy val http4sDsl = "org.http4s" %% "http4s-dsl" % Version.http4s
  lazy val http4sBlazeServer = "org.http4s" %% "http4s-blaze-server" % Version.http4s
  lazy val catsCore = "org.typelevel" %% "cats-core" % "1.5.0"
  lazy val catsEffect = "org.typelevel" %% "cats-effect" % "1.1.0"
}