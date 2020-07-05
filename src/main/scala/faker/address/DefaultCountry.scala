package faker.address

import org.scalacheck.{Arbitrary, Gen}
import pureconfig.ConfigReader
import pureconfig.generic.semiauto._

import faker.ResourceLoader

final case class DefaultCountry private (code: String, name: String)

object DefaultCountry {
  def defaultCountry(implicit loader: ResourceLoader): DefaultCountry =
    loader.loadKey[DefaultCountry]("address.default-country")
  implicit def defaultCountryArbitrary(implicit
      loader: ResourceLoader
  ): Arbitrary[DefaultCountry] = Arbitrary(Gen.const(defaultCountry))

  implicit val defaultCountryConfigReader: ConfigReader[DefaultCountry] =
    deriveReader
}
