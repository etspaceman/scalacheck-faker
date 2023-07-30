package faker

import cats.syntax.all._

object LoremSpec extends FakerSpec {
  
    doTest[lorem.LoremCharacters, String](
      "lorem.LoremCharacters",
      _.loremCharacters(),
      faker => {
        implicit val resourceLoader: ResourceLoader = faker.loader
        implicitly
      },
      _.nonEmpty,
      _.value
    )

    doTest[lorem.LoremWord, String](
      "lorem.LoremWord",
      _.loremWord(),
      faker => {
        implicit val resourceLoader: ResourceLoader = faker.loader
        implicitly
      },
      _.nonEmpty,
      _.value
    )

    doTest[lorem.LoremWords, String](
      "lorem.LoremWords",
      _.loremWords(3),
      faker => {
        implicit val resourceLoader: ResourceLoader = faker.loader
        implicitly
      },
      _.split(" ").length == 3,
      _.value
    )

    doTest[lorem.LoremSentence, String](
      "lorem.LoremSentence",
      _.loremSentence(3),
      faker => {
        implicit val resourceLoader: ResourceLoader = faker.loader
        implicitly
      },
      _.split(" ").length == 3,
      _.value
    )

    doTest[lorem.LoremParagraph, String](
      "lorem.LoremParagraph",
      _.loremParagraph(3),
      faker => {
        implicit val resourceLoader: ResourceLoader = faker.loader
        implicitly
      },
      _.split("\\.").length == 3,
      _.value
    )

    doTest[lorem.LoremParagraphs, String](
      "lorem.LoremParagraphs",
      _.loremParagraphs(3),
      faker => {
        implicit val resourceLoader: ResourceLoader = faker.loader
        implicitly
      },
      _.split("\n").length == 3,
      _.value
    )
}
