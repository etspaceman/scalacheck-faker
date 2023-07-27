/*
 * Copyright (c) 2020 etspaceman
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of
 * this software and associated documentation files (the "Software"), to deal in
 * the Software without restriction, including without limitation the rights to
 * use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of
 * the Software, and to permit persons to whom the Software is furnished to do so,
 * subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
 * FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 * COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER
 * IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
 * CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package faker

import org.scalacheck.{Arbitrary, Gen}
import pureconfig.ConfigReader

object weather {
  type TemperatureCelsius = TemperatureCelsius.Type

  object TemperatureCelsius extends Newtype[String] { self =>
    implicit def temperatureCelsiusArbitrary: Arbitrary[TemperatureCelsius] =
      Arbitrary(Gen.choose(-30, 38).map(x => s"$x °C").map(self.apply))
  }

  type TemperatureFahrenheit = TemperatureFahrenheit.Type

  object TemperatureFahrenheit extends Newtype[String] { self =>
    implicit def temperatureFahrenheitArbitrary
        : Arbitrary[TemperatureFahrenheit] =
      Arbitrary(Gen.choose(-22, 100).map(x => s"$x °F").map(self.apply))
  }

  type WeatherDescription = WeatherDescription.Type

  object WeatherDescription extends Newtype[String] { self =>
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
      ConfigReader[String].map(self.apply)
  }
}
