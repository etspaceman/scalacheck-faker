package faker.name

import org.scalacheck.{Arbitrary, Gen}
import pureconfig.ConfigReader
import pureconfig.generic.semiauto._

import faker.ResourceLoader

final case class LastName private (value: String) extends AnyVal

object LastName {
  def lastNames(implicit loader: ResourceLoader): Seq[LastName] =
    loader.loadKey[Seq[LastName]]("name.last.names")
  implicit def lastNameArbitrary(implicit
      loader: ResourceLoader
  ): Arbitrary[LastName] =
    Arbitrary(Gen.oneOf(lastNames))

  implicit val lastNameConfigReader: ConfigReader[LastName] = deriveReader
}
