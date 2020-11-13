package faker

import io.estatico.newtype.macros.newtype
import io.estatico.newtype.ops._
import org.scalacheck.{Arbitrary, Gen}
import pureconfig.ConfigReader

object dragonball {
  @newtype final case class DragonBallCharacter private (value: String)

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
      ConfigReader[String].coerce
  }

}
