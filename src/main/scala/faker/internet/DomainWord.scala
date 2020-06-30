package faker.internet

import faker.name.LastName
import org.scalacheck.Arbitrary

final case class DomainWord private (value: String) extends AnyVal

object DomainWord {
  implicit val domainWordArbitrary: Arbitrary[DomainWord] = Arbitrary(
    Arbitrary
      .arbitrary[LastName]
      .map(ln => FakerIDN.toASCII(ln.value.toLowerCase().replaceAll("'", "")))
      .map(DomainWord.apply)
  )
}
