package faker.internet

import org.scalacheck.{Arbitrary, Gen}

final case class IpV4Cidr private (value: String) extends AnyVal

object IpV4Cidr {
  implicit val ipV4CidrArbitrary: Arbitrary[IpV4Cidr] =
    Arbitrary(
      for {
        ipV4Address <- Arbitrary.arbitrary[IpV4Address]
        cidrPart <- Gen.choose(1, 31)
      } yield IpV4Cidr(s"${ipV4Address.value}/$cidrPart")
    )
}
