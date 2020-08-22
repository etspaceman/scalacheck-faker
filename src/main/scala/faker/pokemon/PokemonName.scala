package faker.pokemon

import org.scalacheck.{Arbitrary, Gen}
import pureconfig.ConfigReader
import pureconfig.generic.semiauto._

import faker.ResourceLoader

final case class PokemonName private (value: String) extends AnyVal

object PokemonName {
  def pokemonNames(implicit loader: ResourceLoader): Seq[PokemonName] =
    loader.loadKey[Seq[PokemonName]]("pokemon.names")

  implicit def pokemonNameArbitrary(implicit
      loader: ResourceLoader
  ): Arbitrary[PokemonName] =
    Arbitrary(Gen.oneOf(pokemonNames))

  implicit val pokemonNameConfigReader: ConfigReader[PokemonName] = deriveReader
}
