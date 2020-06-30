package faker.lorem

import org.scalacheck.{Arbitrary, Gen}

final case class LoremParagraphs private (value: String) extends AnyVal

object LoremParagraphs {
  implicit val loremParagraphsArbitrary: Arbitrary[LoremParagraphs] =
    Arbitrary {
      for {
        paragraphCount <- Gen.choose(2, 4)
        paragraphs <-
          Gen.listOfN(paragraphCount, Arbitrary.arbitrary[LoremParagraph])
      } yield LoremParagraphs(paragraphs.map(_.value).mkString("\n"))
    }
}
