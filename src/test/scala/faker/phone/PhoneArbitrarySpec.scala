package faker.phone

import faker.FakerArbitrarySpec
import faker.ResourceLoader.Implicits._

class PhoneArbitrarySpec extends FakerArbitrarySpec {
  "Phone" - {
    testCanGen[PhoneNumber]
    testCanGen[CellPhoneNumber]
  }
}
