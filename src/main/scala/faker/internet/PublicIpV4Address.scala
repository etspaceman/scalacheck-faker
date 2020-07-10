package faker.internet

import org.scalacheck.{Arbitrary, Gen}

final case class PublicIpV4Address private (value: String) extends AnyVal

object PublicIpV4Address {
  private val privateFirstParts: Seq[Int] = Seq(10, 127, 169, 192, 172)
  private val publicIpV4FirstPart: Gen[Int] =
    Gen.choose(2, 255).retryUntil(!privateFirstParts.contains(_))
  implicit val publicIpV4AddressArbitrary: Arbitrary[PublicIpV4Address] =
    Arbitrary(
      for {
        ipAddress <- Arbitrary.arbitrary[IpV4Address]
        origParts = ipAddress.value.split("\\.").toList.map(_.toInt)
        publicFirstPart <- publicIpV4FirstPart
        newParts = publicFirstPart +: origParts.drop(1)
      } yield PublicIpV4Address(newParts.mkString("."))
    )
}
