package faker

import cats.syntax.all._

object NameSpec extends FakerSpec {

  doTest[faker.name.FirstName, String](
    "name.FirstName",
    _.firstName(),
    faker => {
      implicit val resourceLoader: ResourceLoader = faker.loader
      implicitly
    },
    _.nonEmpty,
    _.value
  )

  doTest[faker.name.FullName, String](
    "name.FullName",
    _.fullName(),
    faker => {
      implicit val resourceLoader: ResourceLoader = faker.loader
      implicitly
    },
    _.split(" ").length > 1,
    _.value
  )

   doTest[faker.name.FullNameWithMiddle, String](
    "name.FullNameWithMiddle",
    _.fullNameWithMiddle(),
    faker => {
      implicit val resourceLoader: ResourceLoader = faker.loader
      implicitly
    },
    _.split(" ").length > 1,
    _.value
  )

  doTest[faker.name.LastName, String](
    "name.LastName",
    _.lastName(),
    faker => {
      implicit val resourceLoader: ResourceLoader = faker.loader
      implicitly
    },
    _.nonEmpty,
    _.value
  )

  doTest[faker.name.Prefix, String](
    "name.Prefix",
    _.prefix(),
    faker => {
      implicit val resourceLoader: ResourceLoader = faker.loader
      implicitly
    },
    _.nonEmpty,
    _.value
  )

  doTest[faker.name.Suffix, String](
    "name.Suffix",
    _.suffix(),
    faker => {
      implicit val resourceLoader: ResourceLoader = faker.loader
      implicitly
    },
    _.nonEmpty,
    _.value
  )

  doTest[faker.name.Title, String](
    "name.Title",
    _.title(),
    faker => {
      implicit val resourceLoader: ResourceLoader = faker.loader
      implicitly
    },
    _.nonEmpty,
    _.value
  )

  doTest[faker.name.UserName, String](
    "name.UserName",
    _.userName(),
    faker => {
      implicit val resourceLoader: ResourceLoader = faker.loader
      implicitly
    },
    _.split("\\.").length == 2,
    _.value
  )

}
