package faker.weather

import org.scalacheck.{Arbitrary, Gen}

final case class TemperatureCelsius private (value: String) extends AnyVal

object TemperatureCelsius {
  implicit def temperatureCelsiusArbitrary: Arbitrary[TemperatureCelsius] =
    Arbitrary(Gen.choose(-30, 38).map(x => TemperatureCelsius(s"$x °C")))
}
