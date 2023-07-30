package faker

import cats.syntax.all._

object ZeldaSpec extends FakerSpec {

  doTest[zelda.ZeldaCharacter, String](
    "zelda.ZeldaCharacter",
    _.zeldaCharacter(),
    faker => {
      implicit val resourceLoader: ResourceLoader = faker.loader
      implicitly
    },
    _.nonEmpty,
    _.value
  )

  doTest[zelda.ZeldaGame, String](
    "zelda.ZeldaGame",
    _.zeldaGame(),
    faker => {
      implicit val resourceLoader: ResourceLoader = faker.loader
      implicitly
    },
    _.nonEmpty,
    _.value
  )

  doTest[zelda.ZeldaItem, String](
    "zelda.ZeldaItem",
    _.zeldaItem(),
    faker => {
      implicit val resourceLoader: ResourceLoader = faker.loader
      implicitly
    },
    _.nonEmpty,
    _.value
  )

  doTest[zelda.ZeldaLocation, String](
    "zelda.ZeldaLocation",
    _.zeldaLocation(),
    faker => {
      implicit val resourceLoader: ResourceLoader = faker.loader
      implicitly
    },
    _.nonEmpty,
    _.value
  )

}
