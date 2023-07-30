package faker

import cats.syntax.all._

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
