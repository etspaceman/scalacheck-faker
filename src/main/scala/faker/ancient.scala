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

object ancient {
  type God = God.Type

  object God extends Newtype[String] { self =>
    def gods(implicit loader: ResourceLoader): Seq[God] =
      loader.loadKey[Seq[God]]("ancient.gods")

    implicit def godArbitrary(implicit
        loader: ResourceLoader
    ): Arbitrary[God] =
      Arbitrary(Gen.oneOf(gods))

    implicit val godConfigReader: ConfigReader[God] =
      ConfigReader[String].map(self.apply)
  }

  type Primordial = Primordial.Type

  object Primordial extends Newtype[String] { self =>
    def primordials(implicit loader: ResourceLoader): Seq[Primordial] =
      loader.loadKey[Seq[Primordial]]("ancient.primordials")

    implicit def primordialArbitrary(implicit
        loader: ResourceLoader
    ): Arbitrary[Primordial] =
      Arbitrary(Gen.oneOf(primordials))

    implicit val primordialConfigReader: ConfigReader[Primordial] =
      ConfigReader[String].map(self.apply)
  }

  type Titan = Titan.Type

  object Titan extends Newtype[String] { self =>
    def titans(implicit loader: ResourceLoader): Seq[Titan] =
      loader.loadKey[Seq[Titan]]("ancient.titans")

    implicit def primordialArbitrary(implicit
        loader: ResourceLoader
    ): Arbitrary[Titan] =
      Arbitrary(Gen.oneOf(titans))

    implicit val titanConfigReader: ConfigReader[Titan] =
      ConfigReader[String].map(self.apply)
  }

  type Hero = Hero.Type

  object Hero extends Newtype[String] { self =>
    def heroes(implicit loader: ResourceLoader): Seq[Hero] =
      loader.loadKey[Seq[Hero]]("ancient.heroes")

    implicit def heroArbitrary(implicit
        loader: ResourceLoader
    ): Arbitrary[Hero] =
      Arbitrary(Gen.oneOf(heroes))

    implicit val heroConfigReader: ConfigReader[Hero] =
      ConfigReader[String].map(self.apply)
  }
}
