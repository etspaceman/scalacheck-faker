package faker.name

import org.scalacheck.{Arbitrary, Gen}

final case class FullNameWithMiddle private (value: String) extends AnyVal

object FullNameWithMiddle {
  private val prefixFullNameWithMiddleGen: Gen[FullNameWithMiddle] = for {
    prefix <- Arbitrary.arbitrary[Prefix]
    firstName <- Arbitrary.arbitrary[FirstName]
    middleName <- Arbitrary.arbitrary[LastName]
    lastName <- Arbitrary.arbitrary[LastName]
  } yield FullNameWithMiddle(
    s"${prefix.value} ${firstName.value} ${middleName.value} ${lastName.value}"
  )

  private val suffixFullNameWithMiddleGen: Gen[FullNameWithMiddle] = for {
    firstName <- Arbitrary.arbitrary[FirstName]
    middleName <- Arbitrary.arbitrary[LastName]
    lastName <- Arbitrary.arbitrary[LastName]
    suffix <- Arbitrary.arbitrary[Suffix]
  } yield FullNameWithMiddle(
    s"${firstName.value} ${middleName.value} ${lastName.value} ${suffix.value}"
  )

  private val fullNameWithMiddleGen: Gen[FullNameWithMiddle] = for {
    firstName <- Arbitrary.arbitrary[FirstName]
    middleName <- Arbitrary.arbitrary[LastName]
    lastName <- Arbitrary.arbitrary[LastName]
  } yield FullNameWithMiddle(
    s"${firstName.value} ${middleName.value} ${lastName.value}"
  )

  implicit val fullNameWithMiddleArbitrary: Arbitrary[FullNameWithMiddle] =
    Arbitrary(
      Gen.frequency(
        1 -> prefixFullNameWithMiddleGen,
        1 -> suffixFullNameWithMiddleGen,
        4 -> fullNameWithMiddleGen
      )
    )
}
