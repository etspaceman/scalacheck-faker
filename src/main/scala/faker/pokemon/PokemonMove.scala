package faker.pokemon

import org.scalacheck.{Arbitrary, Gen}
import pureconfig.ConfigReader
import pureconfig.generic.semiauto._

import faker.ResourceLoader

final case class PokemonMove private (value: String) extends AnyVal

object PokemonMove {
  def pokemonMoves(implicit loader: ResourceLoader): Seq[PokemonMove] =
    loader.loadKey[Seq[PokemonMove]]("pokemon.moves")

  implicit def pokemonMoveArbitrary(implicit
      loader: ResourceLoader
  ): Arbitrary[PokemonMove] =
    Arbitrary(Gen.oneOf(pokemonMoves))

  implicit val pokemonMoveConfigReader: ConfigReader[PokemonMove] =
    deriveReader
}
