package faker.internet

import org.scalacheck.Arbitrary

final case class IpV4Cidr private (value: String) extends AnyVal

object IpV4Cidr {
  implicit val ipV4CidrArbitrary: Arbitrary[IpV4Cidr] = Arbitrary(
    ???
  )
}
