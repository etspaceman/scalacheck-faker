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

object currency {
  type CurrencyCode = CurrencyCode.Type

  object CurrencyCode extends Newtype[String] { self =>
    def currencyCodes(implicit loader: ResourceLoader): Seq[CurrencyCode] =
      loader.loadKey[Seq[CurrencyCode]]("currency.codes")

    implicit def currencyCodeArbitrary(implicit
        loader: ResourceLoader
    ): Arbitrary[CurrencyCode] =
      Arbitrary(Gen.oneOf(currencyCodes))

    implicit val currencyCodeConfigReader: ConfigReader[CurrencyCode] =
      ConfigReader[String].map(self.apply)
  }

  type CurrencyName = CurrencyName.Type

  object CurrencyName extends Newtype[String] { self =>
    def currencyNames(implicit loader: ResourceLoader): Seq[CurrencyName] =
      loader.loadKey[Seq[CurrencyName]]("currency.names")

    implicit def currencyNameArbitrary(implicit
        loader: ResourceLoader
    ): Arbitrary[CurrencyName] =
      Arbitrary(Gen.oneOf(currencyNames))

    implicit val currencyNameConfigReader: ConfigReader[CurrencyName] =
      ConfigReader[String].map(self.apply)
  }

  type CurrencySymbol = CurrencySymbol.Type

  object CurrencySymbol extends Newtype[String] { self =>
    def currencySymbols(implicit loader: ResourceLoader): Seq[CurrencySymbol] =
      loader.loadKey[Seq[CurrencySymbol]]("currency.symbols")

    implicit def currencySymbolArbitrary(implicit
        loader: ResourceLoader
    ): Arbitrary[CurrencySymbol] =
      Arbitrary(Gen.oneOf(currencySymbols))

    implicit val currencySymbolConfigReader: ConfigReader[CurrencySymbol] =
      ConfigReader[String].map(self.apply)
  }
}
