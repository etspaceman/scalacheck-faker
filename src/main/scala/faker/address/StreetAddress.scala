package faker.address

import org.scalacheck.Arbitrary

import faker.{ResourceLoader, StringGenBuilder}

final case class StreetAddress private (value: String) extends AnyVal

object StreetAddress {
  implicit def streetAddressArbitrary(implicit
      loader: ResourceLoader
  ): Arbitrary[StreetAddress] =
    Arbitrary(
      loader
        .loadKey[StringGenBuilder]("address.street-address-builder")
        .gen
        .map(StreetAddress.apply)
    )
}
