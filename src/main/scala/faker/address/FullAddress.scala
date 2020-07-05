package faker.address

import org.scalacheck.Arbitrary

import faker.{ResourceLoader, StringGenBuilder}

final case class FullAddress private (value: String) extends AnyVal

object FullAddress {
  implicit def fullAddressArbitrary(implicit
      loader: ResourceLoader
  ): Arbitrary[FullAddress] =
    Arbitrary(
      loader
        .loadKey[StringGenBuilder]("address.full-address-builder")
        .gen
        .map(FullAddress.apply)
    )
}
