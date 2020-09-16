package faker.dragonball

import org.scalacheck.{Arbitrary, Gen}
import pureconfig.ConfigReader
import pureconfig.generic.semiauto._

import faker.ResourceLoader

final case class DragonBallCharacter private (value: String) extends AnyVal

object DragonBallCharacter {
  def dragonBallCharacters(implicit
      loader: ResourceLoader
  ): Seq[DragonBallCharacter] =
    loader.loadKey[Seq[DragonBallCharacter]]("dragonball.characters")

  implicit def dragonBallCharacterArbitrary(implicit
      loader: ResourceLoader
  ): Arbitrary[DragonBallCharacter] =
    Arbitrary(Gen.oneOf(dragonBallCharacters))

  implicit val dragonBallCharacterConfigReader
      : ConfigReader[DragonBallCharacter] =
    deriveReader
}
