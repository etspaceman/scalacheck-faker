package faker.gender

import org.scalacheck.{Arbitrary, Gen}
import pureconfig.ConfigReader
import pureconfig.generic.semiauto._

import faker.ResourceLoader

final case class GenderShortBinaryType private (value: String) extends AnyVal

object GenderShortBinaryType {
  def genderShortBinaryTypes(implicit
      loader: ResourceLoader
  ): Seq[GenderShortBinaryType] =
    loader.loadKey[Seq[GenderShortBinaryType]]("gender.short-binary-types")

  implicit def genderShortBinaryTypeArbitrary(implicit
      loader: ResourceLoader
  ): Arbitrary[GenderShortBinaryType] =
    Arbitrary(Gen.oneOf(genderShortBinaryTypes))

  implicit val genderShortBinaryTypeConfigReader
      : ConfigReader[GenderShortBinaryType] =
    deriveReader
}
