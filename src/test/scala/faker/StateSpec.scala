package faker

import cats.syntax.all._

import faker.syntax.scalacheck._

object StatesSpec extends FakerSpec {

  doTest[states.StateLike, states.StateLike](
    "states.StateLike",
    _.state(),
    faker => {
      implicit val resourceLoader: ResourceLoader = faker.loader
      implicitly
    },
    res =>
      res.abbr.nonEmpty && res.name.nonEmpty && res.postalCodeGen.one.nonEmpty,
    _.toString
  )

}
