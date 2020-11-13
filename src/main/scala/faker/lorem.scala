package faker

import io.estatico.newtype.macros.newtype
import io.estatico.newtype.ops._
import org.scalacheck.{Arbitrary, Gen}
import pureconfig.ConfigReader

object lorem {
  @newtype final case class LoremWord private (value: String)

  object LoremWord {
    def loremWords(implicit loader: ResourceLoader): Seq[LoremWord] =
      loader.loadKey[Seq[LoremWord]]("lorem.words")
    implicit def loremWordArbitrary(implicit
        loader: ResourceLoader
    ): Arbitrary[LoremWord] =
      Arbitrary(Gen.oneOf(loremWords))

    implicit val loremWordConfigReader: ConfigReader[LoremWord] =
      ConfigReader[String].coerce
  }

  @newtype final case class LoremCharacters private (value: String)

  object LoremCharacters {
    def loremCharacters(implicit loader: ResourceLoader): Set[Char] =
      LoremWord.loremWords.mkString.toSet
    implicit def loremCharactersArbitrary(implicit
        loader: ResourceLoader
    ): Arbitrary[LoremCharacters] =
      Arbitrary {
        for {
          characterCount <- Gen.choose(2, 255)
          characters <- Gen.listOfN(characterCount, Gen.oneOf(loremCharacters))
        } yield characters.mkString
      }.coerce
  }

  @newtype final case class LoremWords private (value: String)

  object LoremWords {
    implicit def loremWordsArbitrary(implicit
        loader: ResourceLoader
    ): Arbitrary[LoremWords] =
      Arbitrary {
        for {
          wordCount <- Gen.choose(4, 7)
          words <- Gen.listOfN(wordCount, Arbitrary.arbitrary[LoremWord])
        } yield words.map(_.value).mkString(" ")
      }.coerce
  }

  @newtype final case class LoremSentence private (value: String)

  object LoremSentence {
    implicit def loremSentenceArbitrary(implicit
        loader: ResourceLoader
    ): Arbitrary[LoremSentence] =
      Arbitrary(
        Arbitrary.arbitrary[LoremWords].map(x => s"${x.value}.")
      ).coerce
  }

  @newtype final case class LoremParagraph private (value: String)

  object LoremParagraph {
    implicit def loremParagraphArbitrary(implicit
        loader: ResourceLoader
    ): Arbitrary[LoremParagraph] =
      Arbitrary {
        for {
          sentenceCount <- Gen.choose(3, 6)
          sentences <-
            Gen.listOfN(sentenceCount, Arbitrary.arbitrary[LoremSentence])
        } yield sentences.map(_.value).mkString(" ")
      }.coerce
  }

  @newtype final case class LoremParagraphs private (value: String)

  object LoremParagraphs {
    implicit def loremParagraphsArbitrary(implicit
        loader: ResourceLoader
    ): Arbitrary[LoremParagraphs] =
      Arbitrary {
        for {
          paragraphCount <- Gen.choose(2, 4)
          paragraphs <-
            Gen.listOfN(paragraphCount, Arbitrary.arbitrary[LoremParagraph])
        } yield paragraphs.map(_.value).mkString("\n")
      }.coerce
  }
}
