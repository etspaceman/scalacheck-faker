package faker.music

import org.scalacheck.{Arbitrary, Gen}
import pureconfig.ConfigReader
import pureconfig.generic.semiauto._

import faker.ResourceLoader

final case class MusicalInstrument private (value: String) extends AnyVal

object MusicalInstrument {
  def musicalInstruments(implicit
      loader: ResourceLoader
  ): Seq[MusicalInstrument] =
    loader.loadKey[Seq[MusicalInstrument]]("music.instruments")

  implicit def musicalInstrumentArbitrary(implicit
      loader: ResourceLoader
  ): Arbitrary[MusicalInstrument] =
    Arbitrary(Gen.oneOf(musicalInstruments))

  implicit val musicalInstrumentConfigReader: ConfigReader[MusicalInstrument] =
    deriveReader
}
