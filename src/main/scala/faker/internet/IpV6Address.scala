package faker.internet

import org.scalacheck.{Arbitrary, Gen}

final case class IpV6Address private (value: String) extends AnyVal

object IpV6Address {
  def ipV6Char: Gen[String] = Gen.choose(0, 15).map(Integer.toHexString)
  def ipV6Part: Gen[String] = Gen.listOfN(4, ipV6Char).map(_.mkString)
  implicit val ipV6AddressArbitrary: Arbitrary[IpV6Address] = Arbitrary(
    Gen.listOfN(8, ipV6Part).map(x => IpV6Address(x.mkString(":")))
  )
}
