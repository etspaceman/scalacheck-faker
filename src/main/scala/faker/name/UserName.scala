package faker.name

import org.scalacheck.Arbitrary

final case class UserName private (value: String) extends AnyVal

object UserName {
  implicit val userNameArbitrary: Arbitrary[UserName] = Arbitrary {
    for {
      firstName <- Arbitrary.arbitrary[FirstName].map(_.value.replaceAll("'", ""))
      lastName <- Arbitrary.arbitrary[LastName].map(_.value.replaceAll("'", ""))
    } yield UserName(s"$firstName.$lastName")
  }
}
