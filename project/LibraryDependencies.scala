import sbt._

object LibraryDependencies {
  val ScalaCheck = "org.scalacheck" %% "scalacheck" % "1.19.0"
  val TypesafeConfig = "com.typesafe" % "config" % "1.4.5"
  val ApacheCommons = "org.apache.commons" % "commons-lang3" % "3.18.0"
  val KindProjector = "org.typelevel" % "kind-projector" % "0.13.4"
  val PureConfig = "com.github.pureconfig" %% "pureconfig-core" % "0.17.9"
  val ScalaParsingCombinators =
    "org.scala-lang.modules" %% "scala-parser-combinators" % "2.4.0"

  object Munit {
    val core = "org.scalameta" %% "munit" % "1.2.0"
    val scalacheck =
      "org.scalameta" %% "munit-scalacheck" % "1.2.0"
    val catsEffect =
      "org.typelevel" %% "munit-cats-effect" % "2.1.0"
    val scalacheckEffect =
      "org.typelevel" %% "scalacheck-effect-munit" % "2.0.0-M2"
  }

  object Weaver {
    val weaverVersion = "0.9.2"
    val cats = "org.typelevel" %% "weaver-cats" % weaverVersion
    val scalacheck =
      "org.typelevel" %% "weaver-scalacheck" % weaverVersion
  }
}
