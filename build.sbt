import LibraryDependencies._
import laika.config._
import laika.helium.Helium
import laika.helium.config.TextLink
import laika.helium.config.ThemeNavigationSection

val Scala212 = "2.12.19"
val Scala213 = "2.13.12"
val Scala3 = "3.3.1"

val allScalaVersions = List(Scala213, Scala3, Scala212)

inThisBuild(
  Seq(
    tlVersionIntroduced := Map(
      "2.13" -> "8.0.0",
      "2.12" -> "8.0.0",
      "3" -> "8.0.0"
    ),
    tlBaseVersion := "8.0",
    organization := "io.github.etspaceman",
    organizationName := "etspaceman",
    description := "Fake data generation using ScalaCheck Arbitrary instances",
    startYear := Some(2020),
    scalaVersion := Scala213,
    crossScalaVersions := allScalaVersions,
    semanticdbEnabled := true,
    semanticdbVersion := scalafixSemanticdb.revision,
    licenses := Seq(License.MIT),
    developers := List(tlGitHubDev("etspaceman", "Eric Meisel")),
    tlCiHeaderCheck := true,
    tlCiScalafmtCheck := true,
    tlCiScalafixCheck := true,
    githubWorkflowJavaVersions := Seq(
      JavaSpec.temurin("17")
    ),
    tlCiReleaseBranches := Seq("main"),
    tlSonatypeUseLegacyHost := true,
    mergifyStewardConfig := Some(
      MergifyStewardConfig(
        action = MergifyAction.Merge(method = Some("squash")),
        author = "etspaceman-scala-steward-app[bot]"
      )
    ),
    scmInfo := Some(
      ScmInfo(
        url(
          s"https://github.com/etspaceman/scalacheck-faker"
        ),
        s"git@github.com:etspaceman/scalacheck-faker.git"
      )
    ),
    homepage := Some(
      url("https://github.com/etspaceman/scalacheck-faker")
    )
  )
)

lazy val `scalacheck-faker` = project
  .in(file("."))
  .settings(
    libraryDependencies ++= Seq(
      ScalaCheck,
      TypesafeConfig,
      PureConfig,
      ApacheCommons,
      ScalaParsingCombinators,
      Munit.core % Test,
      Munit.scalacheck % Test,
      Munit.catsEffect % Test,
      Munit.scalacheckEffect % Test,
      Weaver.cats % Test,
      Weaver.scalacheck % Test
    ),
    Test / fork := true,
    tlJdkRelease := Some(8),
    Test / testOptions ++= {
      List(Tests.Argument(TestFrameworks.MUnit, "+l"))
    },
    scalacOptions ++= {
      if (scalaVersion.value.startsWith("2.13")) Seq("-Xlint:-byname-implicit")
      else Nil
    }
  )
  .settings(
    addCommandAlias(
      "cpl",
      ";+Test / compile"
    ),
    addCommandAlias(
      "fixCheck",
      ";+Compile / scalafix --check;+Test / scalafix --check"
    ),
    addCommandAlias(
      "fix",
      ";+Compile / scalafix;+Test / scalafix"
    ),
    addCommandAlias(
      "fmtCheck",
      ";+Compile / scalafmtCheck;+Test / scalafmtCheck;scalafmtSbtCheck"
    ),
    addCommandAlias(
      "fmt",
      ";+Compile / scalafmt;+Test / scalafmt;scalafmtSbt"
    ),
    addCommandAlias(
      "pretty",
      ";githubWorkflowGenerate;headerCreateAll;fix;fmt"
    ),
    addCommandAlias(
      "prettyCheck",
      ";headerCheckAll;fixCheck;fmtCheck"
    ),
    addCommandAlias(
      "cov",
      ";clean;coverage;test;coverageReport;coverageOff"
    )
  )

lazy val docs = project
  .in(file("site"))
  .enablePlugins(TypelevelSitePlugin)
  .settings(
    tlSiteHelium := tlSiteHelium.value.site
      .mainNavigation(appendLinks =
        Seq(
          ThemeNavigationSection(
            "Other Faker Implementations",
            TextLink.external(
              "https://github.com/DiUS/java-faker",
              "java-faker"
            ),
            TextLink.external(
              "https://fakerjs.dev/guide/",
              "faker-js"
            ),
            TextLink.external(
              "https://faker.readthedocs.io/en/master/",
              "faker-py"
            ),
            TextLink.external(
              "https://github.com/faker-ruby/faker",
              "faker-ruby"
            )
          )
        )
      ),
    tlSiteApiPackage := Some("scalacheck-faker"),
    laikaConfig := LaikaConfig.defaults.withConfigValue(
      LinkConfig.empty.addSourceLinks(
        SourceLinks(
          baseUri = "https://github.com/etspaceman/scalacheck-faker/blob/main/",
          suffix = "scala"
        )
      )
    )
  )
  .dependsOn(`scalacheck-faker`)
