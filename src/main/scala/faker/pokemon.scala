/*
 * Copyright (c) 2020 etspaceman
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of
 * this software and associated documentation files (the "Software"), to deal in
 * the Software without restriction, including without limitation the rights to
 * use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of
 * the Software, and to permit persons to whom the Software is furnished to do so,
 * subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
 * FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 * COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER
 * IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
 * CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package faker

import org.scalacheck.{Arbitrary, Gen}
import pureconfig.ConfigReader

object pokemon {
  type PokemonLocation = PokemonLocation.Type

  object PokemonLocation extends Newtype[String] { self =>
    def pokemonLocations(implicit
        loader: ResourceLoader
    ): Seq[PokemonLocation] =
      loader.loadKey[Seq[PokemonLocation]]("pokemon.locations")

    implicit def pokemonLocationArbitrary(implicit
        loader: ResourceLoader
    ): Arbitrary[PokemonLocation] =
      Arbitrary(Gen.oneOf(pokemonLocations))

    implicit val pokemonLocationConfigReader: ConfigReader[PokemonLocation] =
      ConfigReader[String].map(self.apply)
  }

  type PokemonMove = PokemonMove.Type

  object PokemonMove extends Newtype[String] { self =>
    def pokemonMoves(implicit loader: ResourceLoader): Seq[PokemonMove] =
      loader.loadKey[Seq[PokemonMove]]("pokemon.moves")

    implicit def pokemonMoveArbitrary(implicit
        loader: ResourceLoader
    ): Arbitrary[PokemonMove] =
      Arbitrary(Gen.oneOf(pokemonMoves))

    implicit val pokemonMoveConfigReader: ConfigReader[PokemonMove] =
      ConfigReader[String].map(self.apply)
  }

  type PokemonName = PokemonName.Type

  object PokemonName extends Newtype[String] { self =>
    def pokemonNames(implicit loader: ResourceLoader): Seq[PokemonName] =
      loader.loadKey[Seq[PokemonName]]("pokemon.names")

    implicit def pokemonNameArbitrary(implicit
        loader: ResourceLoader
    ): Arbitrary[PokemonName] =
      Arbitrary(Gen.oneOf(pokemonNames))

    implicit val pokemonNameConfigReader: ConfigReader[PokemonName] =
      ConfigReader[String].map(self.apply)
  }

}
