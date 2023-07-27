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

object gender {

  type GenderBinaryType = GenderBinaryType.Type

  object GenderBinaryType extends Newtype[String] { self =>
    def genderBinaryTypes(implicit
        loader: ResourceLoader
    ): Seq[GenderBinaryType] =
      loader.loadKey[Seq[GenderBinaryType]]("gender.binary-types")

    implicit def genderBinaryTypeArbitrary(implicit
        loader: ResourceLoader
    ): Arbitrary[GenderBinaryType] =
      Arbitrary(Gen.oneOf(genderBinaryTypes))

    implicit val genderBinaryTypeConfigReader: ConfigReader[GenderBinaryType] =
      ConfigReader[String].map(self.apply)
  }

  type GenderShortBinaryType = GenderShortBinaryType.Type

  object GenderShortBinaryType extends Newtype[String] { self =>
    def genderShortBinaryTypes(implicit
        loader: ResourceLoader
    ): Seq[GenderShortBinaryType] =
      loader.loadKey[Seq[GenderShortBinaryType]]("gender.short-binary-types")

    implicit def genderShortBinaryTypeArbitrary(implicit
        loader: ResourceLoader
    ): Arbitrary[GenderShortBinaryType] =
      Arbitrary(Gen.oneOf(genderShortBinaryTypes))

    implicit val genderShortBinaryTypeConfigReader
        : ConfigReader[GenderShortBinaryType] =
      ConfigReader[String].map(self.apply)
  }

  type GenderType = GenderType.Type

  object GenderType extends Newtype[String] { self =>
    def genderTypes(implicit loader: ResourceLoader): Seq[GenderType] =
      loader.loadKey[Seq[GenderType]]("gender.types")

    implicit def genderTypeArbitrary(implicit
        loader: ResourceLoader
    ): Arbitrary[GenderType] =
      Arbitrary(Gen.oneOf(genderTypes))

    implicit val genderTypeConfigReader: ConfigReader[GenderType] =
      ConfigReader[String].map(self.apply)
  }

}
