package faker.pokemon

import org.scalacheck.{Arbitrary, Gen}
import pureconfig.ConfigReader
import pureconfig.generic.semiauto._

import faker.ResourceLoader

final case class PokemonLocation private (value: String) extends AnyVal

object PokemonLocation {
  def pokemonLocations(implicit loader: ResourceLoader): Seq[PokemonLocation] =
    loader.loadKey[Seq[PokemonLocation]]("pokemon.locations")

  implicit def pokemonLocationArbitrary(implicit
      loader: ResourceLoader
  ): Arbitrary[PokemonLocation] =
    Arbitrary(Gen.oneOf(pokemonLocations))

  implicit val pokemonLocationConfigReader: ConfigReader[PokemonLocation] =
    deriveReader
}
