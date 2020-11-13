package faker

import io.estatico.newtype.macros.newtype
import io.estatico.newtype.ops.toCoercibleIdOps
import org.scalacheck.{Arbitrary, Gen}
import pureconfig.ConfigReader

object music {
  @newtype final case class MusicAlbum private (value: String)

  object MusicAlbum {
    def musicAlbums(implicit loader: ResourceLoader): Seq[MusicAlbum] =
      loader.loadKey[Seq[MusicAlbum]]("music.albums")

    implicit def musicAlbumArbitrary(implicit
        loader: ResourceLoader
    ): Arbitrary[MusicAlbum] =
      Arbitrary(Gen.oneOf(musicAlbums))

    implicit val musicAlbumConfigReader: ConfigReader[MusicAlbum] =
      ConfigReader[String].coerce
  }

  @newtype final case class MusicalGenre private (value: String)

  object MusicalGenre {
    def musicalGenres(implicit loader: ResourceLoader): Seq[MusicalGenre] =
      loader.loadKey[Seq[MusicalGenre]]("music.genres")

    implicit def musicalGenreArbitrary(implicit
        loader: ResourceLoader
    ): Arbitrary[MusicalGenre] =
      Arbitrary(Gen.oneOf(musicalGenres))

    implicit val musicalGenreConfigReader: ConfigReader[MusicalGenre] =
      ConfigReader[String].coerce
  }

  @newtype final case class MusicalInstrument private (value: String)

  object MusicalInstrument {
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
      ConfigReader[String].coerce
  }

  @newtype final case class MusicBand private (value: String)

  object MusicBand {
    def musicBands(implicit loader: ResourceLoader): Seq[MusicBand] =
      loader.loadKey[Seq[MusicBand]]("music.bands")

    implicit def musicBandArbitrary(implicit
        loader: ResourceLoader
    ): Arbitrary[MusicBand] =
      Arbitrary(Gen.oneOf(musicBands))

    implicit val musicBandConfigReader: ConfigReader[MusicBand] =
      ConfigReader[String].coerce
  }
}
