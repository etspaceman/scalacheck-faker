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

object name {
  type FirstName = FirstName.Type

  object FirstName extends Newtype[String] { self =>
    def firstNames(implicit loader: ResourceLoader): Seq[FirstName] =
      loader.loadKey[Seq[FirstName]]("name.first.names")

    implicit def firstNameArbitrary(implicit
        loader: ResourceLoader
    ): Arbitrary[FirstName] =
      Arbitrary(Gen.oneOf(firstNames))

    implicit val firstNameConfigReader: ConfigReader[FirstName] =
      ConfigReader[String].map(self.apply)
  }

  type FullName = FullName.Type

  object FullName extends Newtype[String] { self =>
    implicit def fullNameArbitrary(implicit
        loader: ResourceLoader
    ): Arbitrary[FullName] =
      Arbitrary(
        loader
          .loadKey[StringGenBuilder]("name.full-name-builder")
          .gen
          .map(self.apply)
      )
  }

  type FullNameWithMiddle = FullNameWithMiddle.Type

  object FullNameWithMiddle extends Newtype[String] { self =>
    implicit def fullNameWithMiddleArbitrary(implicit
        loader: ResourceLoader
    ): Arbitrary[FullNameWithMiddle] =
      Arbitrary(
        loader
          .loadKey[StringGenBuilder]("name.full-name-with-middle-builder")
          .gen
          .map(self.apply)
      )
  }

  type LastName = LastName.Type

  object LastName extends Newtype[String] { self =>
    def lastNames(implicit loader: ResourceLoader): Seq[LastName] =
      loader.loadKey[Seq[LastName]]("name.last.names")
    implicit def lastNameArbitrary(implicit
        loader: ResourceLoader
    ): Arbitrary[LastName] =
      Arbitrary(Gen.oneOf(lastNames))

    implicit val lastNameConfigReader: ConfigReader[LastName] =
      ConfigReader[String].map(self.apply)
  }

  type Prefix = Prefix.Type

  object Prefix extends Newtype[String] { self =>
    def prefixes(implicit loader: ResourceLoader): Seq[Prefix] =
      loader.loadKey[Seq[Prefix]]("name.prefixes")
    implicit def prefixArbitrary(implicit
        loader: ResourceLoader
    ): Arbitrary[Prefix] =
      Arbitrary(Gen.oneOf(prefixes))

    implicit val prefixConfigReader: ConfigReader[Prefix] =
      ConfigReader[String].map(self.apply)
  }

  type Suffix = Suffix.Type

  object Suffix extends Newtype[String] { self =>
    def suffixes(implicit loader: ResourceLoader): Seq[Suffix] =
      loader.loadKey[Seq[Suffix]]("name.suffixes")
    implicit def suffixArbitrary(implicit
        loader: ResourceLoader
    ): Arbitrary[Suffix] = Arbitrary(Gen.oneOf(suffixes))

    implicit val suffixConfigReader: ConfigReader[Suffix] =
      ConfigReader[String].map(self.apply)
  }

  type Title = Title.Type

  object Title extends Newtype[String] { self =>
    def titleDescriptors(implicit loader: ResourceLoader): Seq[String] =
      loader.loadKey[Seq[String]]("name.title.descriptors")
    def titleLevels(implicit loader: ResourceLoader): Seq[String] =
      loader.loadKey[Seq[String]]("name.title.levels")
    def titleJobs(implicit loader: ResourceLoader): Seq[String] =
      loader.loadKey[Seq[String]]("name.title.jobs")
    implicit def titleArbitrary(implicit
        loader: ResourceLoader
    ): Arbitrary[Title] =
      Arbitrary {
        (for {
          descriptor <- Gen.oneOf(titleDescriptors)
          level <- Gen.oneOf(titleLevels)
          job <- Gen.oneOf(titleJobs)
        } yield s"$descriptor $level $job").map(self.apply)
      }
  }

  type UserName = UserName.Type

  object UserName extends Newtype[String] { self =>
    implicit def userNameArbitrary(implicit
        loader: ResourceLoader
    ): Arbitrary[UserName] =
      Arbitrary {
        (for {
          firstName <-
            Arbitrary
              .arbitrary[FirstName]
              .map(
                _.value
                  .replaceAll("'", "")
                  .replaceAll(" ", "")
                  .replaceAll("\\.", "")
                  .toLowerCase()
              )
          lastName <-
            Arbitrary
              .arbitrary[LastName]
              .map(
                _.value
                  .replaceAll("'", "")
                  .replaceAll(" ", "")
                  .replaceAll("\\.", "")
                  .toLowerCase()
              )
        } yield s"$firstName.$lastName").map(self.apply)
      }
  }
}
