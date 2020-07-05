package faker.name

import org.scalacheck.{Arbitrary, Gen}
import pureconfig.ConfigReader
import pureconfig.generic.semiauto._

import faker.ResourceLoader

final case class Prefix private (value: String) extends AnyVal

object Prefix {
  def prefixes(implicit loader: ResourceLoader): Seq[Prefix] =
    loader.loadKey[Seq[Prefix]]("name.prefixes")
  implicit def prefixArbitrary(implicit
      loader: ResourceLoader
  ): Arbitrary[Prefix] =
    Arbitrary(Gen.oneOf(prefixes))

  implicit val prefixConfigReader: ConfigReader[Prefix] = deriveReader
}
