package faker.internet

import faker.lorem.LoremWord
import org.scalacheck.{Arbitrary, Gen}

final case class Slug private (value: String) extends AnyVal

object Slug {
  implicit val slugArbitrary: Arbitrary[Slug] = Arbitrary(
    Gen
      .listOfN(2, Arbitrary.arbitrary[LoremWord])
      .map(x => Slug(x.map(_.value).mkString("_")))
  )
}
