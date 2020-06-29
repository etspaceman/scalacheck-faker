name := "scalacheck-faker"

version := "0.1"

scalaVersion := "2.13.3"

crossScalaVersions ++= Seq("2.12.11", "2.11.12", "2.10.7")

libraryDependencies ++= Seq(
  "org.scalacheck" %% "scalacheck" % "1.14.3"
)