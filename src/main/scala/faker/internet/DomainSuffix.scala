package faker.internet

import org.scalacheck.{Arbitrary, Gen}

import faker.ResourceLoader

final case class DomainSuffix private (value: String) extends AnyVal

object DomainSuffix {
  def domainSuffixes(implicit loader: ResourceLoader): Seq[String] =
    loader.loadStringList("internet.domain-suffixes")

  implicit def domainSuffixArbitrary(implicit
      loader: ResourceLoader
  ): Arbitrary[DomainSuffix] =
    Arbitrary(Gen.oneOf(domainSuffixes.map(DomainSuffix.apply)))
}
