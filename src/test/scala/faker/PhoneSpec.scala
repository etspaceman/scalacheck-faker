package faker

import cats.syntax.all._

object PhoneSpec extends FakerSpec {

  doTest[phone.PhoneNumber, String](
    "phone.PhoneNumber",
    _.phoneNumber(),
    faker => {
      implicit val resourceLoader: ResourceLoader = faker.loader
      implicitly
    },
    _.nonEmpty,
    _.value
  )

  doTest[phone.CellPhoneNumber, String](
    "phone.CellPhoneNumber",
    _.cellPhoneNumber(),
    faker => {
      implicit val resourceLoader: ResourceLoader = faker.loader
      implicitly
    },
    _.nonEmpty,
    _.value
  )

}
