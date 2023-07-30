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

import java.time.Instant

import org.scalacheck.{Arbitrary, Gen}
import pureconfig.ConfigReader

object backToTheFuture {
  type Character = Character.Type

  object Character extends Newtype[String] { self =>
    def characters(implicit loader: ResourceLoader): Seq[Character] =
      loader.loadKey[Seq[Character]]("back_to_the_future.characters")

    implicit def characterArbitrary(implicit
        loader: ResourceLoader
    ): Arbitrary[Character] =
      Arbitrary(Gen.oneOf(characters))

    implicit val characterConfigReader: ConfigReader[Character] =
      ConfigReader[String].map(self.apply)
  }

  type Quote = Quote.Type

  object Quote extends Newtype[String] { self =>
    def quotes(implicit loader: ResourceLoader): Seq[Quote] =
      loader.loadKey[Seq[Quote]]("back_to_the_future.quotes")

    implicit def quoteArbitrary(implicit
        loader: ResourceLoader
    ): Arbitrary[Quote] =
      Arbitrary(Gen.oneOf(quotes))

    implicit val quoteConfigReader: ConfigReader[Quote] =
      ConfigReader[String].map(self.apply)
  }

  type Date = Date.Type

  object Date extends Newtype[Instant] { self =>
    def dates: Seq[Instant] =
      Seq(
        Instant.parse("1955-11-05T00:00:00.00Z"),
        Instant.parse("1955-11-12T00:00:00.00Z"),
        Instant.parse("1985-10-26T00:00:00.00Z"),
        Instant.parse("2015-10-21T00:00:00.00Z")
      )

    implicit val dateArbitrary: Arbitrary[Date] =
      Arbitrary(Gen.oneOf(dates).map(self.apply))
  }
}
