package faker.internet

import org.scalacheck.{Arbitrary, Gen}

final case class IpV6Cidr private (value: String) extends AnyVal

object IpV6Cidr {
  implicit val ipV6CidrArbitrary: Arbitrary[IpV6Cidr] = Arbitrary(
    for {
      ipv6 <- Arbitrary.arbitrary[IpV6Address]
      cidrPart <- Gen.choose(1, 127)
    } yield IpV6Cidr(s"${ipv6.value}/$cidrPart")
  )
}
