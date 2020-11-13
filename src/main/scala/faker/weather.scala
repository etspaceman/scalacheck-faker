package faker

import io.estatico.newtype.macros.newtype
import io.estatico.newtype.ops._
import org.scalacheck.{Arbitrary, Gen}
import pureconfig.ConfigReader

object weather {
  @newtype final case class TemperatureCelsius private (value: String)

  object TemperatureCelsius {
    implicit def temperatureCelsiusArbitrary: Arbitrary[TemperatureCelsius] =
      Arbitrary(Gen.choose(-30, 38).map(x => s"$x °C")).coerce
  }

  @newtype final case class TemperatureFahrenheit private (value: String)

  object TemperatureFahrenheit {
    implicit def temperatureFahrenheitArbitrary
        : Arbitrary[TemperatureFahrenheit] =
      Arbitrary(Gen.choose(-22, 100).map(x => s"$x °F")).coerce
  }

  @newtype final case class WeatherDescription private (value: String)

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
      ConfigReader[String].coerce
  }
}
