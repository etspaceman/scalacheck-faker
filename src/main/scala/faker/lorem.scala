/*
 * Copyright (c) 2020 etspaceman
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of
 * this software and associated documentation files (the "Software"), to deal in
 * the Software without restriction, including without limitation the rights to
 * use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of
 * the Software, and to permit persons to whom the Software is furnished to do so,
 * subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
 * FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 * COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER
 * IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
 * CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package faker

import org.scalacheck.{Arbitrary, Gen}
import pureconfig.ConfigReader

object lorem {
  type LoremWord = LoremWord.Type

  object LoremWord extends Newtype[String] { self =>
    def loremWords(implicit loader: ResourceLoader): Seq[LoremWord] =
      loader.loadKey[Seq[LoremWord]]("lorem.words")
    implicit def loremWordArbitrary(implicit
        loader: ResourceLoader
    ): Arbitrary[LoremWord] =
      Arbitrary(Gen.oneOf(loremWords))

    implicit val loremWordConfigReader: ConfigReader[LoremWord] =
      ConfigReader[String].map(self.apply)
  }

  type LoremCharacters = LoremCharacters.Type

  object LoremCharacters extends Newtype[String] { self =>
    def loremCharacters(implicit loader: ResourceLoader): Set[Char] =
      LoremWord.loremWords.mkString.toSet
    implicit def loremCharactersArbitrary(implicit
        loader: ResourceLoader
    ): Arbitrary[LoremCharacters] =
      Arbitrary {
        (for {
          characterCount <- Gen.choose(2, 255)
          characters <- Gen.listOfN(characterCount, Gen.oneOf(loremCharacters))
        } yield characters.mkString).map(self.apply)
      }
  }

  type LoremWords = LoremWords.Type

  object LoremWords extends Newtype[String] { self =>
    implicit def loremWordsArbitrary(implicit
        loader: ResourceLoader
    ): Arbitrary[LoremWords] =
      Arbitrary {
        (for {
          wordCount <- Gen.choose(4, 7)
          words <- Gen.listOfN(wordCount, Arbitrary.arbitrary[LoremWord])
        } yield words.map(_.value).mkString(" ")).map(self.apply)
      }
  }

  type LoremSentence = LoremSentence.Type

  object LoremSentence extends Newtype[String] { self =>
    implicit def loremSentenceArbitrary(implicit
        loader: ResourceLoader
    ): Arbitrary[LoremSentence] =
      Arbitrary(
        Arbitrary.arbitrary[LoremWords].map(x => s"${x.value}.").map(self.apply)
      )
  }

  type LoremParagraph = LoremParagraph.Type

  object LoremParagraph extends Newtype[String] { self =>
    implicit def loremParagraphArbitrary(implicit
        loader: ResourceLoader
    ): Arbitrary[LoremParagraph] =
      Arbitrary {
        (for {
          sentenceCount <- Gen.choose(3, 6)
          sentences <-
            Gen.listOfN(sentenceCount, Arbitrary.arbitrary[LoremSentence])
        } yield sentences.map(_.value).mkString(" ")).map(self.apply)
      }
  }

  type LoremParagraphs = LoremParagraphs.Type

  object LoremParagraphs extends Newtype[String] { self =>
    implicit def loremParagraphsArbitrary(implicit
        loader: ResourceLoader
    ): Arbitrary[LoremParagraphs] =
      Arbitrary {
        (for {
          paragraphCount <- Gen.choose(2, 4)
          paragraphs <-
            Gen.listOfN(paragraphCount, Arbitrary.arbitrary[LoremParagraph])
        } yield paragraphs.map(_.value).mkString("\n")).map(self.apply)
      }
  }
}
