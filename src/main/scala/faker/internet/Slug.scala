package faker.internet

import org.scalacheck.Arbitrary

final case class Slug private (value: String) extends AnyVal

object Slug {
  implicit val slugArbitrary: Arbitrary[Slug] = Arbitrary(
    ???
  )
}
