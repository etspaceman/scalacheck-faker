package faker.company

import faker.FakerArbitrarySpec
import faker.ResourceLoader.Implicits._

class CompanyArbitrarySpec extends FakerArbitrarySpec {
  "Company" - {
    testCanGen[BS]
    testCanGen[BuzzWord]
    testCanGen[CatchPhrase]
    testCanGen[CompanyDomainName]
    testCanGen[CompanyName]
    testCanGen[CompanySuffix]
    testCanGen[CompanyUrl]
    testCanGen[Industry]
    testCanGen[Logo]
    testCanGen[Profession]
  }
}
