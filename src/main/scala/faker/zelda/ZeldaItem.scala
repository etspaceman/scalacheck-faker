package faker.zelda

import org.scalacheck.{Arbitrary, Gen}
import pureconfig.ConfigReader
import pureconfig.generic.semiauto._

import faker.ResourceLoader

final case class ZeldaItem private (value: String) extends AnyVal

object ZeldaItem {
  def zeldaItems(implicit loader: ResourceLoader): Seq[ZeldaItem] =
    loader.loadKey[Seq[ZeldaItem]]("zelda.items")

  implicit def zeldaItemArbitrary(implicit
      loader: ResourceLoader
  ): Arbitrary[ZeldaItem] =
    Arbitrary(Gen.oneOf(zeldaItems))

  implicit val zeldaItemConfigReader: ConfigReader[ZeldaItem] =
    deriveReader
}
