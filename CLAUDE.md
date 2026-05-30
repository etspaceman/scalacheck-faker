# CLAUDE.md — scalacheck-faker

Fake data generation as ScalaCheck `Arbitrary`/`Gen` instances — a "Faker" for ScalaCheck, with 60+ locales. Published as `io.github.etspaceman::scalacheck-faker`. Single-module sbt build (JVM-only), cross-built for Scala 2.13 and 3.

## Toolchain
- **Java 17** via sdkman (`.sdkmanrc` → `17.0.19-amzn`); run `sdk env` first. CI uses Temurin 17. Library targets JDK 8 bytecode (`tlJdkRelease := Some(8)`).
- **sbt** 1.12.11 (`project/build.properties`). Built on sbt-typelevel (`tlBaseVersion := "9.0"`).
- **Scala**: `2.13.18` (default) and `3.3.7`. Cross-build with `+`.
- **Platforms**: JVM only. No Scala.js/Native despite `scala-2`/`scala-3`/`scala-2.13`/`scala-2.12` source dirs (those are Scala-*version* shims, not platforms).

## Commands
Compile (cross): `sbt cpl` (alias for `+Test / compile`)

Test (current Scala): `sbt test` — cross: `sbt +test`

Run the exact CI verification locally before pushing:
```
sbt githubWorkflowCheck '++ 2.13' headerCheckAll scalafmtCheckAll 'project /' scalafmtSbtCheck '++ 2.13' 'scalafixAll --check' '++ 2.13' test '++ 2.13' mimaReportBinaryIssues '++ 2.13' doc
```

Auto-fix everything (headers, scalafix, scalafmt, workflow gen): `sbt pretty`

Check-only equivalent: `sbt prettyCheck`

Individual fix/check aliases: `sbt fix` / `sbt fixCheck` / `sbt fmt` / `sbt fmtCheck` (all cross-build via `+`)

Coverage report: `sbt cov`

Docs site (Laika/typelevel-site): `sbt docs/tlSite` (output under `site/target/docs/site`)

Binary compat (MiMA): `sbt +mimaReportBinaryIssues`

CI workflows are generated — never hand-edit `.github/workflows/*.yml`. Run `sbt githubWorkflowGenerate` (included in `pretty`) and commit the result.

## Architecture / source layout
- `src/main/scala/faker/` — one file per category (`cat.scala`, `name.scala`, `address.scala`, `internet.scala`, …). Each category is an `object` containing newtype-wrapped fields. Pattern per field: an `object Foo extends Newtype[String]` with (a) a `def foos(implicit loader: ResourceLoader): Seq[Foo]` calling `loader.loadKey[Seq[Foo]]("category.key")`, (b) an `implicit Arbitrary[Foo]` = `Arbitrary(Gen.oneOf(foos))`, and (c) an `implicit ConfigReader[Foo]`.
- `Faker.scala` — convenience class wrapping a `Locale`; exposes ergonomic `def catName()` etc. that materialize the Arbitrary instances. `ResourceLoader.scala` — loads/caches HOCON config; has named `ResourceLoader.<locale>` vals. `SupportedLocales.scala` — `Locale` constants + `all` list. `StringGenBuilder.scala`, `regexp/` (regex→Gen parser via scala-parser-combinators), `states/`, `syntax/` (Gen/string extension methods).
- `Newtype` is cross-version: `src/main/scala-2/faker/Newtype.scala` and `src/main/scala-3/faker/Newtype.scala`. Other version dirs (`scala-2.12`, `scala-2.13`, `scala-3/.../compat`) hold `LazyListCompat` shims.
- `src/test/scala/faker/` — one `*Spec.scala` per category extending `FakerSpec`; use the `doTest[A,B](...)` helper. munit + weaver + scalacheck.

## Faker resource data (locales)
- HOCON `.conf` files under `src/main/resources/`. Top-level `<locale>.conf` (e.g. `en.conf`, `en_US.conf`, `default.conf`) `include` per-category files from a matching subdir (e.g. `default/cat.conf`).
- **Fallback chain** (in `ResourceLoader`): `<lang>_<COUNTRY>.conf` → `<lang>.conf` → `default.conf`. `default/*.conf` files mostly reference `${dummy.string}` placeholders; real data lives in language/locale dirs.
- Keys are dotted (`cat.names`, `cat.breeds`) and looked up via `loader.loadKey[...]("...")`; results are cached per loader.

## Adding a new faker field/category
1. Add the newtype object + `Arbitrary` + `ConfigReader` in the relevant `src/main/scala/faker/<category>.scala` (mirror `cat.scala`).
2. Add the HOCON key under `src/main/resources/default/<category>.conf` and any locale overrides; ensure `default.conf` (and locale `.conf`s) `include` it.
3. Add a convenience method on `Faker.scala` if exposing an ergonomic accessor.
4. Add a `*Spec` using `doTest`. New public API may need a MiMA filter — check `sbt +mimaReportBinaryIssues`.

## Code style (enforced in CI)
- **scalafmt** (`.scalafmt.conf`): `scala213Source3` dialect; rewrite rules RedundantBraces/RedundantParens/PreferCurlyFors/Imports; import groups ordered `scala.*`, `java.*`, others, then `faker.*`. SBT/`project/**` files use scala212 dialect.
- **scalafix** (`.scalafix.conf`): `DisableSyntax` + `NoValInForComprehension`. Bans `var`, `throw`, `null`, `return`, `while`, `asInstanceOf`/`isInstanceOf` (opt out with `@SuppressWarnings(Array("scalafix:DisableSyntax.asInstanceOf"))`, as `ResourceLoader.loadKey` does), `finalize`, val-patterns, `final val`, XML.
- **License headers** enforced (`headerCheckAll`); `sbt pretty` / `headerCreateAll` adds them.
- **`.git-blame-ignore-revs`** lists bulk scalafmt-reformat commits — keep adding mass-format commits there so `git blame` stays clean.

## Gotchas
- Hebrew locale: JVM rewrites `he` → `iw`; `ResourceLoader` special-cases this back to `he`.
- `default` locale = `Locale.getDefault()` (machine-dependent) — tests can be locale-sensitive.
- Mergify auto-squash-merges Scala Steward PRs from `etspaceman-scala-steward-app[bot]`.
- Always run `sbt pretty` before committing; CI fails on header/fmt/fix/MiMA, all cross-built.
