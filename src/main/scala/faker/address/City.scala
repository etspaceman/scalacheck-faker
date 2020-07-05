package faker.address

import org.scalacheck.Arbitrary
import pureconfig.ConfigReader
import pureconfig.generic.semiauto._

import faker.{ResourceLoader, StringGenBuilder}

final case class City private (value: String) extends AnyVal

object City {
  implicit def cityArbitrary(implicit loader: ResourceLoader): Arbitrary[City] =
    Arbitrary(
      loader
        .loadKey[StringGenBuilder]("address.city-builder")
        .gen
        .map(x => City(x))
    )

  implicit val cityConfigReader: ConfigReader[City] = deriveReader

}
