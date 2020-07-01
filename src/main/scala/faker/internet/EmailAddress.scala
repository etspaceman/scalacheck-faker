package faker.internet

import faker.ResourceLoader
import faker.name.UserName
import org.apache.commons.lang3.StringUtils
import org.scalacheck.{Arbitrary, Gen}

final case class EmailAddress private (value: String) extends AnyVal

object EmailAddress {
  private val freeEmailDomains: Seq[String] =
    ResourceLoader.loadStringList("internet.free-email-domains")
  implicit val emailAddressArbitrary: Arbitrary[EmailAddress] = Arbitrary(
    for {
      localPart <-
        Arbitrary
          .arbitrary[UserName]
          .map(x => x.copy(value = StringUtils.stripAccents(x.value)))
      domain <- Gen.oneOf(freeEmailDomains).map(FakerIDN.toASCII)
    } yield EmailAddress(s"$localPart@$domain")
  )
}
