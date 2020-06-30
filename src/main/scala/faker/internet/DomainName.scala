package faker.internet

import org.scalacheck.Arbitrary

final case class DomainName private (value: String) extends AnyVal

object DomainName {
  implicit val domainNameArbitrary: Arbitrary[DomainName] = Arbitrary {
    for {
      word <- Arbitrary.arbitrary[DomainWord]
      suffix <- Arbitrary.arbitrary[DomainSuffix]
    } yield DomainName(s"${word.value}.${suffix.value}")
  }
}
