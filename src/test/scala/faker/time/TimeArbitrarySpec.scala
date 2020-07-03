package faker.time

import faker.FakerArbitrarySpec

class TimeArbitrarySpec extends FakerArbitrarySpec {
  testCanGen[CurrentEraInstant]
  testCanGen[CurrentEraLocalDateTime]
  testCanGen[CurrentEraOffsetDateTime]
  testCanGen[CurrentEraZonedDateTime]

  testCanGen[FutureInstant]
  testCanGen[FutureLocalDateTime]
  testCanGen[FutureOffsetDateTime]
  testCanGen[FutureZonedDateTime]

  testCanGen[NowInstant]
  testCanGen[NowLocalDateTime]
  testCanGen[NowOffsetDateTime]
  testCanGen[NowZonedDateTime]

  testCanGen[PastInstant]
  testCanGen[PastLocalDateTime]
  testCanGen[PastOffsetDateTime]
  testCanGen[PastZonedDateTime]

  testCanGen[RandomInstant]
  testCanGen[RandomLocalDateTime]
  testCanGen[RandomOffsetDateTime]
  testCanGen[RandomZonedDateTime]
}
