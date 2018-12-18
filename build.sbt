import Dependencies._

lazy val root = (project in file(".")).
  settings(
    inThisBuild(List(
      organization := "im.plmnt",
      scalaVersion := "2.12.7",
      version      := "0.1.0"
    )),
    name := "pet-fs2-tf-crud",
    libraryDependencies := List(
      catsCore,
      catsEffect,
      http4s,
      http4sDsl,
      http4sBlazeServer,
      scalaTest % Test
    ),
    scalacOptions := List(
      "-Ypartial-unification",
      "-feature",
      "-language:higherKinds"
    ),
    fork in run := true,
    cancelable in Global := true
  )
