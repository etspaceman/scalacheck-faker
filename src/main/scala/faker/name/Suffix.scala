package faker.name

import org.scalacheck.{Arbitrary, Gen}
import pureconfig.ConfigReader
import pureconfig.generic.semiauto._

import faker.ResourceLoader

final case class Suffix private (value: String) extends AnyVal

object Suffix {
  def suffixes(implicit loader: ResourceLoader): Seq[Suffix] =
    loader.loadKey[Seq[Suffix]]("name.suffixes")
  implicit def suffixArbitrary(implicit
      loader: ResourceLoader
  ): Arbitrary[Suffix] = Arbitrary(Gen.oneOf(suffixes))

  implicit val suffixConfigReader: ConfigReader[Suffix] = deriveReader
}
