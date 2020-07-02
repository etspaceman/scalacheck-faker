import sbt._
import sbt.Keys._
import LibraryDependencies._
import com.jsuereth.sbtpgp.PgpKeys
import com.typesafe.tools.mima.plugin.MimaKeys.mimaPreviousArtifacts
import sbtrelease.ReleasePlugin.autoImport._
import ReleaseTransformations._
import scalafix.sbt.ScalafixPlugin.autoImport.scalafixDependencies

object ScalacheckFakerPlugin extends AutoPlugin {
  override def trigger = allRequirements

  override def buildSettings: Seq[Setting[_]] =
    Seq(
      organization := "io.github.etspaceman",
      description := "Fake data generation using ScalaCheck Arbitrary instances",
      scalaVersion := "2.13.3",
      crossScalaVersions ++= Seq(scalaVersion.value, "2.12.11", "2.11.12"),
      scalafixDependencies += OrganizeImports,
      addCompilerPlugin(KindProjector cross CrossVersion.full),
      semanticdbEnabled := true,
      semanticdbVersion := "4.3.16",
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
    ) ++ addCommandAlias("cpl", ";+test:compile") ++
      addCommandAlias(
        "fixCheck",
        ";compile:scalafix --check ;test:scalafix --check"
      ) ++ addCommandAlias("fix", ";compile:scalafix ;test:scalafix") ++
      addCommandAlias("validate", ";+test;fixCheck;scalafmtCheckAll")

  override def projectSettings: Seq[Setting[_]] =
    Seq(
      libraryDependencies ++= Seq(
        ScalaCheck,
        TypesafeConfig,
        ApacheCommons,
        ScalaTest % Test,
        ScalaTestPlusScalaCheck % Test
      ),
      scalacOptions ++= (ScalaVersion.fromString(scalaVersion.value) match {
        case `2.11` => ScalacSettings.`2.11`
        case `2.12` => ScalacSettings.`2.12`
        case `2.13` => ScalacSettings.`2.13`
      }),
      mimaPreviousArtifacts := Set(),
      homepage := Some(url("https://github.com/etspaceman/scalacheck-faker")),
      licenses := Seq(
        "MIT" -> url("https://github.com/etspaceman/scalacheck-faker/LICENSE")
      ),
      scmInfo := Some(
        ScmInfo(
          url("https://github.com/etspaceman/scalacheck-faker"),
          "scm:git:git@github.com:etspaceman/scalacheck-faker.git"
        )
      ),
      developers := List(
        Developer(
          "etspaceman",
          "Eric Meisel",
          "eric.steven.meisel@gmail.com",
          url("https://github.com/etspaceman")
        )
      ),
      releaseTagName := s"${version.value}",
      releaseVcsSign := true,
      releaseCrossBuild := true,
      publishMavenStyle := true,
      publishArtifact in Test := false,
      pomIncludeRepository := Function.const(false),
      publishTo := {
        if (isSnapshot.value) Some(Opts.resolver.sonatypeSnapshots)
        else Some(Opts.resolver.sonatypeStaging)
      },
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
    )
}

sealed trait ScalaVersion extends Product with Serializable
object ScalaVersion {
  def fromString(str: String): ScalaVersion =
    CrossVersion.partialVersion(str) match {
      case Some((2, 11)) => `2.11`
      case Some((2, 12)) => `2.12`
      case Some((2, 13)) => `2.13`
      case _             => throw new RuntimeException("")
    }
}
case object `2.11` extends ScalaVersion
case object `2.12` extends ScalaVersion
case object `2.13` extends ScalaVersion
