package faker.company

import org.scalacheck.Arbitrary

import faker.{ResourceLoader, StringGenBuilder}

final case class CompanyName private (value: String) extends AnyVal

object CompanyName {
  implicit def companyNameArbitrary(implicit
      loader: ResourceLoader
  ): Arbitrary[CompanyName] =
    Arbitrary(
      loader
        .loadKey[StringGenBuilder]("company.company-name-builder")
        .gen
        .map(CompanyName.apply)
    )
}
