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

object programmingLanguage {
  type ProgrammingLanguageName = ProgrammingLanguageName.Type

  object ProgrammingLanguageName extends Newtype[String] { self =>
    def programmingLanguageNames(implicit
        loader: ResourceLoader
    ): Seq[ProgrammingLanguageName] =
      loader.loadKey[Seq[ProgrammingLanguageName]]("programming_language.names")

    implicit def programminglanguageNameArbitrary(implicit
        loader: ResourceLoader
    ): Arbitrary[ProgrammingLanguageName] =
      Arbitrary(Gen.oneOf(programmingLanguageNames))

    implicit val programminglanguageNameConfigReader
        : ConfigReader[ProgrammingLanguageName] =
      ConfigReader[String].map(self.apply)
  }

  type ProgrammingLanguageCreator = ProgrammingLanguageCreator.Type

  object ProgrammingLanguageCreator extends Newtype[String] { self =>
    def programmingLanguageCreators(implicit
        loader: ResourceLoader
    ): Seq[ProgrammingLanguageCreator] =
      loader.loadKey[Seq[ProgrammingLanguageCreator]](
        "programming_language.creators"
      )

    implicit def programmingLanguageCreatorArbitrary(implicit
        loader: ResourceLoader
    ): Arbitrary[ProgrammingLanguageCreator] =
      Arbitrary(Gen.oneOf(programmingLanguageCreators))

    implicit val programmingLanguageCreatorConfigReader
        : ConfigReader[ProgrammingLanguageCreator] =
      ConfigReader[String].map(self.apply)
  }
}
