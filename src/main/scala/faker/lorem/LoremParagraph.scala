package faker.lorem

import org.scalacheck.{Arbitrary, Gen}

final case class LoremParagraph private (value: String) extends AnyVal

object LoremParagraph {
  implicit val loremParagraphArbitrary: Arbitrary[LoremParagraph] = Arbitrary {
    for {
      sentenceCount <- Gen.choose(3, 6)
      sentences <- Gen.listOfN(sentenceCount, Arbitrary.arbitrary[LoremSentence])
    } yield LoremParagraph(sentences.map(_.value).mkString(" "))
  }
}
