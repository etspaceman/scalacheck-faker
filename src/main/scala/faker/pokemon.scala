package faker

import io.estatico.newtype.macros.newtype
import io.estatico.newtype.ops._
import org.scalacheck.{Arbitrary, Gen}
import pureconfig.ConfigReader

object pokemon {
  @newtype final case class PokemonLocation private (value: String)

  object PokemonLocation {
    def pokemonLocations(implicit
        loader: ResourceLoader
    ): Seq[PokemonLocation] =
      loader.loadKey[Seq[PokemonLocation]]("pokemon.locations")

    implicit def pokemonLocationArbitrary(implicit
        loader: ResourceLoader
    ): Arbitrary[PokemonLocation] =
      Arbitrary(Gen.oneOf(pokemonLocations))

    implicit val pokemonLocationConfigReader: ConfigReader[PokemonLocation] =
      ConfigReader[String].coerce
  }

  @newtype final case class PokemonMove private (value: String)

  object PokemonMove {
    def pokemonMoves(implicit loader: ResourceLoader): Seq[PokemonMove] =
      loader.loadKey[Seq[PokemonMove]]("pokemon.moves")

    implicit def pokemonMoveArbitrary(implicit
        loader: ResourceLoader
    ): Arbitrary[PokemonMove] =
      Arbitrary(Gen.oneOf(pokemonMoves))

    implicit val pokemonMoveConfigReader: ConfigReader[PokemonMove] =
      ConfigReader[String].coerce
  }

  @newtype final case class PokemonName private (value: String)

  object PokemonName {
    def pokemonNames(implicit loader: ResourceLoader): Seq[PokemonName] =
      loader.loadKey[Seq[PokemonName]]("pokemon.names")

    implicit def pokemonNameArbitrary(implicit
        loader: ResourceLoader
    ): Arbitrary[PokemonName] =
      Arbitrary(Gen.oneOf(pokemonNames))

    implicit val pokemonNameConfigReader: ConfigReader[PokemonName] =
      ConfigReader[String].coerce
  }

}
