package faker.gender

import org.scalacheck.{Arbitrary, Gen}
import pureconfig.ConfigReader
import pureconfig.generic.semiauto._

import faker.ResourceLoader

final case class GenderType private (value: String) extends AnyVal

object GenderType {
  def genderTypes(implicit loader: ResourceLoader): Seq[GenderType] =
    loader.loadKey[Seq[GenderType]]("gender.types")

  implicit def genderTypeArbitrary(implicit
      loader: ResourceLoader
  ): Arbitrary[GenderType] =
    Arbitrary(Gen.oneOf(genderTypes))

  implicit val genderTypeConfigReader: ConfigReader[GenderType] =
    deriveReader
}
