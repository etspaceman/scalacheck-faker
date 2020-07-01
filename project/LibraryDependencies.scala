import sbt._

object LibraryDependencies {
  val ScalaCheck = "org.scalacheck" %% "scalacheck" % "1.14.3"
  val TypesafeConfig = "com.typesafe" % "config" % "1.4.0"
  val ApacheCommons = "org.apache.commons" % "commons-lang3" % "3.10"
  val ScalaTest = "org.scalatest" %% "scalatest" % "3.2.0"
  val ScalaTestPlusScalaCheck =
    "org.scalatestplus" %% "scalacheck-1-14" % "3.1.0.0"
}
