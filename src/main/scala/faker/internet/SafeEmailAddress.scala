package faker.internet

import org.scalacheck.Arbitrary

final case class SafeEmailAddress private (value: String) extends AnyVal

object SafeEmailAddress {
  implicit val safeEmailAddressArbitrary: Arbitrary[SafeEmailAddress] =
    Arbitrary(
      ???
    )
}
