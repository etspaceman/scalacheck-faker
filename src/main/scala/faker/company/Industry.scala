package faker.company

import org.scalacheck.{Arbitrary, Gen}
import pureconfig.ConfigReader
import pureconfig.generic.semiauto._

import faker.ResourceLoader

final case class Industry private (value: String) extends AnyVal

object Industry {
  def industries(implicit loader: ResourceLoader): Seq[Industry] =
    loader.loadKey[Seq[Industry]]("company.industries")
  implicit def industryArbitrary(implicit
      loader: ResourceLoader
  ): Arbitrary[Industry] = Arbitrary(Gen.oneOf(industries))

  implicit val industryConfigReader: ConfigReader[Industry] = deriveReader
}
