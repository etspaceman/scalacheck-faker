package faker.internet

import faker.ResourceLoader
import org.scalacheck.{Arbitrary, Gen}

final case class DomainSuffix private (value: String) extends AnyVal

object DomainSuffix {
  private val domainSuffixes: Seq[String] =
    ResourceLoader.loadStringList("internet.domain-suffixes")
  implicit val domainSuffixArbitrary: Arbitrary[DomainSuffix] = Arbitrary(
    Gen.oneOf(domainSuffixes).map(DomainSuffix.apply)
  )
}
