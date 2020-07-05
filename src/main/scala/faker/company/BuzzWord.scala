package faker.company

import org.scalacheck.{Arbitrary, Gen}
import pureconfig.ConfigReader
import pureconfig.generic.semiauto._

import faker.ResourceLoader

final case class BuzzWord private (value: String) extends AnyVal

object BuzzWord {
  def buzzWords(implicit loader: ResourceLoader): Seq[BuzzWord] =
    loader.loadKey[Seq[BuzzWord]]("company.buzz-words")
  implicit def buzzWordArbitrary(implicit
      loader: ResourceLoader
  ): Arbitrary[BuzzWord] = Arbitrary(Gen.oneOf(buzzWords))

  implicit val buzzWordConfigReader: ConfigReader[BuzzWord] = deriveReader
}
