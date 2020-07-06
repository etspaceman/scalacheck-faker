package faker.phone

import org.scalacheck.Arbitrary

import faker.{ResourceLoader, StringGenBuilder}

final case class CellPhoneNumber private (value: String) extends AnyVal

object CellPhoneNumber {
  implicit def cellPhoneNumberArbitrary(implicit
      loader: ResourceLoader
  ): Arbitrary[CellPhoneNumber] =
    Arbitrary(
      loader
        .loadKey[StringGenBuilder]("phone.cell-number-builder")
        .gen
        .map(CellPhoneNumber.apply)
    )
}
