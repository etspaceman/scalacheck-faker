package faker.address

import org.scalacheck.{Arbitrary, Gen}
import pureconfig.ConfigReader
import pureconfig.generic.semiauto._

import faker.ResourceLoader

final case class DefaultCountry private (code: String, name: String)

object DefaultCountry {
  def defaultCountries(implicit loader: ResourceLoader): Seq[DefaultCountry] =
    loader.loadKey[Seq[DefaultCountry]]("address.default-countries")
  implicit def defaultCountryArbitrary(implicit
      loader: ResourceLoader
  ): Arbitrary[DefaultCountry] = Arbitrary(Gen.oneOf(defaultCountries))

  implicit val defaultCountryConfigReader: ConfigReader[DefaultCountry] =
    deriveReader
}
