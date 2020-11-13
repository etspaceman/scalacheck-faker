package faker

import io.estatico.newtype.macros.newtype
import io.estatico.newtype.ops.toCoercibleIdOps
import org.scalacheck.{Arbitrary, Gen}
import pureconfig.ConfigReader

object zelda {
  @newtype final case class ZeldaCharacter private (value: String)

  object ZeldaCharacter {
    def zeldaCharacters(implicit loader: ResourceLoader): Seq[ZeldaCharacter] =
      loader.loadKey[Seq[ZeldaCharacter]]("zelda.characters")

    implicit def zeldaCharacterArbitrary(implicit
        loader: ResourceLoader
    ): Arbitrary[ZeldaCharacter] =
      Arbitrary(Gen.oneOf(zeldaCharacters))

    implicit val zeldaCharacterConfigReader: ConfigReader[ZeldaCharacter] =
      ConfigReader[String].coerce
  }

  @newtype final case class ZeldaGame private (value: String)

  object ZeldaGame {
    def zeldaGames(implicit loader: ResourceLoader): Seq[ZeldaGame] =
      loader.loadKey[Seq[ZeldaGame]]("zelda.games")

    implicit def zeldaGameArbitrary(implicit
        loader: ResourceLoader
    ): Arbitrary[ZeldaGame] =
      Arbitrary(Gen.oneOf(zeldaGames))

    implicit val zeldaGameConfigReader: ConfigReader[ZeldaGame] =
      ConfigReader[String].coerce
  }

  @newtype final case class ZeldaItem private (value: String)

  object ZeldaItem {
    def zeldaItems(implicit loader: ResourceLoader): Seq[ZeldaItem] =
      loader.loadKey[Seq[ZeldaItem]]("zelda.items")

    implicit def zeldaItemArbitrary(implicit
        loader: ResourceLoader
    ): Arbitrary[ZeldaItem] =
      Arbitrary(Gen.oneOf(zeldaItems))

    implicit val zeldaItemConfigReader: ConfigReader[ZeldaItem] =
      ConfigReader[String].coerce
  }

  @newtype final case class ZeldaLocation private (value: String)

  object ZeldaLocation {
    def zeldaLocations(implicit loader: ResourceLoader): Seq[ZeldaLocation] =
      loader.loadKey[Seq[ZeldaLocation]]("zelda.locations")

    implicit def zeldaLocationArbitrary(implicit
        loader: ResourceLoader
    ): Arbitrary[ZeldaLocation] =
      Arbitrary(Gen.oneOf(zeldaLocations))

    implicit val zeldaLocationConfigReader: ConfigReader[ZeldaLocation] =
      ConfigReader[String].coerce
  }
}
