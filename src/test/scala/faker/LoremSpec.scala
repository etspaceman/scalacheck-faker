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

import cats.syntax.all._

object LoremSpec extends FakerSpec {

  doTest[lorem.LoremCharacters, String](
    "lorem.LoremCharacters",
    _.loremCharacters(),
    faker => {
      implicit val resourceLoader: ResourceLoader = faker.loader
      implicitly
    },
    _.nonEmpty,
    _.value
  )

  doTest[lorem.LoremWord, String](
    "lorem.LoremWord",
    _.loremWord(),
    faker => {
      implicit val resourceLoader: ResourceLoader = faker.loader
      implicitly
    },
    _.nonEmpty,
    _.value
  )

  doTest[lorem.LoremWords, String](
    "lorem.LoremWords",
    _.loremWords(3),
    faker => {
      implicit val resourceLoader: ResourceLoader = faker.loader
      implicitly
    },
    _.split(" ").length == 3,
    _.value
  )

  doTest[lorem.LoremSentence, String](
    "lorem.LoremSentence",
    _.loremSentence(3),
    faker => {
      implicit val resourceLoader: ResourceLoader = faker.loader
      implicitly
    },
    _.split(" ").length == 3,
    _.value
  )

  doTest[lorem.LoremParagraph, String](
    "lorem.LoremParagraph",
    _.loremParagraph(3),
    faker => {
      implicit val resourceLoader: ResourceLoader = faker.loader
      implicitly
    },
    _.split("\\.").length == 3,
    _.value
  )

  doTest[lorem.LoremParagraphs, String](
    "lorem.LoremParagraphs",
    _.loremParagraphs(3),
    faker => {
      implicit val resourceLoader: ResourceLoader = faker.loader
      implicitly
    },
    _.split("\n").length == 3,
    _.value
  )
}
