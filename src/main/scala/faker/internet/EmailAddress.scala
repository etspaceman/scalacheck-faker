package faker.internet

import org.scalacheck.Arbitrary

final case class EmailAddress private (value: String) extends AnyVal

object EmailAddress {
  implicit val emailAddressArbitrary: Arbitrary[EmailAddress] = Arbitrary(
    ???
  )
}
