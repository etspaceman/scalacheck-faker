package faker

import org.scalatest.freespec.AnyFreeSpecLike

import faker.syntax.scalacheck._

class StringGenBuilderSpec extends AnyFreeSpecLike {
  "StringGenBuilder" - {
    "StringPart should work" in {
      val stringPart1 = StringPart(
        Some("stringPart1Prefix "),
        Some(" stringPart1Suffix "),
        "stringPart1"
      )
      val stringPart2 = StringPart(
        Some("stringPart2Prefix "),
        Some(" stringPart2Suffix."),
        "stringPart2"
      )
      val builder = StringGenBuilder(
        Seq(StringGenBuilderWeightedOption(List(stringPart1, stringPart2)))
      )
      val res = builder.gen.one
      assert(
        res === "stringPart1Prefix stringPart1 stringPart1Suffix stringPart2Prefix stringPart2 stringPart2Suffix."
      )
    }
  }
}
