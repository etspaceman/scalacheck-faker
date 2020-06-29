name := "scalacheck-faker"

scalaVersion := "2.13.3"

crossScalaVersions ++= Seq("2.12.11", "2.11.12", "2.10.7")

libraryDependencies ++= Seq(
  "org.scalacheck" %% "scalacheck" % "1.14.3",
  "org.scalatest" %% "scalatest" % "3.2.0" % Test,
  "org.scalatestplus" %% "scalacheck-1-14" % "3.1.0.0" % Test
)
