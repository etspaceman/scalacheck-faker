package faker.lorem

import faker.FakerSpec
import faker.ResourceLoader.Implicits._

class LoremSpec extends FakerSpec {
  "Lorem" - {
    testCanGen[LoremWord]
    testCanGen[LoremWords]
    testCanGen[LoremSentence]
    testCanGen[LoremParagraph]
    testCanGen[LoremParagraphs]
    testCanGen[LoremCharacters]
  }
}
