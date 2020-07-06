import sbt._

object LibraryDependencies {
  val ScalaCheck = "org.scalacheck" %% "scalacheck" % "1.14.3"
  val TypesafeConfig = "com.typesafe" % "config" % "1.4.0"
  val ApacheCommons = "org.apache.commons" % "commons-lang3" % "3.10"
  val ScalaTest = "org.scalatest" %% "scalatest" % "3.2.0"
  val ScalaTestPlusScalaCheck =
    "org.scalatestplus" %% "scalacheck-1-14" % "3.1.0.0"
  val KindProjector = "org.typelevel" % "kind-projector" % "0.11.0"
  val OrganizeImports =
    "com.github.liancheng" %% "organize-imports" % "0.3.1-RC3"
  val PureConfig = "com.github.pureconfig" %% "pureconfig" % "0.13.0"
}
