package faker.address

import scala.jdk.CollectionConverters._

import org.scalacheck.{Arbitrary, Gen}

import faker.ResourceLoader
import faker.syntax.string._

final case class SecondaryAddress private (value: String) extends AnyVal

object SecondaryAddress {
  implicit def secondaryAddressArbitrary(implicit
      loader: ResourceLoader
  ): Arbitrary[SecondaryAddress] =
    Arbitrary(
      Gen
        .sequence(
          loader
            .loadKey[Seq[String]]("address.secondary-addresses")
            .map(_.interpolatedGen)
        )
        .flatMap(x => Gen.oneOf(x.asScala).map(SecondaryAddress.apply))
    )
}
