package faker.address

import faker.FakerArbitrarySpec
import faker.ResourceLoader.Implicits._

class AddressArbitrarySpec extends FakerArbitrarySpec {
  "Internet" - {
    testCanGen[BuildingNumber]
    testCanGen[City]
    testCanGen[CityPrefix]
    testCanGen[CitySuffix]
    testCanGen[Country]
    testCanGen[DefaultCountry]
    testCanGen[FullAddress]
    testCanGen[Latitude]
    testCanGen[Longitude]
    testCanGen[PostalCode]
    testCanGen[SecondaryAddress]
    testCanGen[State]
    testCanGen[StreetAddress]
    testCanGen[StreetName]
    testCanGen[StreetPrefix]
    testCanGen[StreetSuffix]
  }
}
