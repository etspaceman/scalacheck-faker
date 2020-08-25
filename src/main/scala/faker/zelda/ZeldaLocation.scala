package faker.zelda

import org.scalacheck.{Arbitrary, Gen}
import pureconfig.ConfigReader
import pureconfig.generic.semiauto._

import faker.ResourceLoader

final case class ZeldaLocation private (value: String) extends AnyVal

object ZeldaLocation {
  def zeldaLocations(implicit loader: ResourceLoader): Seq[ZeldaLocation] =
    loader.loadKey[Seq[ZeldaLocation]]("zelda.locations")

  implicit def zeldaLocationArbitrary(implicit
      loader: ResourceLoader
  ): Arbitrary[ZeldaLocation] =
    Arbitrary(Gen.oneOf(zeldaLocations))

  implicit val zeldaLocationConfigReader: ConfigReader[ZeldaLocation] =
    deriveReader
}
