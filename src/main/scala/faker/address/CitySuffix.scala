package faker.address

import org.scalacheck.{Arbitrary, Gen}
import pureconfig.ConfigReader
import pureconfig.generic.semiauto._

import faker.ResourceLoader

final case class CitySuffix private (value: String) extends AnyVal

object CitySuffix {
  def citySuffixes(implicit loader: ResourceLoader): Seq[CitySuffix] =
    loader.loadKey[Seq[CitySuffix]]("address.city-suffixes")
  implicit def citySuffixArbitrary(implicit
      loader: ResourceLoader
  ): Arbitrary[CitySuffix] =
    Arbitrary(Gen.oneOf(citySuffixes))

  implicit val citySuffixConfigReader: ConfigReader[CitySuffix] = deriveReader
}
