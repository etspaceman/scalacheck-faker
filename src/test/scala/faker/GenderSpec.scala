package faker

import cats.syntax.all._

object GenderSpec extends FakerSpec {

  doTest[gender.GenderType, String](
    "gender.GenderType",
    _.genderType(),
    faker => {
      implicit val resourceLoader: ResourceLoader = faker.loader
      implicitly
    },
    _.nonEmpty,
    _.value
  )

  doTest[gender.GenderBinaryType, String](
    "gender.GenderBinaryType",
    _.genderBinaryType(),
    faker => {
      implicit val resourceLoader: ResourceLoader = faker.loader
      implicitly
    },
    _.nonEmpty,
    _.value
  )

  doTest[gender.GenderShortBinaryType, String](
    "gender.GenderShortBinaryType",
    _.genderShortBinaryType(),
    faker => {
      implicit val resourceLoader: ResourceLoader = faker.loader
      implicitly
    },
    _.nonEmpty,
    _.value
  )

}
