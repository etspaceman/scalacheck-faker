package faker.internet

import org.scalacheck.{Arbitrary, Gen}

import faker.ResourceLoader

/**
  * Generates a random image url based on the lorempixel service. All the images provided by this service are released
  * under the creative commons license (CC BY-SA). For more information, please visit: http://lorempixel.com/
  *
  * @see <a href="http://loremflickr.com/">lorempixel</a>
  */
final case class Image private (value: String) extends AnyVal

object Image {
  def imageDimensions(implicit loader: ResourceLoader): Seq[String] =
    loader.loadKey[Seq[String]]("internet.image.dimensions")
  def imageCategories(implicit loader: ResourceLoader): Seq[String] =
    loader.loadKey[Seq[String]]("internet.image.categories")
  implicit def imageArbitrary(implicit
      loader: ResourceLoader
  ): Arbitrary[Image] =
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
