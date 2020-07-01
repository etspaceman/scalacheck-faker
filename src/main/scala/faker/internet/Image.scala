package faker.internet

import faker.ResourceLoader
import org.scalacheck.{Arbitrary, Gen}

/**
  * Generates a random image url based on the lorempixel service. All the images provided by this service are released
  * under the creative commons license (CC BY-SA). For more information, please visit: http://lorempixel.com/
  *
  * @see <a href="http://loremflickr.com/">lorempixel</a>
  */
final case class Image private (value: String) extends AnyVal

object Image {
  private val imageDimensions =
    ResourceLoader.loadStringList("internet.image.dimensions")
  private val imageCategories =
    ResourceLoader.loadStringList("internet.image.categories")
  implicit val imageArbitrary: Arbitrary[Image] =
    Arbitrary {
      for {
        dimension <- Gen.oneOf(imageDimensions)
        category <- Gen.oneOf(imageCategories)
        split = dimension.split("x").map(_.trim)
      } yield Image(
        s"https://loremflickr.com/${split.head}/${split(1)}/$category"
      )
    }

}
