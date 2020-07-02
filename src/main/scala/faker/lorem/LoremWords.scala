package faker.lorem

import org.scalacheck.{Arbitrary, Gen}

import faker.ResourceLoader

final case class LoremWords private (value: String) extends AnyVal

object LoremWords {
  implicit def loremWordsArbitrary(implicit
      loader: ResourceLoader
  ): Arbitrary[LoremWords] =
    Arbitrary {
      for {
        wordCount <- Gen.choose(4, 7)
        words <- Gen.listOfN(wordCount, Arbitrary.arbitrary[LoremWord])
      } yield LoremWords(words.map(_.value).mkString(" "))
    }
}
