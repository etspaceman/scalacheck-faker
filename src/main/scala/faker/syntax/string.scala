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

package faker.syntax

import org.scalacheck.Gen

import faker.regexp.RegexpGen

private[faker] object string extends FakerStringSyntax

private[faker] trait FakerStringSyntax {
  implicit def toFakerStringOps(str: String): FakerStringSyntax.FakerStringOps =
    new FakerStringSyntax.FakerStringOps(str)
}

private[faker] object FakerStringSyntax {
  final class FakerStringOps(private val str: String) extends AnyVal {
    def interpolatedGen: Gen[String] =
      str.foldLeft(Gen.const("")) { (gen, char) =>
        if (char == '#') gen.flatMap(x => Gen.choose(0, 9).map(y => s"$x$y"))
        else gen.map(y => y + char.toString)
      }

    def regexGen: Gen[String] = RegexpGen.from(str)
  }
}
