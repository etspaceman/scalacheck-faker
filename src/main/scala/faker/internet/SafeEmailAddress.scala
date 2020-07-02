package faker.internet

import org.apache.commons.lang3.StringUtils
import org.scalacheck.{Arbitrary, Gen}

import faker.ResourceLoader
import faker.name.UserName

final case class SafeEmailAddress private (value: String) extends AnyVal

object SafeEmailAddress {
  def safeEmailDomains(implicit loader: ResourceLoader): Seq[String] =
    loader.loadStringList("internet.safe-email-domains")
  implicit def safeEmailAddressArbitrary(implicit
      loader: ResourceLoader
  ): Arbitrary[SafeEmailAddress] =
    Arbitrary(
      for {
        localPart <-
          Arbitrary
            .arbitrary[UserName]
            .map(x => x.copy(value = StringUtils.stripAccents(x.value)))
        domain <- Gen.oneOf(safeEmailDomains).map(FakerIDN.toASCII)
      } yield SafeEmailAddress(s"$localPart@$domain")
    )
}
