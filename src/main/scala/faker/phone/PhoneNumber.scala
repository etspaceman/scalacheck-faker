package faker.phone

import org.scalacheck.Arbitrary

import faker.{ResourceLoader, StringGenBuilder}

final case class PhoneNumber private (value: String) extends AnyVal

object PhoneNumber {
  implicit def phoneNumberArbitrary(implicit
      loader: ResourceLoader
  ): Arbitrary[PhoneNumber] =
    Arbitrary(
      loader
        .loadKey[StringGenBuilder]("phone.phone-number-builder")
        .gen
        .map(PhoneNumber.apply)
    )
}
