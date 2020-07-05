package faker.name

import org.scalacheck.{Arbitrary, Gen}
import pureconfig.ConfigReader
import pureconfig.generic.semiauto._

import faker.ResourceLoader

final case class FirstName private (value: String) extends AnyVal

object FirstName {
  def firstNames(implicit loader: ResourceLoader): Seq[FirstName] =
    loader.loadKey[Seq[FirstName]]("name.first.names")

  implicit def firstNameArbitrary(implicit
      loader: ResourceLoader
  ): Arbitrary[FirstName] =
    Arbitrary(Gen.oneOf(firstNames))

  implicit val firstNameConfigReader: ConfigReader[FirstName] = deriveReader
}
