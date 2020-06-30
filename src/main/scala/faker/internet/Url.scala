package faker.internet

import org.scalacheck.Arbitrary

final case class Url private (value: String) extends AnyVal

object Url {
  implicit val urlArbitrary: Arbitrary[Url] = Arbitrary(
    ???
  )
}
