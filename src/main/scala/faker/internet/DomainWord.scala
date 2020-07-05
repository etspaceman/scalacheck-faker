package faker.internet

import org.scalacheck.Arbitrary

import faker.ResourceLoader
import faker.name.LastName

final case class DomainWord private (value: String) extends AnyVal

object DomainWord {
  implicit def domainWordArbitrary(implicit
      loader: ResourceLoader
  ): Arbitrary[DomainWord] =
    Arbitrary(
      Arbitrary
        .arbitrary[LastName]
        .map(ln =>
          FakerIDN.toASCII(
            ln.value.toLowerCase().replaceAll("'", "").replaceAll(" ", "")
          )
        )
        .map(DomainWord.apply)
    )
}
