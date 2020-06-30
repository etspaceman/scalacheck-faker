package faker.internet

import org.scalacheck.Arbitrary

final case class Password private (value: String) extends AnyVal

object Password {
  implicit val passwordArbitrary: Arbitrary[Password] = Arbitrary(
    ???
  )
}
