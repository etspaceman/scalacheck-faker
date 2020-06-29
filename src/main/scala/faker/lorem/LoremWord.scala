package faker.lorem

import org.scalacheck.{Arbitrary, Gen}

final case class LoremWord private (value: String) extends AnyVal

object LoremWord {
  implicit val loremWordArbitrary: Arbitrary[LoremWord] = Arbitrary(Gen.oneOf(Lorem.words))
}
