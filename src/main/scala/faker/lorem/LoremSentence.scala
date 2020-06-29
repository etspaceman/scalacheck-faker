package faker.lorem

import org.scalacheck.Arbitrary

final case class LoremSentence private (value: String) extends AnyVal

object LoremSentence {
  implicit val loremSentenceArbitrary: Arbitrary[LoremSentence] = Arbitrary(
    Arbitrary.arbitrary[LoremWords].map(x => LoremSentence(s"${x.value}."))
  )
}
