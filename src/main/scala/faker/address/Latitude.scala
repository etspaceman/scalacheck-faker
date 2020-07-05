package faker.address

import scala.util.Random

import org.scalacheck.{Arbitrary, Gen}

final case class Latitude private (value: String) extends AnyVal

object Latitude {
  def latitude: String = "%.8g".format((Random.nextDouble() * 180) - 90)
  implicit val latitudeArbitrary: Arbitrary[Latitude] = Arbitrary(
    Gen.delay(latitude).map(Latitude.apply)
  )
}
