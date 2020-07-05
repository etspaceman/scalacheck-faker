package faker.name

import org.scalacheck.Arbitrary

import faker.{ResourceLoader, StringGenBuilder}

final case class FullName private (value: String) extends AnyVal

object FullName {
  implicit def fullNameArbitrary(implicit
      loader: ResourceLoader
  ): Arbitrary[FullName] =
    Arbitrary(
      loader
        .loadKey[StringGenBuilder]("name.full-name-builder")
        .gen
        .map(FullName.apply)
    )
}
