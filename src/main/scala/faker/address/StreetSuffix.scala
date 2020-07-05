package faker.address

import org.scalacheck.{Arbitrary, Gen}
import pureconfig.ConfigReader
import pureconfig.generic.semiauto._

import faker.ResourceLoader

final case class StreetSuffix private (value: String) extends AnyVal

object StreetSuffix {
  def streetSuffixes(implicit loader: ResourceLoader): Seq[StreetSuffix] =
    loader.loadKey[Seq[StreetSuffix]]("address.street-suffixes")
  implicit def streetSuffixArbitrary(implicit
      loader: ResourceLoader
  ): Arbitrary[StreetSuffix] =
    Arbitrary(Gen.oneOf(streetSuffixes))
  implicit val streetSuffixConfigReader: ConfigReader[StreetSuffix] =
    deriveReader
}
