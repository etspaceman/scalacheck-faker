package faker.company

import org.scalacheck.Arbitrary

import faker.ResourceLoader
import faker.internet.DomainSuffix

final case class CompanyUrl private (value: String) extends AnyVal

object CompanyUrl {
  implicit def companyUrlArbitrary(implicit
      loader: ResourceLoader
  ): Arbitrary[CompanyUrl] =
    Arbitrary(
      for {
        domainName <- Arbitrary.arbitrary[CompanyDomainName]
        domainSuffix <- Arbitrary.arbitrary[DomainSuffix]
      } yield CompanyUrl(s"www.${domainName.value}.${domainSuffix.value}")
    )
}
