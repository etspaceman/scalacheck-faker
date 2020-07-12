package faker.address

import org.scalacheck.{Arbitrary, Gen}

final case class Latitude private (value: String) extends AnyVal

object Latitude {
  implicit val latitudeArbitrary: Arbitrary[Latitude] = Arbitrary(
    Gen
      .choose[Double](0, 0.99)
      .map(x => Latitude("%.8g".format((x * 180) - 90)))
  )
}
