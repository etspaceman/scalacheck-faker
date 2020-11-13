package faker

import io.estatico.newtype.macros.newtype
import io.estatico.newtype.ops._
import org.scalacheck.{Arbitrary, Gen}
import pureconfig.ConfigReader

object animal {
  @newtype final case class AnimalName private (value: String)

  object AnimalName {
    def animalNames(implicit loader: ResourceLoader): Seq[AnimalName] =
      loader.loadKey[Seq[AnimalName]]("animal.names")

    implicit def animalNameArbitrary(implicit
        loader: ResourceLoader
    ): Arbitrary[AnimalName] =
      Arbitrary(Gen.oneOf(animalNames))

    implicit val animalNameConfigReader: ConfigReader[AnimalName] =
      ConfigReader[String].coerce
  }
}
