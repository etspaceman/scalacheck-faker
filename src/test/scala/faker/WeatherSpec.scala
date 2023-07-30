package faker

import cats.syntax.all._

object WeatherSpec extends FakerSpec {

  doTest[weather.WeatherDescription, String](
    "weather.WeatherDescription",
    _.weatherDescription(),
    faker => {
      implicit val resourceLoader: ResourceLoader = faker.loader
      implicitly
    },
    _.nonEmpty,
    _.value
  )

  doTest[weather.TemperatureCelsius, String](
    "weather.TemperatureCelsius",
    _.temperatureCelsius(),
    _ => implicitly,
    _.nonEmpty,
    _.value
  )

  doTest[weather.TemperatureFahrenheit, String](
    "weather.TemperatureFahrenheit",
    _.temperatureFahrenheit(),
    _ => implicitly,
    _.nonEmpty,
    _.value
  )

}
