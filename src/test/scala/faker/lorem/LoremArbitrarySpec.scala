package faker.lorem

import faker.FakerArbitrarySpec
import faker.ResourceLoader.Implicits._

class LoremArbitrarySpec extends FakerArbitrarySpec {
  "Lorem" - {
    testCanGen[LoremWord]
    testCanGen[LoremWords]
    testCanGen[LoremSentence]
    testCanGen[LoremParagraph]
    testCanGen[LoremParagraphs]
    testCanGen[LoremCharacters]
  }
}
