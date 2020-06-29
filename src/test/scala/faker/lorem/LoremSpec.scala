package faker.lorem

import faker.FakerSpec

class LoremSpec extends FakerSpec {
  "Lorem" - {
    testCanGen[LoremWord]()
    testCanGen[LoremWords]()
    testCanGen[LoremSentence]()
    testCanGen[LoremParagraph]()
    testCanGen[LoremParagraphs]()
    testCanGen[LoremCharacters]()
  }
}
