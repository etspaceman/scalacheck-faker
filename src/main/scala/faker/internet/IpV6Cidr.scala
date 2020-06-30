package faker.internet

import org.scalacheck.Arbitrary

final case class IpV6Cidr private (value: String) extends AnyVal

object IpV6Cidr {
  implicit val ipV6CidrArbitrary: Arbitrary[IpV6Cidr] = Arbitrary(
    ???
  )
}
