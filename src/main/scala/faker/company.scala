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

import faker.internet.{DomainSuffix, FakerIDN}

object company {
  type BS = BS.Type

  object BS extends Newtype[String] { self =>
    implicit def bsArbitrary(implicit
        loader: ResourceLoader
    ): Arbitrary[BS] =
      Arbitrary(
        loader
          .loadKey[StringGenBuilder]("company.bs-builder")
          .gen
          .map(self.apply)
      )
  }

  type BuzzWord = BuzzWord.Type

  object BuzzWord extends Newtype[String] { self =>
    def buzzWords(implicit loader: ResourceLoader): Seq[BuzzWord] =
      loader.loadKey[Seq[BuzzWord]]("company.buzz-words")
    implicit def buzzWordArbitrary(implicit
        loader: ResourceLoader
    ): Arbitrary[BuzzWord] = Arbitrary(Gen.oneOf(buzzWords))

    implicit val buzzWordConfigReader: ConfigReader[BuzzWord] =
      ConfigReader[String].map(self.apply)
  }

  type CatchPhrase = CatchPhrase.Type

  object CatchPhrase extends Newtype[String] { self =>
    implicit def catchPhraseArbitrary(implicit
        loader: ResourceLoader
    ): Arbitrary[CatchPhrase] =
      Arbitrary(
        loader
          .loadKey[StringGenBuilder]("company.catch-phrase-builder")
          .gen
          .map(self.apply)
      )
  }

  type CompanyName = CompanyName.Type

  object CompanyName extends Newtype[String] { self =>
    implicit def companyNameArbitrary(implicit
        loader: ResourceLoader
    ): Arbitrary[CompanyName] =
      Arbitrary(
        loader
          .loadKey[StringGenBuilder]("company.company-name-builder")
          .gen
          .map(self.apply)
      )
  }

  type CompanyDomainName = CompanyDomainName.Type

  object CompanyDomainName extends Newtype[String] { self =>
    implicit def companyDomainNameArbitrary(implicit
        loader: ResourceLoader
    ): Arbitrary[CompanyDomainName] =
      Arbitrary(
        Arbitrary
          .arbitrary[CompanyName]
          .map(ln =>
            FakerIDN.toASCII(
              ln.value
                .toLowerCase()
                .replaceAll("'", "")
                .replaceAll(" ", "")
                .replaceAll(",", "\\.")
            )
          )
          .map(self.apply)
      )
  }

  type CompanySuffix = CompanySuffix.Type

  object CompanySuffix extends Newtype[String] { self =>
    def companySuffixes(implicit loader: ResourceLoader): Seq[CompanySuffix] =
      loader.loadKey[Seq[CompanySuffix]]("company.suffixes")

    implicit def companySuffixArbitrary(implicit
        loader: ResourceLoader
    ): Arbitrary[CompanySuffix] =
      Arbitrary(Gen.oneOf(companySuffixes))

    implicit val companySuffixConfigReader: ConfigReader[CompanySuffix] =
      ConfigReader[String].map(self.apply)
  }

  type CompanyUrl = CompanyUrl.Type

  object CompanyUrl extends Newtype[String] { self =>
    implicit def companyUrlArbitrary(implicit
        loader: ResourceLoader
    ): Arbitrary[CompanyUrl] =
      Arbitrary(
        (for {
          domainName <- Arbitrary.arbitrary[CompanyDomainName]
          domainSuffix <- Arbitrary.arbitrary[DomainSuffix]
        } yield s"www.${domainName.value}.${domainSuffix.value}")
          .map(self.apply)
      )
  }

  type Industry = Industry.Type

  object Industry extends Newtype[String] { self =>
    def industries(implicit loader: ResourceLoader): Seq[Industry] =
      loader.loadKey[Seq[Industry]]("company.industries")
    implicit def industryArbitrary(implicit
        loader: ResourceLoader
    ): Arbitrary[Industry] = Arbitrary(Gen.oneOf(industries))

    implicit val industryConfigReader: ConfigReader[Industry] =
      ConfigReader[String].map(self.apply)
  }

  type Logo = Logo.Type

  object Logo extends Newtype[String] { self =>
    implicit val logoArbitrary: Arbitrary[Logo] =
      Arbitrary(
        Gen
          .choose(1, 13)
          .map(x =>
            s"https://pigment.github.io/fake-logos/logos/medium/color/$x.png"
          )
          .map(self.apply)
      )
  }

  type Profession = Profession.Type

  object Profession extends Newtype[String] { self =>
    def professions(implicit loader: ResourceLoader): Seq[Profession] =
      loader.loadKey[Seq[Profession]]("company.professions")
    implicit def professionArbitrary(implicit
        loader: ResourceLoader
    ): Arbitrary[Profession] = Arbitrary(Gen.oneOf(professions))

    implicit val professionConfigReader: ConfigReader[Profession] =
      ConfigReader[String].map(self.apply)
  }
}
