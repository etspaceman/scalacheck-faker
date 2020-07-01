package faker.internet

import faker.ResourceLoader
import faker.name.UserName
import org.apache.commons.lang3.StringUtils
import org.scalacheck.{Arbitrary, Gen}

final case class SafeEmailAddress private (value: String) extends AnyVal

object SafeEmailAddress {
  private val safeEmailDomains: Seq[String] =
    ResourceLoader.loadStringList("internet.safe-email-domains")
  implicit val safeEmailAddressArbitrary: Arbitrary[SafeEmailAddress] =
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
