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

object zelda {
  type ZeldaCharacter = ZeldaCharacter.Type

  object ZeldaCharacter extends Newtype[String] { self =>
    def zeldaCharacters(implicit loader: ResourceLoader): Seq[ZeldaCharacter] =
      loader.loadKey[Seq[ZeldaCharacter]]("zelda.characters")

    implicit def zeldaCharacterArbitrary(implicit
        loader: ResourceLoader
    ): Arbitrary[ZeldaCharacter] =
      Arbitrary(Gen.oneOf(zeldaCharacters))

    implicit val zeldaCharacterConfigReader: ConfigReader[ZeldaCharacter] =
      ConfigReader[String].map(self.apply)
  }

  type ZeldaGame = ZeldaGame.Type

  object ZeldaGame extends Newtype[String] { self =>
    def zeldaGames(implicit loader: ResourceLoader): Seq[ZeldaGame] =
      loader.loadKey[Seq[ZeldaGame]]("zelda.games")

    implicit def zeldaGameArbitrary(implicit
        loader: ResourceLoader
    ): Arbitrary[ZeldaGame] =
      Arbitrary(Gen.oneOf(zeldaGames))

    implicit val zeldaGameConfigReader: ConfigReader[ZeldaGame] =
      ConfigReader[String].map(self.apply)
  }

  type ZeldaItem = ZeldaItem.Type

  object ZeldaItem extends Newtype[String] { self =>
    def zeldaItems(implicit loader: ResourceLoader): Seq[ZeldaItem] =
      loader.loadKey[Seq[ZeldaItem]]("zelda.items")

    implicit def zeldaItemArbitrary(implicit
        loader: ResourceLoader
    ): Arbitrary[ZeldaItem] =
      Arbitrary(Gen.oneOf(zeldaItems))

    implicit val zeldaItemConfigReader: ConfigReader[ZeldaItem] =
      ConfigReader[String].map(self.apply)
  }

  type ZeldaLocation = ZeldaLocation.Type

  object ZeldaLocation extends Newtype[String] { self =>
    def zeldaLocations(implicit loader: ResourceLoader): Seq[ZeldaLocation] =
      loader.loadKey[Seq[ZeldaLocation]]("zelda.locations")

    implicit def zeldaLocationArbitrary(implicit
        loader: ResourceLoader
    ): Arbitrary[ZeldaLocation] =
      Arbitrary(Gen.oneOf(zeldaLocations))

    implicit val zeldaLocationConfigReader: ConfigReader[ZeldaLocation] =
      ConfigReader[String].map(self.apply)
  }
}
