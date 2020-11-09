package faker.address

import org.scalacheck.{Arbitrary, Gen}

import faker.ResourceLoader
import faker.syntax.string._

final case class BuildingNumber private (value: String) extends AnyVal

object BuildingNumber {
  implicit def buildingNumberArbitrary(implicit
      loader: ResourceLoader
  ): Arbitrary[BuildingNumber] =
    Arbitrary(
      Gen
        .sequence[Seq[String], String](
          loader
            .loadKey[Seq[String]]("address.building-numbers")
            .map(_.interpolatedGen)
        )
        .flatMap(x => Gen.oneOf(x).map(BuildingNumber.apply))
    )
}
