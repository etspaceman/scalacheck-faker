package faker.company

import org.scalacheck.Arbitrary

import faker.{ResourceLoader, StringGenBuilder}

final case class BS private (value: String) extends AnyVal

object BS {
  implicit def bsArbitrary(implicit
      loader: ResourceLoader
  ): Arbitrary[BS] =
    Arbitrary(
      loader.loadKey[StringGenBuilder]("company.bs-builder").gen.map(BS.apply)
    )
}
