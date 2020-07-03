package faker.name

import org.scalacheck.{Arbitrary, Gen}

import faker.ResourceLoader

final case class Prefix private (value: String) extends AnyVal

object Prefix {
  private val prefixes: Seq[String] =
    ResourceLoader.default.loadStringList("name.prefixes")
  implicit val prefixArbitrary: Arbitrary[Prefix] = Arbitrary(
    Gen.oneOf(prefixes).map(Prefix.apply)
  )
}
