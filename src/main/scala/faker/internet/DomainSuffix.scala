package faker.internet

import org.scalacheck.{Arbitrary, Gen}
import pureconfig.ConfigReader
import pureconfig.generic.semiauto._

import faker.ResourceLoader

final case class DomainSuffix private (value: String) extends AnyVal

object DomainSuffix {
  def domainSuffixes(implicit loader: ResourceLoader): Seq[DomainSuffix] =
    loader.loadKey[Seq[DomainSuffix]]("internet.domain-suffixes")

  implicit def domainSuffixArbitrary(implicit
      loader: ResourceLoader
  ): Arbitrary[DomainSuffix] =
    Arbitrary(Gen.oneOf(domainSuffixes))

  implicit val domainSuffixConfigReader: ConfigReader[DomainSuffix] =
    deriveReader
}
