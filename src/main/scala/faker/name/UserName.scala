package faker.name

import org.scalacheck.Arbitrary

import faker.ResourceLoader

final case class UserName private (value: String) extends AnyVal

object UserName {
  implicit def userNameArbitrary(implicit
      loader: ResourceLoader
  ): Arbitrary[UserName] =
    Arbitrary {
      for {
        firstName <-
          Arbitrary
            .arbitrary[FirstName]
            .map(_.value.replaceAll("'", "").toLowerCase())
        lastName <-
          Arbitrary
            .arbitrary[LastName]
            .map(_.value.replaceAll("'", "").toLowerCase())
      } yield UserName(s"$firstName.$lastName")
    }
}
