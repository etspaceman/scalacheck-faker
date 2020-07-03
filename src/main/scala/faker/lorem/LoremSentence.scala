package faker.lorem

import org.scalacheck.Arbitrary

import faker.ResourceLoader

final case class LoremSentence private (value: String) extends AnyVal

object LoremSentence {
  implicit def loremSentenceArbitrary(implicit
      loader: ResourceLoader
  ): Arbitrary[LoremSentence] =
    Arbitrary(
      Arbitrary.arbitrary[LoremWords].map(x => LoremSentence(s"${x.value}."))
    )
}
