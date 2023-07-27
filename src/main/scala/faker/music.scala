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

object music {
  type MusicAlbum = MusicAlbum.Type

  object MusicAlbum extends Newtype[String] { self =>
    def musicAlbums(implicit loader: ResourceLoader): Seq[MusicAlbum] =
      loader.loadKey[Seq[MusicAlbum]]("music.albums")

    implicit def musicAlbumArbitrary(implicit
        loader: ResourceLoader
    ): Arbitrary[MusicAlbum] =
      Arbitrary(Gen.oneOf(musicAlbums))

    implicit val musicAlbumConfigReader: ConfigReader[MusicAlbum] =
      ConfigReader[String].map(self.apply)
  }

  type MusicalGenre = MusicalGenre.Type

  object MusicalGenre extends Newtype[String] { self =>
    def musicalGenres(implicit loader: ResourceLoader): Seq[MusicalGenre] =
      loader.loadKey[Seq[MusicalGenre]]("music.genres")

    implicit def musicalGenreArbitrary(implicit
        loader: ResourceLoader
    ): Arbitrary[MusicalGenre] =
      Arbitrary(Gen.oneOf(musicalGenres))

    implicit val musicalGenreConfigReader: ConfigReader[MusicalGenre] =
      ConfigReader[String].map(self.apply)
  }

  type MusicalInstrument = MusicalInstrument.Type

  object MusicalInstrument extends Newtype[String] { self =>
    def musicalInstruments(implicit
        loader: ResourceLoader
    ): Seq[MusicalInstrument] =
      loader.loadKey[Seq[MusicalInstrument]]("music.instruments")

    implicit def musicalInstrumentArbitrary(implicit
        loader: ResourceLoader
    ): Arbitrary[MusicalInstrument] =
      Arbitrary(Gen.oneOf(musicalInstruments))

    implicit val musicalInstrumentConfigReader
        : ConfigReader[MusicalInstrument] =
      ConfigReader[String].map(self.apply)
  }

  type MusicBand = MusicBand.Type

  object MusicBand extends Newtype[String] { self =>
    def musicBands(implicit loader: ResourceLoader): Seq[MusicBand] =
      loader.loadKey[Seq[MusicBand]]("music.bands")

    implicit def musicBandArbitrary(implicit
        loader: ResourceLoader
    ): Arbitrary[MusicBand] =
      Arbitrary(Gen.oneOf(musicBands))

    implicit val musicBandConfigReader: ConfigReader[MusicBand] =
      ConfigReader[String].map(self.apply)
  }
}
