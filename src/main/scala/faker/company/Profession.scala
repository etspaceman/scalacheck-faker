package faker.company

import org.scalacheck.{Arbitrary, Gen}
import pureconfig.ConfigReader
import pureconfig.generic.semiauto._

import faker.ResourceLoader

final case class Profession private (value: String) extends AnyVal

object Profession {
  def professions(implicit loader: ResourceLoader): Seq[Profession] =
    loader.loadKey[Seq[Profession]]("company.professions")
  implicit def professionArbitrary(implicit
      loader: ResourceLoader
  ): Arbitrary[Profession] = Arbitrary(Gen.oneOf(professions))

  implicit val professionConfigReader: ConfigReader[Profession] = deriveReader
}
