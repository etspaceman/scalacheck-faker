package faker.internet

import org.scalacheck.Arbitrary

final case class MacAddress private (value: String) extends AnyVal

object MacAddress {
  implicit val macAddressArbitrary: Arbitrary[MacAddress] = Arbitrary(
    ???
  )
}
