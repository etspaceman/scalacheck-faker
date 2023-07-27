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

object cat {
  type Name = Name.Type

  object Name extends Newtype[String] { self =>
    def names(implicit loader: ResourceLoader): Seq[Name] =
      loader.loadKey[Seq[Name]]("cat.names")

    implicit def nameArbitrary(implicit
        loader: ResourceLoader
    ): Arbitrary[Name] =
      Arbitrary(Gen.oneOf(names))

    implicit val nameConfigReader: ConfigReader[Name] =
      ConfigReader[String].map(self.apply)
  }

  type Breed = Breed.Type

  object Breed extends Newtype[String] { self =>
    def breeds(implicit loader: ResourceLoader): Seq[Breed] =
      loader.loadKey[Seq[Breed]]("cat.breeds")

    implicit def breedArbitrary(implicit
        loader: ResourceLoader
    ): Arbitrary[Breed] =
      Arbitrary(Gen.oneOf(breeds))

    implicit val breedConfigReader: ConfigReader[Breed] =
      ConfigReader[String].map(self.apply)
  }

  type Registry = Registry.Type

  object Registry extends Newtype[String] { self =>
    def registries(implicit loader: ResourceLoader): Seq[Registry] =
      loader.loadKey[Seq[Registry]]("cat.registries")

    implicit def registryArbitrary(implicit
        loader: ResourceLoader
    ): Arbitrary[Registry] =
      Arbitrary(Gen.oneOf(registries))

    implicit val registryConfigReader: ConfigReader[Registry] =
      ConfigReader[String].map(self.apply)
  }
}
