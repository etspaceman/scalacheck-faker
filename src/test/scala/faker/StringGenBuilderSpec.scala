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

import faker.syntax.scalacheck._

class StringGenBuilderSpec extends munit.FunSuite {

  test("StringPart should work") {
    val stringPart1 = StringPart(
      Some("stringPart1Prefix "),
      Some(" stringPart1Suffix "),
      "stringPart1"
    )
    val stringPart2 = StringPart(
      Some("stringPart2Prefix "),
      Some(" stringPart2Suffix."),
      "stringPart2"
    )
    val builder = StringGenBuilder(
      Seq(StringGenBuilderWeightedOption(List(stringPart1, stringPart2)))
    )
    val res = builder.gen.one
    assertEquals(
      res,
      "stringPart1Prefix stringPart1 stringPart1Suffix stringPart2Prefix stringPart2 stringPart2Suffix."
    )
  }
  test("StringRegexPart should work") {
    val regexPart = StringRegexPart(
      Some("stringPart1Prefix "),
      Some(" stringPart1Suffix "),
      "[A-CEJ-NPR-TVXY][0-9][A-CEJ-NPR-TV-Z] ?[0-9][A-CEJ-NPR-TV-Z][0-9]"
    )
    val builder = StringGenBuilder(
      Seq(StringGenBuilderWeightedOption(List(regexPart)))
    )

    val res = builder.gen.one

    assert(res.nonEmpty)
  }
}
