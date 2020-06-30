package faker.internet

import org.scalacheck.Arbitrary

final case class IpV6Address private (value: String) extends AnyVal

object IpV6Address {
  implicit val ipV6AddressArbitrary: Arbitrary[IpV6Address] = Arbitrary(
    ???
  )
}
