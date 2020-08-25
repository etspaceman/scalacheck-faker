package faker.gender

import org.scalacheck.{Arbitrary, Gen}
import pureconfig.ConfigReader
import pureconfig.generic.semiauto._

import faker.ResourceLoader

final case class GenderBinaryType private (value: String) extends AnyVal

object GenderBinaryType {
  def genderBinaryTypes(implicit
      loader: ResourceLoader
  ): Seq[GenderBinaryType] =
    loader.loadKey[Seq[GenderBinaryType]]("gender.binary-types")

  implicit def genderBinaryTypeArbitrary(implicit
      loader: ResourceLoader
  ): Arbitrary[GenderBinaryType] =
    Arbitrary(Gen.oneOf(genderBinaryTypes))

  implicit val genderBinaryTypeConfigReader: ConfigReader[GenderBinaryType] =
    deriveReader
}
