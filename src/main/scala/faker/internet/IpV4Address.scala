package faker.internet

import org.scalacheck.Arbitrary

final case class IpV4Address private (value: String) extends AnyVal

object IpV4Address {
  implicit val ipV4AddressArbitrary: Arbitrary[IpV4Address] = Arbitrary(
    ???
  )
}
