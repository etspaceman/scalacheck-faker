package faker.address

import org.scalacheck.{Arbitrary, Gen}

final case class Longitude private (value: String) extends AnyVal

object Longitude {
  implicit val longitudeArbitrary: Arbitrary[Longitude] = Arbitrary(
    Gen
      .choose[Double](0, 0.99)
      .map(x => Longitude("%.8g".format((x * 360) - 180)))
  )
}
