package faker.lorem

import org.scalacheck.{Arbitrary, Gen}

import faker.ResourceLoader

final case class LoremParagraph private (value: String) extends AnyVal

object LoremParagraph {
  implicit def loremParagraphArbitrary(implicit
      loader: ResourceLoader
  ): Arbitrary[LoremParagraph] =
    Arbitrary {
      for {
        sentenceCount <- Gen.choose(3, 6)
        sentences <-
          Gen.listOfN(sentenceCount, Arbitrary.arbitrary[LoremSentence])
      } yield LoremParagraph(sentences.map(_.value).mkString(" "))
    }
}
