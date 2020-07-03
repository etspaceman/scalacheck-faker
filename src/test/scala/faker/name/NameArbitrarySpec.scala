package faker.name

import faker.FakerArbitrarySpec
import faker.ResourceLoader.Implicits._

class NameArbitrarySpec extends FakerArbitrarySpec {
  "Name" - {
    testCanGen[FirstName]
    testCanGen[LastName]
    testCanGen[FullName]
    testCanGen[FullNameWithMiddle]
    testCanGen[Prefix]
    testCanGen[Suffix]
    testCanGen[Title]
    testCanGen[UserName]
  }
}
