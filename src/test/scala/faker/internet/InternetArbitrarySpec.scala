package faker.internet

import faker.FakerArbitrarySpec
import faker.ResourceLoader.Implicits._

class InternetArbitrarySpec extends FakerArbitrarySpec {
  "Internet" - {
    testCanGen[Avatar]
    testCanGen[DomainName]
    testCanGen[DomainSuffix]
    testCanGen[DomainWord]
    testCanGen[EmailAddress]
    testCanGen[Image]
    testCanGen[IpV4Address]
    testCanGen[IpV4Cidr]
    testCanGen[IpV6Address]
    testCanGen[IpV6Cidr]
    testCanGen[MacAddress]
    testCanGen[Password]
    testCanGen[PrivateIpV4Address]
    testCanGen[PublicIpV4Address]
    testCanGen[SafeEmailAddress]
    testCanGen[Slug]
    testCanGen[Url]
    testCanGen[UserAgent]
  }
}
