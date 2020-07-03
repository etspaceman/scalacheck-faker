package faker.internet

import org.scalacheck.{Arbitrary, Gen}

final case class IpV4Address private (value: String) extends AnyVal

object IpV4Address {
  implicit val ipV4AddressArbitrary: Arbitrary[IpV4Address] =
    Arbitrary(
      for {
        part1 <- Gen.choose(2, 255)
        part2 <- Gen.choose(0, 255)
        part3 <- Gen.choose(0, 255)
        part4 <- Gen.choose(0, 255)
      } yield IpV4Address(s"$part1.$part2.$part3.$part4")
    )
}
