import sbt._
import sbt.Keys._
import LibraryDependencies._
import scalafix.sbt.ScalafixPlugin.autoImport.scalafixDependencies

object ScalacheckFakerPlugin extends AutoPlugin {
  override def trigger = allRequirements

  override def buildSettings: Seq[Setting[_]] =
    Seq(
      organization := "faker",
      scalaVersion := "2.13.3",
      crossScalaVersions ++= Seq("2.12.11", "2.11.12"),
      scalafixDependencies += "com.github.liancheng" %% "organize-imports" % "0.3.1-RC3",
      semanticdbEnabled := true,
      semanticdbVersion := "4.3.16",
      scalacOptions ++= Seq(
        "-deprecation",
        "-unchecked",
        "-feature",
        "-encoding",
        "utf8",
        "-Ywarn-unused",
        "-Xfatal-warnings"
      )
    ) ++ addCommandAlias("cpl", ";+test:compile")

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
      })
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
