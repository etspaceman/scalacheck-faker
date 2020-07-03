package faker.name

import org.scalacheck.{Arbitrary, Gen}

import faker.ResourceLoader

final case class Suffix private (value: String) extends AnyVal

object Suffix {
  private val suffixes: Seq[String] =
    ResourceLoader.default.loadStringList("name.suffixes")
  implicit val suffixArbitrary: Arbitrary[Suffix] = Arbitrary(
    Gen.oneOf(suffixes).map(Suffix.apply)
  )
}
