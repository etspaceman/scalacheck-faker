import sbt._

object LibraryDependencies {
  val ScalaCheck = "org.scalacheck" %% "scalacheck" % "1.18.1"
  val TypesafeConfig = "com.typesafe" % "config" % "1.4.3"
  val ApacheCommons = "org.apache.commons" % "commons-lang3" % "3.14.0"
  val KindProjector = "org.typelevel" % "kind-projector" % "0.13.3"
  val PureConfig = "com.github.pureconfig" %% "pureconfig-core" % "0.17.7"
  val ScalaParsingCombinators =
    "org.scala-lang.modules" %% "scala-parser-combinators" % "2.4.0"

  object Munit {
    val munitVersion = "1.0.0"
    val core = "org.scalameta" %% "munit" % munitVersion
    val scalacheck =
      "org.scalameta" %% "munit-scalacheck" % munitVersion
    val catsEffect =
      "org.typelevel" %% "munit-cats-effect" % "2.0.0"
    val scalacheckEffect =
      "org.typelevel" %% "scalacheck-effect-munit" % "2.0.0-M2"
  }

  object Weaver {
    val weaverVersion = "0.8.4"
    val cats = "com.disneystreaming" %% "weaver-cats" % weaverVersion
    val scalacheck =
      "com.disneystreaming" %% "weaver-scalacheck" % weaverVersion
  }
}
