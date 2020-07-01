package faker.internet

import faker.name.FirstName
import org.scalacheck.Arbitrary

final case class Url private (value: String) extends AnyVal

object Url {
  implicit val urlArbitrary: Arbitrary[Url] = Arbitrary(
    for {
      firstName <- Arbitrary.arbitrary[FirstName]
      firstNameProcessed = firstName.value.toLowerCase().replaceAll("'", "")
      domainWord <- Arbitrary.arbitrary[DomainWord]
      domainSuffix <- Arbitrary.arbitrary[DomainSuffix]
    } yield Url(s"www.$firstNameProcessed-$domainWord.$domainSuffix")
  )
}
