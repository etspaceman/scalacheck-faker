package faker.company

import org.scalacheck.{Arbitrary, Gen}

final case class Logo private (value: String) extends AnyVal

object Logo {
  implicit val logoArbitrary: Arbitrary[Logo] =
    Arbitrary(
      Gen
        .choose(1, 13)
        .map(x =>
          Logo(
            s"https://pigment.github.io/fake-logos/logos/medium/color/$x.png"
          )
        )
    )
}
