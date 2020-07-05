package faker.address

import org.scalacheck.{Arbitrary, Gen}
import pureconfig.ConfigReader
import pureconfig.generic.semiauto._

import faker.ResourceLoader

final case class CityPrefix private (value: String) extends AnyVal

object CityPrefix {
  def cityPrefixes(implicit loader: ResourceLoader): Seq[CityPrefix] =
    loader.loadKey[Seq[CityPrefix]]("address.city-prefixes")
  implicit def cityPrefixArbitrary(implicit
      loader: ResourceLoader
  ): Arbitrary[CityPrefix] =
    Arbitrary(Gen.oneOf(cityPrefixes))

  implicit val cityPrefixConfigReader: ConfigReader[CityPrefix] = deriveReader
}
