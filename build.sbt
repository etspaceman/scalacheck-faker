name := "scalacheck-faker"

scalaVersion := "2.13.3"

crossScalaVersions ++= Seq("2.12.11", "2.11.12")

addCommandAlias("cpl", ";+test:compile")

scalafixDependencies += "com.github.liancheng" %% "organize-imports" % "0.3.1-RC3"
semanticdbEnabled := true
semanticdbVersion := "4.3.16"

scalacOptions ++= Seq(
  "-deprecation",
  "-unchecked",
  "-feature",
  "-encoding",
  "utf8",
  "-Ywarn-unused",
  "-Xfatal-warnings"
)

libraryDependencies ++= Seq(
  "org.scalacheck" %% "scalacheck" % "1.14.3",
  "com.typesafe" % "config" % "1.4.0",
  "org.apache.commons" % "commons-lang3" % "3.10",
  "org.scalatest" %% "scalatest" % "3.2.0" % Test,
  "org.scalatestplus" %% "scalacheck-1-14" % "3.1.0.0" % Test
)
