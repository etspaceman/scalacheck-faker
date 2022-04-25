import sbt._

object LibraryDependencies {
  val ScalaCheck = "org.scalacheck" %% "scalacheck" % "1.16.0"
  val TypesafeConfig = "com.typesafe" % "config" % "1.4.2"
  val ApacheCommons = "org.apache.commons" % "commons-lang3" % "3.12.0"
  val ScalaTest = "org.scalatest" %% "scalatest" % "3.2.12"
  val ScalaTestPlusScalaCheck =
    "org.scalatestplus" %% "scalacheck-1-14" % "3.2.2.0"
  val KindProjector = "org.typelevel" % "kind-projector" % "0.13.2"
  val OrganizeImports =
    "com.github.liancheng" %% "organize-imports" % "0.6.0"
  val PureConfig = "com.github.pureconfig" %% "pureconfig" % "0.17.1"
  val ScalaCheckGenRegexp =
    "io.github.wolfendale" %% "scalacheck-gen-regexp" % "0.1.3"
  val NewType = "io.estatico" %% "newtype" % "0.4.4"
}
