package faker.address

import scala.util.Random

import org.scalacheck.{Arbitrary, Gen}

final case class Longitude private (value: String) extends AnyVal

object Longitude {
  def longitude: String = "%.8g".format((Random.nextDouble() * 360) - 180)
  implicit val longitudeArbitrary: Arbitrary[Longitude] = Arbitrary(
    Gen.delay(longitude).map(Longitude.apply)
  )
}
