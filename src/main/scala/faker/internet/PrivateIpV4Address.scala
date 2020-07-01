package faker.internet

import org.scalacheck.{Arbitrary, Gen}

final case class PrivateIpV4Address private (value: String) extends AnyVal

object PrivateIpV4Address {
  private val privateFirstParts: Seq[Int] = Seq(10, 127, 169, 192, 172)
  private val privateSecondPartsFor172: Seq[Int] =
    Seq(16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31)
  implicit val privateIpV4AddressArbitrary: Arbitrary[PrivateIpV4Address] =
    Arbitrary(
      for {
        part1 <- Gen.oneOf(privateFirstParts)
        part2 <- part1 match {
          case 172 => Gen.oneOf(privateSecondPartsFor172)
          case 192 => Gen.const(168)
          case 169 => Gen.const(254)
          case _   => Gen.choose(2, 255)
        }
        part3 <- Gen.choose(0, 255)
        part4 <- Gen.choose(0, 255)
      } yield PrivateIpV4Address(s"$part1.$part2.$part3.$part4")
    )
}
