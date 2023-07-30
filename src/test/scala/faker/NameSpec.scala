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

object NameSpec extends FakerSpec {

  doTest[faker.name.FirstName, String](
    "name.FirstName",
    _.firstName(),
    faker => {
      implicit val resourceLoader: ResourceLoader = faker.loader
      implicitly
    },
    _.nonEmpty,
    _.value
  )

  doTest[faker.name.FullName, String](
    "name.FullName",
    _.fullName(),
    faker => {
      implicit val resourceLoader: ResourceLoader = faker.loader
      implicitly
    },
    _.split(" ").length > 1,
    _.value
  )

  doTest[faker.name.FullNameWithMiddle, String](
    "name.FullNameWithMiddle",
    _.fullNameWithMiddle(),
    faker => {
      implicit val resourceLoader: ResourceLoader = faker.loader
      implicitly
    },
    _.split(" ").length > 1,
    _.value
  )

  doTest[faker.name.LastName, String](
    "name.LastName",
    _.lastName(),
    faker => {
      implicit val resourceLoader: ResourceLoader = faker.loader
      implicitly
    },
    _.nonEmpty,
    _.value
  )

  doTest[faker.name.Prefix, String](
    "name.Prefix",
    _.prefix(),
    faker => {
      implicit val resourceLoader: ResourceLoader = faker.loader
      implicitly
    },
    _.nonEmpty,
    _.value
  )

  doTest[faker.name.Suffix, String](
    "name.Suffix",
    _.suffix(),
    faker => {
      implicit val resourceLoader: ResourceLoader = faker.loader
      implicitly
    },
    _.nonEmpty,
    _.value
  )

  doTest[faker.name.Title, String](
    "name.Title",
    _.title(),
    faker => {
      implicit val resourceLoader: ResourceLoader = faker.loader
      implicitly
    },
    _.nonEmpty,
    _.value
  )

  doTest[faker.name.UserName, String](
    "name.UserName",
    _.userName(),
    faker => {
      implicit val resourceLoader: ResourceLoader = faker.loader
      implicitly
    },
    _.split("\\.").length == 2,
    _.value
  )

}
