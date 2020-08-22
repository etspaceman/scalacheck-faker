package faker.animal

import org.scalacheck.{Arbitrary, Gen}
import pureconfig.ConfigReader
import pureconfig.generic.semiauto._

import faker.ResourceLoader

final case class AnimalName private (value: String) extends AnyVal

object AnimalName {
  def animalNames(implicit loader: ResourceLoader): Seq[AnimalName] =
    loader.loadKey[Seq[AnimalName]]("animal.names")

  implicit def animalNameArbitrary(implicit
      loader: ResourceLoader
  ): Arbitrary[AnimalName] =
    Arbitrary(Gen.oneOf(animalNames))

  implicit val animalNameConfigReader: ConfigReader[AnimalName] = deriveReader
}
