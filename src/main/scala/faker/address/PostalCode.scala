package faker.address

import org.scalacheck.Arbitrary

import faker.{ResourceLoader, StringGenBuilder}

final case class PostalCode private (value: String) extends AnyVal

object PostalCode {
  implicit def postalCodeArbitrary(implicit
      loader: ResourceLoader
  ): Arbitrary[PostalCode] =
    Arbitrary(
      loader
        .loadKey[StringGenBuilder]("address.postal-code-builder")
        .gen
        .map(PostalCode.apply)
    )
}
