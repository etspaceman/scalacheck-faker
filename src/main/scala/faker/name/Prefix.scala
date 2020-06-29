package faker.name

import faker.ResourceLoader
import org.scalacheck.{Arbitrary, Gen}

final case class Prefix private (value: String) extends AnyVal

object Prefix {
  private val prefixes: Seq[String] = ResourceLoader.loadLines("prefixes.txt")
  implicit val prefixArbitrary: Arbitrary[Prefix] = Arbitrary(Gen.oneOf(prefixes).map(Prefix.apply))
}
