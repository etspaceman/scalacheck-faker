package faker

import io.estatico.newtype.macros.newtype
import io.estatico.newtype.ops._
import org.scalacheck.{Arbitrary, Gen}
import pureconfig.ConfigReader

object gender {

  @newtype final case class GenderBinaryType private (value: String)

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
      ConfigReader[String].coerce
  }

  @newtype final case class GenderShortBinaryType private (value: String)

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
      ConfigReader[String].coerce
  }

  @newtype final case class GenderType private (value: String)

  object GenderType {
    def genderTypes(implicit loader: ResourceLoader): Seq[GenderType] =
      loader.loadKey[Seq[GenderType]]("gender.types")

    implicit def genderTypeArbitrary(implicit
        loader: ResourceLoader
    ): Arbitrary[GenderType] =
      Arbitrary(Gen.oneOf(genderTypes))

    implicit val genderTypeConfigReader: ConfigReader[GenderType] =
      ConfigReader[String].coerce
  }

}
