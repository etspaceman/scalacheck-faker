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

object InternetSpec extends FakerSpec {

  doTest[internet.Avatar, String](
    "internet.Avatar",
    _.avatar(),
    faker => {
      implicit val resourceLoader: ResourceLoader = faker.loader
      implicitly
    },
    _.nonEmpty,
    _.value
  )

  doTest[internet.DomainName, String](
    "internet.DomainName",
    _.domainName(),
    faker => {
      implicit val resourceLoader: ResourceLoader = faker.loader
      implicitly
    },
    _.nonEmpty,
    _.value
  )

  doTest[internet.DomainWord, String](
    "internet.DomainWord",
    _.domainWord(),
    faker => {
      implicit val resourceLoader: ResourceLoader = faker.loader
      implicitly
    },
    _.nonEmpty,
    _.value
  )

  doTest[internet.DomainSuffix, String](
    "internet.DomainSuffix",
    _.domainSuffix(),
    faker => {
      implicit val resourceLoader: ResourceLoader = faker.loader
      implicitly
    },
    _.nonEmpty,
    _.value
  )

  doTest[internet.EmailAddress, String](
    "internet.EmailAddress",
    _.emailAddress(),
    faker => {
      implicit val resourceLoader: ResourceLoader = faker.loader
      implicitly
    },
    _.split("@").length == 2,
    _.value
  )

  doTest[internet.Image, String](
    "internet.Image",
    _.image(),
    faker => {
      implicit val resourceLoader: ResourceLoader = faker.loader
      implicitly
    },
    _.nonEmpty,
    _.value
  )

  doTest[internet.IpV4Address, String](
    "internet.IpV4Address",
    _.ipV4Address(),
    _ => implicitly,
    _.split("\\.").length == 4,
    _.value
  )

  doTest[internet.IpV4Cidr, String](
    "internet.IpV4Cidr",
    _.ipV4Cidr(),
    _ => implicitly,
    _.nonEmpty,
    _.value
  )

  doTest[internet.IpV6Address, String](
    "internet.IpV6Address",
    _.ipV6Address(),
    _ => implicitly,
    _.split(":").length == 8,
    _.value
  )

  doTest[internet.IpV6Cidr, String](
    "internet.IpV6Cidr",
    _.image(),
    _ => implicitly,
    _.nonEmpty,
    _.value
  )

  doTest[internet.MacAddress, String](
    "internet.MacAddress",
    _.macAddress(),
    _ => implicitly,
    _.split(":").length == 6,
    _.value
  )

  doTest[internet.Password, String](
    "internet.Password",
    _.password(),
    faker => {
      implicit val resourceLoader: ResourceLoader = faker.loader
      implicitly
    },
    _.nonEmpty,
    _.value
  )

  doTest[internet.PrivateIpV4Address, String](
    "internet.PrivateIpV4Address",
    _.privateIpV4Address(),
    _ => implicitly,
    _.split("\\.").length == 4,
    _.value
  )

  doTest[internet.PublicIpV4Address, String](
    "internet.PublicIpV4Address",
    _.publicIpV4Address(),
    _ => implicitly,
    _.split("\\.").length == 4,
    _.value
  )

  doTest[internet.SafeEmailAddress, String](
    "internet.SafeEmailAddress",
    _.safeEmailAddress(),
    faker => {
      implicit val resourceLoader: ResourceLoader = faker.loader
      implicitly
    },
    _.split("@").length == 2,
    _.value
  )

  doTest[internet.Slug, String](
    "internet.Slug",
    _.slug(),
    faker => {
      implicit val resourceLoader: ResourceLoader = faker.loader
      implicitly
    },
    _.nonEmpty,
    _.value
  )

  doTest[internet.Url, String](
    "internet.Url",
    _.url(),
    faker => {
      implicit val resourceLoader: ResourceLoader = faker.loader
      implicitly
    },
    _.nonEmpty,
    _.value
  )

  doTest[internet.UserAgent, String](
    "internet.UserAgent",
    _.userAgent(),
    faker => {
      implicit val resourceLoader: ResourceLoader = faker.loader
      implicitly
    },
    _.nonEmpty,
    _.value
  )

}
