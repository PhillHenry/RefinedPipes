val MunitVersion = "0.7.20"

lazy val root = (project in file("."))
  .settings(
    organization := "uk.co.odinconsultants",
    name := "RefinedPipes",
    version := "0.0.1-SNAPSHOT",
    scalaVersion := "2.13.4",
    libraryDependencies ++= Seq(
      "org.scalameta"   %% "munit"                % MunitVersion           % Test,
      "eu.timepit"      %% "refined"              % "0.9.28",
      "org.scalameta"   %% "svm-subs"             % "20.2.0",
      "org.scalatest"   %% "scalatest"            % "3.0.9"                % Test

),
    addCompilerPlugin("org.typelevel" %% "kind-projector"     % "0.10.3"),
    addCompilerPlugin("com.olegpy"    %% "better-monadic-for" % "0.3.1"),
    testFrameworks += new TestFramework("munit.Framework")
  )

lazy val docs = project       // new documentation project
  .in(file("dox"))       // important: it must not be docs/
  .dependsOn(root)
  .enablePlugins(MdocPlugin)
  .settings(
    mdocIn := file("mdocs"),
    scalaVersion := "2.13.4",
  )