package faker.beer

import org.scalacheck.Arbitrary

import faker.{ResourceLoader, StringGenBuilder}

final case class Beer private (value: String) extends AnyVal

object Beer {
  implicit def beerArbitrary(implicit
      loader: ResourceLoader
  ): Arbitrary[Beer] =
    Arbitrary(
      loader
        .loadKey[StringGenBuilder]("beer.beer-builder")
        .gen
        .map(Beer.apply)
    )
}
