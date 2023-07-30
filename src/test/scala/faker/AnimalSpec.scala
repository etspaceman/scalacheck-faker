package faker

import cats.syntax.all._

object AnimalSpec extends FakerSpec {

  doTest[animal.AnimalName, String](
    "animal.AnimalName",
    _.animalName(),
    faker => {
      implicit val resourceLoader: ResourceLoader = faker.loader
      implicitly
    },
    _.nonEmpty,
    _.value
  )

}
