package faker.address

import org.scalacheck.Arbitrary

import faker.{ResourceLoader, StringGenBuilder}

final case class StreetName private (value: String) extends AnyVal

object StreetName {
  implicit def streetNameArbitrary(implicit
      loader: ResourceLoader
  ): Arbitrary[StreetName] =
    Arbitrary(
      loader
        .loadKey[StringGenBuilder]("address.street-name-builder")
        .gen
        .map(StreetName.apply)
    )
}
