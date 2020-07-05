package faker.company

import org.scalacheck.Arbitrary

import faker.ResourceLoader
import faker.internet.FakerIDN

final case class CompanyDomainName private (value: String) extends AnyVal

object CompanyDomainName {
  implicit def companyDomainNameArbitrary(implicit
      loader: ResourceLoader
  ): Arbitrary[CompanyDomainName] =
    Arbitrary(
      Arbitrary
        .arbitrary[CompanyName]
        .map(ln =>
          FakerIDN.toASCII(
            ln.value
              .toLowerCase()
              .replaceAll("'", "")
              .replaceAll(" ", "")
              .replaceAll(",", "\\.")
          )
        )
        .map(CompanyDomainName.apply)
    )
}
