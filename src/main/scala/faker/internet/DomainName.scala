package faker.internet

import org.scalacheck.Arbitrary

import faker.ResourceLoader

final case class DomainName private (value: String) extends AnyVal

object DomainName {
  implicit def domainNameArbitrary(implicit
      loader: ResourceLoader
  ): Arbitrary[DomainName] =
    Arbitrary {
      for {
        word <- Arbitrary.arbitrary[DomainWord]
        suffix <- Arbitrary.arbitrary[DomainSuffix]
      } yield DomainName(s"${word.value}.${suffix.value}")
    }
}
