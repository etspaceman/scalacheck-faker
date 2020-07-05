package faker.address

import org.scalacheck.{Arbitrary, Gen}
import pureconfig.ConfigReader
import pureconfig.generic.semiauto._

import faker.ResourceLoader

final case class Country private (code: String, name: String)

object Country {
  def countries(implicit loader: ResourceLoader): Seq[Country] =
    loader.loadKey[Seq[Country]]("address.countries")
  implicit def countryArbitrary(implicit
      loader: ResourceLoader
  ): Arbitrary[Country] = Arbitrary(Gen.oneOf(countries))

  implicit val countryConfigReader: ConfigReader[Country] = deriveReader
}
