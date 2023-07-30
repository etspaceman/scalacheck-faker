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

object ZeldaSpec extends FakerSpec {

  doTest[zelda.ZeldaCharacter, String](
    "zelda.ZeldaCharacter",
    _.zeldaCharacter(),
    faker => {
      implicit val resourceLoader: ResourceLoader = faker.loader
      implicitly
    },
    _.nonEmpty,
    _.value
  )

  doTest[zelda.ZeldaGame, String](
    "zelda.ZeldaGame",
    _.zeldaGame(),
    faker => {
      implicit val resourceLoader: ResourceLoader = faker.loader
      implicitly
    },
    _.nonEmpty,
    _.value
  )

  doTest[zelda.ZeldaItem, String](
    "zelda.ZeldaItem",
    _.zeldaItem(),
    faker => {
      implicit val resourceLoader: ResourceLoader = faker.loader
      implicitly
    },
    _.nonEmpty,
    _.value
  )

  doTest[zelda.ZeldaLocation, String](
    "zelda.ZeldaLocation",
    _.zeldaLocation(),
    faker => {
      implicit val resourceLoader: ResourceLoader = faker.loader
      implicitly
    },
    _.nonEmpty,
    _.value
  )

}
