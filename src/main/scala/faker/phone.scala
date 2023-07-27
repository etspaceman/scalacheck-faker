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

import org.scalacheck.Arbitrary

object phone {
  type CellPhoneNumber = CellPhoneNumber.Type

  object CellPhoneNumber extends Newtype[String] { self =>
    implicit def cellPhoneNumberArbitrary(implicit
        loader: ResourceLoader
    ): Arbitrary[CellPhoneNumber] =
      Arbitrary(
        loader
          .loadKey[StringGenBuilder]("phone.cell-number-builder")
          .gen
          .map(self.apply)
      )
  }

  type PhoneNumber = PhoneNumber.Type

  object PhoneNumber extends Newtype[String] { self =>
    implicit def phoneNumberArbitrary(implicit
        loader: ResourceLoader
    ): Arbitrary[PhoneNumber] =
      Arbitrary(
        loader
          .loadKey[StringGenBuilder]("phone.phone-number-builder")
          .gen
          .map(self.apply)
      )
  }
}
