package faker.weather

import org.scalacheck.{Arbitrary, Gen}
import pureconfig.ConfigReader
import pureconfig.generic.semiauto._

import faker.ResourceLoader

final case class WeatherDescription private (value: String) extends AnyVal

object WeatherDescription {
  def weatherDescriptions(implicit
      loader: ResourceLoader
  ): Seq[WeatherDescription] =
    loader.loadKey[Seq[WeatherDescription]]("weather.descriptions")

  implicit def weatherDescriptionArbitrary(implicit
      loader: ResourceLoader
  ): Arbitrary[WeatherDescription] =
    Arbitrary(Gen.oneOf(weatherDescriptions))

  implicit val weatherDescriptionConfigReader
      : ConfigReader[WeatherDescription] =
    deriveReader
}
