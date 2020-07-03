package faker.name

import org.scalacheck.{Arbitrary, Gen}

import faker.ResourceLoader

final case class FullName private (value: String) extends AnyVal

object FullName {
  private def prefixFullNameGen(implicit
      loader: ResourceLoader
  ): Gen[FullName] =
    for {
      prefix <- Arbitrary.arbitrary[Prefix]
      firstName <- Arbitrary.arbitrary[FirstName]
      lastName <- Arbitrary.arbitrary[LastName]
    } yield FullName(s"${prefix.value} ${firstName.value} ${lastName.value}")

  private def suffixFullNameGen(implicit
      loader: ResourceLoader
  ): Gen[FullName] =
    for {
      firstName <- Arbitrary.arbitrary[FirstName]
      lastName <- Arbitrary.arbitrary[LastName]
      suffix <- Arbitrary.arbitrary[Suffix]
    } yield FullName(s"${firstName.value} ${lastName.value} ${suffix.value}")

  private def fullNameGen(implicit
      loader: ResourceLoader
  ): Gen[FullName] =
    for {
      firstName <- Arbitrary.arbitrary[FirstName]
      lastName <- Arbitrary.arbitrary[LastName]
    } yield FullName(s"${firstName.value} ${lastName.value}")

  implicit def fullNameArbitrary(implicit
      loader: ResourceLoader
  ): Arbitrary[FullName] =
    Arbitrary(
      Gen.frequency(
        1 -> prefixFullNameGen,
        1 -> suffixFullNameGen,
        4 -> fullNameGen
      )
    )
}
