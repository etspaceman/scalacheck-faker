package faker.internet

import org.apache.commons.lang3.StringUtils
import org.scalacheck.{Arbitrary, Gen}

import faker.ResourceLoader
import faker.name.UserName

final case class EmailAddress private (value: String) extends AnyVal

object EmailAddress {
  def freeEmailDomains(implicit loader: ResourceLoader): Seq[String] =
    loader.loadStringList("internet.free-email-domains")
  implicit def emailAddressArbitrary(implicit
      loader: ResourceLoader
  ): Arbitrary[EmailAddress] =
    Arbitrary(
      for {
        localPart <-
          Arbitrary
            .arbitrary[UserName]
            .map(x => StringUtils.stripAccents(x.value))
        domain <-
          Gen
            .oneOf(freeEmailDomains)
            .map(FakerIDN.toASCII)
      } yield EmailAddress(s"$localPart@$domain")
    )
}
