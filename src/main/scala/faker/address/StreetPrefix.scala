package faker.address

import org.scalacheck.{Arbitrary, Gen}
import pureconfig.ConfigReader
import pureconfig.generic.semiauto._

import faker.ResourceLoader

final case class StreetPrefix private (value: String) extends AnyVal

object StreetPrefix {
  def streetPrefixes(implicit loader: ResourceLoader): Seq[StreetPrefix] =
    loader.loadKey[Seq[StreetPrefix]]("address.street-prefixes")
  implicit def streetPrefixArbitrary(implicit
      loader: ResourceLoader
  ): Arbitrary[StreetPrefix] =
    Arbitrary(Gen.oneOf(streetPrefixes))
  implicit val streetPrefixConfigReader: ConfigReader[StreetPrefix] =
    deriveReader
}
