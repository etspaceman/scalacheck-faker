package faker.name

import faker.FakerSpec

class NameSpec extends FakerSpec {
  "Name" - {
    testCanGen[FirstName]()
    testCanGen[LastName]()
    testCanGen[FullName]()
    testCanGen[FullNameWithMiddle]()
    testCanGen[Prefix]()
    testCanGen[Suffix]()
    testCanGen[Title]()
    testCanGen[UserName]()
  }
}
