package faker

import cats.syntax.all._

object PokemonSpec extends FakerSpec {

  doTest[pokemon.PokemonName, String](
    "pokemon.PokemonName",
    _.pokemonName(),
    faker => {
      implicit val resourceLoader: ResourceLoader = faker.loader
      implicitly
    },
    _.nonEmpty,
    _.value
  )

  doTest[pokemon.PokemonLocation, String](
    "pokemon.PokemonLocation",
    _.pokemonLocation(),
    faker => {
      implicit val resourceLoader: ResourceLoader = faker.loader
      implicitly
    },
    _.nonEmpty,
    _.value
  )

   doTest[pokemon.PokemonMove, String](
    "pokemon.PokemonMove",
    _.pokemonMove(),
    faker => {
      implicit val resourceLoader: ResourceLoader = faker.loader
      implicitly
    },
    _.nonEmpty,
    _.value
  )

}
