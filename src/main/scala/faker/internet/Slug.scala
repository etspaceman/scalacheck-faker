package faker.internet

import org.scalacheck.{Arbitrary, Gen}

import faker.ResourceLoader
import faker.lorem.LoremWord

final case class Slug private (value: String) extends AnyVal

object Slug {
  implicit def slugArbitrary(implicit loader: ResourceLoader): Arbitrary[Slug] =
    Arbitrary(
      Gen
        .listOfN(2, Arbitrary.arbitrary[LoremWord])
        .map(x => Slug(x.map(_.value).mkString("_")))
    )
}
