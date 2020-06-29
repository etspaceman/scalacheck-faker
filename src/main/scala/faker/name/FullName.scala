package faker.name

import org.scalacheck.{Arbitrary, Gen}

final case class FullName private (value: String) extends AnyVal

object FullName {
  private val prefixFullNameGen: Gen[FullName] = for {
    prefix <- Arbitrary.arbitrary[Prefix]
    firstName <- Arbitrary.arbitrary[FirstName]
    lastName <- Arbitrary.arbitrary[LastName]
  } yield FullName(s"${prefix.value} ${firstName.value} ${lastName.value}")

  private val suffixFullNameGen: Gen[FullName] = for {
    firstName <- Arbitrary.arbitrary[FirstName]
    lastName <- Arbitrary.arbitrary[LastName]
    suffix <- Arbitrary.arbitrary[Suffix]
  } yield FullName(s"${firstName.value} ${lastName.value} ${suffix.value}")

  private val fullNameGen: Gen[FullName] = for {
    firstName <- Arbitrary.arbitrary[FirstName]
    lastName <- Arbitrary.arbitrary[LastName]
  } yield FullName(s"${firstName.value} ${lastName.value}")

  implicit val fullNameArbitrary: Arbitrary[FullName] = Arbitrary(
    Gen.frequency(1 -> prefixFullNameGen, 1 -> suffixFullNameGen, 4 -> fullNameGen)
  )
}
