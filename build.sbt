import LibraryDependencies._
import sbtrelease.ReleaseStateTransformations._

organization := "io.github.etspaceman"
description := "Fake data generation using ScalaCheck Arbitrary instances"
scalaVersion := "2.13.3"
crossScalaVersions ++= Seq(scalaVersion.value, "2.12.11", "2.11.12")
ThisBuild / scalafixDependencies += OrganizeImports
addCompilerPlugin(KindProjector cross CrossVersion.full)
semanticdbEnabled := true
semanticdbVersion := "4.3.16"
credentials ++= (
  for {
    username <- Option(System.getenv().get("SONATYPE_USERNAME"))
    password <- Option(System.getenv().get("SONATYPE_PASSWORD"))
  } yield Credentials(
    "Sonatype Nexus Repository Manager",
    "oss.sonatype.org",
    username,
    password
  )
).toSeq
libraryDependencies ++= Seq(
  ScalaCheck,
  TypesafeConfig,
  PureConfig,
  ApacheCommons,
  ScalaTest % Test,
  ScalaTestPlusScalaCheck % Test
)
scalacOptions ++= (ScalaVersionADT.fromString(scalaVersion.value) match {
  case `2.11` => ScalacSettings.`2.11`
  case `2.12` => ScalacSettings.`2.12`
  case `2.13` => ScalacSettings.`2.13`
})
val mimaVersion: Option[String] = Some("2.0.0")
mimaPreviousArtifacts :=
  mimaVersion.map("io.github.etspaceman" %% "scalacheck-faker" % _).toSet
initialCommands in console := "import faker._"
homepage := Some(url("https://github.com/etspaceman/scalacheck-faker"))
licenses := Seq(
  "MIT" -> url("https://github.com/etspaceman/scalacheck-faker/LICENSE")
)
scmInfo := Some(
  ScmInfo(
    url("https://github.com/etspaceman/scalacheck-faker"),
    "scm:git:git@github.com:etspaceman/scalacheck-faker.git"
  )
)
developers := List(
  Developer(
    "etspaceman",
    "Eric Meisel",
    "eric.steven.meisel@gmail.com",
    url("https://github.com/etspaceman")
  )
)
releaseTagName := s"${version.value}"
releaseVcsSign := true
releasePublishArtifactsAction := PgpKeys.publishSigned.value
releaseCrossBuild := true
publishMavenStyle := true
publishArtifact in Test := false
pomIncludeRepository := Function.const(false)
publishTo := {
  if (isSnapshot.value) Some(Opts.resolver.sonatypeSnapshots)
  else Some(Opts.resolver.sonatypeStaging)
}
releaseProcess := Seq[ReleaseStep](
  checkSnapshotDependencies,
  inquireVersions,
  runClean,
  runTest,
  setReleaseVersion,
  commitReleaseVersion,
  tagRelease,
  releaseStepCommandAndRemaining("+publishSigned"),
  setNextVersion,
  commitNextVersion,
  releaseStepCommand("sonatypeReleaseAll"),
  pushChanges
)

scalacOptions in (Compile, console) ~= {
  _.filterNot(Set("-Ywarn-unused-import", "-Ywarn-unused:imports"))
}

addCommandAlias("cpl", ";+test:compile")
addCommandAlias(
  "fixCheck",
  ";compile:scalafix --check ;test:scalafix --check"
)
addCommandAlias("fix", ";compile:scalafix ;test:scalafix")
addCommandAlias(
  "cov",
  ";clean;coverage;+test;coverageReport"
)
addCommandAlias(
  "validate",
  ";cov;prettyCheck;mimaReportBinaryIssues"
)
addCommandAlias(
  "fmt",
  ";scalafmtAll;scalafmtSbt"
)
addCommandAlias(
  "fmtCheck",
  ";scalafmtCheckAll;scalafmtSbtCheck"
)
addCommandAlias(
  "pretty",
  ";fix;fmt"
)
addCommandAlias(
  "prettyCheck",
  ";fixCheck;fmtCheck"
)
