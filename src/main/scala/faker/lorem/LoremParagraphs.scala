package faker.lorem

import org.scalacheck.{Arbitrary, Gen}

import faker.ResourceLoader

final case class LoremParagraphs private (value: String) extends AnyVal

object LoremParagraphs {
  implicit def loremParagraphsArbitrary(implicit
      loader: ResourceLoader
  ): Arbitrary[LoremParagraphs] =
    Arbitrary {
      for {
        paragraphCount <- Gen.choose(2, 4)
        paragraphs <-
          Gen.listOfN(paragraphCount, Arbitrary.arbitrary[LoremParagraph])
      } yield LoremParagraphs(paragraphs.map(_.value).mkString("\n"))
    }
}
