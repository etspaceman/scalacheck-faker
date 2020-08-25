package faker.zelda

import org.scalacheck.{Arbitrary, Gen}
import pureconfig.ConfigReader
import pureconfig.generic.semiauto._

import faker.ResourceLoader

final case class ZeldaCharacter private (value: String) extends AnyVal

object ZeldaCharacter {
  def zeldaCharacters(implicit loader: ResourceLoader): Seq[ZeldaCharacter] =
    loader.loadKey[Seq[ZeldaCharacter]]("zelda.characters")

  implicit def zeldaCharacterArbitrary(implicit
      loader: ResourceLoader
  ): Arbitrary[ZeldaCharacter] =
    Arbitrary(Gen.oneOf(zeldaCharacters))

  implicit val zeldaCharacterConfigReader: ConfigReader[ZeldaCharacter] =
    deriveReader
}
