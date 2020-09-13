package faker.music

import org.scalacheck.{Arbitrary, Gen}
import pureconfig.ConfigReader
import pureconfig.generic.semiauto._

import faker.ResourceLoader

final case class MusicBand private (value: String) extends AnyVal

object MusicBand {
  def musicBands(implicit loader: ResourceLoader): Seq[MusicBand] =
    loader.loadKey[Seq[MusicBand]]("music.bands")

  implicit def musicBandArbitrary(implicit
      loader: ResourceLoader
  ): Arbitrary[MusicBand] =
    Arbitrary(Gen.oneOf(musicBands))

  implicit val musicBandConfigReader: ConfigReader[MusicBand] = deriveReader

}
