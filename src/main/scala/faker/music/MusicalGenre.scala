package faker.music

import org.scalacheck.{Arbitrary, Gen}
import pureconfig.ConfigReader
import pureconfig.generic.semiauto._

import faker.ResourceLoader

final case class MusicalGenre private (value: String) extends AnyVal

object MusicalGenre {
  def musicalGenres(implicit loader: ResourceLoader): Seq[MusicalGenre] =
    loader.loadKey[Seq[MusicalGenre]]("music.genres")

  implicit def musicalGenreArbitrary(implicit
      loader: ResourceLoader
  ): Arbitrary[MusicalGenre] =
    Arbitrary(Gen.oneOf(musicalGenres))

  implicit val musicalGenreConfigReader: ConfigReader[MusicalGenre] =
    deriveReader
}
