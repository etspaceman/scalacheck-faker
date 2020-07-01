package faker.internet

import org.scalacheck.{Arbitrary, Gen}

final case class MacAddress private (value: String) extends AnyVal

object MacAddress {
  private def macAddressChar: Gen[String] =
    Gen.choose(0, 15).map(Integer.toHexString)
  private def macAddressSection =
    macAddressChar.flatMap(x => macAddressChar.map(y => x + y))
  implicit val macAddressArbitrary: Arbitrary[MacAddress] = Arbitrary(
    Gen.listOfN(6, macAddressSection).map(x => MacAddress(x.mkString(":")))
  )
}
