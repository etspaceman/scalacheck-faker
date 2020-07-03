package faker.internet

import org.scalacheck.Arbitrary

import faker.ResourceLoader
import faker.name.FirstName

final case class Url private (value: String) extends AnyVal

object Url {
  implicit def urlArbitrary(implicit loader: ResourceLoader): Arbitrary[Url] =
    Arbitrary(
      for {
        firstName <- Arbitrary.arbitrary[FirstName]
        firstNameProcessed = firstName.value.toLowerCase().replaceAll("'", "")
        domainWord <- Arbitrary.arbitrary[DomainWord]
        domainSuffix <- Arbitrary.arbitrary[DomainSuffix]
      } yield Url(
        s"www.$firstNameProcessed-${domainWord.value}.${domainSuffix.value}"
      )
    )
}
