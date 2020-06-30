package faker.internet

import org.scalacheck.Arbitrary

final case class PrivateIpV4Address private (value: String) extends AnyVal

object PrivateIpV4Address {
  implicit val privateIpV4AddressArbitrary: Arbitrary[PrivateIpV4Address] =
    Arbitrary(
      ???
    )
}
