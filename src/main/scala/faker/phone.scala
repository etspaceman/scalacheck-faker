package faker

import io.estatico.newtype.macros.newtype
import io.estatico.newtype.ops._
import org.scalacheck.Arbitrary

object phone {
  @newtype final case class CellPhoneNumber private (value: String)

  object CellPhoneNumber {
    implicit def cellPhoneNumberArbitrary(implicit
        loader: ResourceLoader
    ): Arbitrary[CellPhoneNumber] =
      Arbitrary(
        loader
          .loadKey[StringGenBuilder]("phone.cell-number-builder")
          .gen
      ).coerce
  }

  @newtype final case class PhoneNumber private (value: String)

  object PhoneNumber {
    implicit def phoneNumberArbitrary(implicit
        loader: ResourceLoader
    ): Arbitrary[PhoneNumber] =
      Arbitrary(
        loader
          .loadKey[StringGenBuilder]("phone.phone-number-builder")
          .gen
      ).coerce
  }
}
