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

object AddressSpec extends FakerSpec {

  doTest[address.BuildingNumber, String](
    "address.BuildingNumber",
    _.buildingNumber(),
    faker => {
      implicit val resourceLoader: ResourceLoader = faker.loader
      implicitly
    },
    _.nonEmpty,
    _.value
  )

  doTest[address.City, String](
    "address.City",
    _.city(),
    faker => {
      implicit val resourceLoader: ResourceLoader = faker.loader
      implicitly
    },
    _.nonEmpty,
    _.value
  )

  doTest[address.CityPrefix, String](
    "address.CityPrefix",
    _.cityPrefix(),
    faker => {
      implicit val resourceLoader: ResourceLoader = faker.loader
      implicitly
    },
    _.nonEmpty,
    _.value
  )

  doTest[address.CitySuffix, String](
    "address.CitySuffix",
    _.citySuffix(),
    faker => {
      implicit val resourceLoader: ResourceLoader = faker.loader
      implicitly
    },
    _.nonEmpty,
    _.value
  )

  doTest[address.Country, address.Country](
    "address.Country",
    _.country(),
    faker => {
      implicit val resourceLoader: ResourceLoader = faker.loader
      implicitly
    },
    res => res.code.nonEmpty && res.name.nonEmpty,
    _.toString
  )

  doTest[address.DefaultCountry, address.DefaultCountry](
    "address.DefaultCountry",
    _.defaultCountry(),
    faker => {
      implicit val resourceLoader: ResourceLoader = faker.loader
      implicitly
    },
    res => res.code.nonEmpty && res.name.nonEmpty,
    _.toString
  )

  doTest[address.FullAddress, String](
    "address.FullAddress",
    _.fullAddress(),
    faker => {
      implicit val resourceLoader: ResourceLoader = faker.loader
      implicitly
    },
    _.nonEmpty,
    _.value
  )

  doTest[address.Latitude, String](
    "address.Latitude",
    _.latitude(),
    _ => implicitly,
    _.nonEmpty,
    _.value
  )

  doTest[address.Longitude, String](
    "address.Longitude",
    _.longitude(),
    _ => implicitly,
    _.nonEmpty,
    _.value
  )

  doTest[address.SecondaryAddress, String](
    "address.SecondaryAddress",
    _.secondaryAddress(),
    faker => {
      implicit val resourceLoader: ResourceLoader = faker.loader
      implicitly
    },
    _.nonEmpty,
    _.value
  )

  doTest[address.PostalCode, String](
    "address.PostalCode",
    _.postalCode(),
    faker => {
      implicit val resourceLoader: ResourceLoader = faker.loader
      implicitly
    },
    _.nonEmpty,
    _.value
  )

  doTest[address.StreetAddress, String](
    "address.StreetAddress",
    _.streetAddress(),
    faker => {
      implicit val resourceLoader: ResourceLoader = faker.loader
      implicitly
    },
    _.nonEmpty,
    _.value
  )

  doTest[address.StreetName, String](
    "address.StreetName",
    _.streetName(),
    faker => {
      implicit val resourceLoader: ResourceLoader = faker.loader
      implicitly
    },
    _.nonEmpty,
    _.value
  )

  doTest[address.StreetPrefix, String](
    "address.StreetPrefix",
    _.streetPrefix(),
    faker => {
      implicit val resourceLoader: ResourceLoader = faker.loader
      implicitly
    },
    _.nonEmpty,
    _.value
  )

  doTest[address.StreetSuffix, String](
    "address.StreetSuffix",
    _.streetSuffix(),
    faker => {
      implicit val resourceLoader: ResourceLoader = faker.loader
      implicitly
    },
    _.nonEmpty,
    _.value
  )
}
