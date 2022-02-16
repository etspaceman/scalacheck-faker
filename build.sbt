import LibraryDependencies._
import sbtrelease.ReleaseStateTransformations._

organization := "io.github.etspaceman"
description := "Fake data generation using ScalaCheck Arbitrary instances"
scalaVersion := "2.13.8"
crossScalaVersions ++= Seq(scalaVersion.value, "2.12.15")
ThisBuild / scalafixDependencies += OrganizeImports
addCompilerPlugin(KindProjector cross CrossVersion.full)
semanticdbEnabled := true
semanticdbVersion := scalafixSemanticdb.revision
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
  ScalaCheckGenRegexp,
  NewType,
  ScalaTest % Test,
  ScalaTestPlusScalaCheck % Test
) ++ (if (ScalaVersionADT.fromString(scalaVersion.value) == `2.13`) Seq.empty
      else
        Seq(
          compilerPlugin(
            "org.scalamacros" % "paradise" % "2.1.1" cross CrossVersion.full
          )
        ))
scalacOptions ++= (ScalaVersionADT.fromString(scalaVersion.value) match {
  case `2.12` => ScalacSettings.`2.12`
  case `2.13` => ScalacSettings.`2.13`
})
val mimaVersion: Option[String] = None
mimaPreviousArtifacts :=
  mimaVersion.map("io.github.etspaceman" %% "scalacheck-faker" % _).toSet
console / initialCommands :=
  """import faker._
    |import faker.syntax.string._
    |import faker.syntax.scalacheck._
    |""".stripMargin
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
Test / publishArtifact := false
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

Compile / console / scalacOptions ~= {
  _.filterNot(Set("-Ywarn-unused-import", "-Ywarn-unused:imports"))
}

Test / fork := true
testForkedParallel := true

addCommandAlias("cpl", ";+Test / compile")
addCommandAlias(
  "fixCheck",
  ";Compile / scalafix --check ;Test / scalafix --check"
)
addCommandAlias("fix", ";Compile / scalafix ;Test / scalafix")
addCommandAlias(
  "cov",
  ";clean;coverage;test;coverageReport"
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
