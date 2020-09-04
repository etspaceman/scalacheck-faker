package faker.weather

import org.scalacheck.{Arbitrary, Gen}

final case class TemperatureFahrenheit private (value: String) extends AnyVal

object TemperatureFahrenheit {
  implicit def temperatureFahrenheitArbitrary
      : Arbitrary[TemperatureFahrenheit] =
    Arbitrary(Gen.choose(-22, 100).map(x => TemperatureFahrenheit(s"$x °F")))
}
