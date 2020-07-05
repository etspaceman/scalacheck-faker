package faker.name

import org.scalacheck.Arbitrary

import faker.{ResourceLoader, StringGenBuilder}

final case class FullNameWithMiddle private (value: String) extends AnyVal

object FullNameWithMiddle {
  implicit def fullNameWithMiddleArbitrary(implicit
      loader: ResourceLoader
  ): Arbitrary[FullNameWithMiddle] =
    Arbitrary(
      loader
        .loadKey[StringGenBuilder]("name.full-name-with-middle-builder")
        .gen
        .map(FullNameWithMiddle.apply)
    )
}
