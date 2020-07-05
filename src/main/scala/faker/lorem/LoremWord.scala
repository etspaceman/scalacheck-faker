package faker.lorem

import org.scalacheck.{Arbitrary, Gen}
import pureconfig.ConfigReader
import pureconfig.generic.semiauto._

import faker.ResourceLoader

final case class LoremWord private (value: String) extends AnyVal

object LoremWord {
  def loremWords(implicit loader: ResourceLoader): Seq[LoremWord] =
    loader.loadKey[Seq[LoremWord]]("lorem.words")
  implicit def loremWordArbitrary(implicit
      loader: ResourceLoader
  ): Arbitrary[LoremWord] =
    Arbitrary(Gen.oneOf(loremWords))

  implicit val loremWordConfigReader: ConfigReader[LoremWord] = deriveReader
}
