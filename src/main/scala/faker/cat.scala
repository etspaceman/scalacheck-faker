package faker

import io.estatico.newtype.macros.newtype
import io.estatico.newtype.ops._
import org.scalacheck.{Arbitrary, Gen}
import pureconfig.ConfigReader

object cat {
  @newtype final case class Name private (value: String)

  object Name {
    def names(implicit loader: ResourceLoader): Seq[Name] =
      loader.loadKey[Seq[Name]]("cat.names")

    implicit def nameArbitrary(implicit
        loader: ResourceLoader
    ): Arbitrary[Name] =
      Arbitrary(Gen.oneOf(names))

    implicit val nameConfigReader: ConfigReader[Name] =
      ConfigReader[String].coerce
  }

  @newtype final case class Breed private (value: String)

  object Breed {
    def breeds(implicit loader: ResourceLoader): Seq[Breed] =
      loader.loadKey[Seq[Breed]]("cat.breeds")

    implicit def breedArbitrary(implicit
        loader: ResourceLoader
    ): Arbitrary[Breed] =
      Arbitrary(Gen.oneOf(breeds))

    implicit val breedConfigReader: ConfigReader[Breed] =
      ConfigReader[String].coerce
  }

  @newtype final case class Registry private (value: String)

  object Registry {
    def registries(implicit loader: ResourceLoader): Seq[Registry] =
      loader.loadKey[Seq[Registry]]("cat.registries")

    implicit def registryArbitrary(implicit
        loader: ResourceLoader
    ): Arbitrary[Registry] =
      Arbitrary(Gen.oneOf(registries))

    implicit val registryConfigReader: ConfigReader[Registry] =
      ConfigReader[String].coerce
  }
}
