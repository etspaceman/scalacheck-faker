package faker

import io.estatico.newtype.macros.newtype
import io.estatico.newtype.ops._
import org.scalacheck.{Arbitrary, Gen}
import pureconfig.ConfigReader

object ancient {
  @newtype final case class God private (value: String)

  object God {
    def gods(implicit loader: ResourceLoader): Seq[God] =
      loader.loadKey[Seq[God]]("ancient.gods")

    implicit def godArbitrary(implicit
        loader: ResourceLoader
    ): Arbitrary[God] =
      Arbitrary(Gen.oneOf(gods))

    implicit val godConfigReader: ConfigReader[God] =
      ConfigReader[String].coerce
  }

  @newtype final case class Primordial private (value: String)

  object Primordial {
    def primordials(implicit loader: ResourceLoader): Seq[Primordial] =
      loader.loadKey[Seq[Primordial]]("ancient.primordials")

    implicit def primordialArbitrary(implicit
        loader: ResourceLoader
    ): Arbitrary[Primordial] =
      Arbitrary(Gen.oneOf(primordials))

    implicit val primordialConfigReader: ConfigReader[Primordial] =
      ConfigReader[String].coerce
  }

  @newtype final case class Titan private (value: String)

  object Titan {
    def titans(implicit loader: ResourceLoader): Seq[Titan] =
      loader.loadKey[Seq[Titan]]("ancient.titans")

    implicit def primordialArbitrary(implicit
        loader: ResourceLoader
    ): Arbitrary[Titan] =
      Arbitrary(Gen.oneOf(titans))

    implicit val titanConfigReader: ConfigReader[Titan] =
      ConfigReader[String].coerce
  }

  @newtype final case class Hero private (value: String)

  object Hero {
    def heroes(implicit loader: ResourceLoader): Seq[Hero] =
      loader.loadKey[Seq[Hero]]("ancient.heroes")

    implicit def heroArbitrary(implicit
        loader: ResourceLoader
    ): Arbitrary[Hero] =
      Arbitrary(Gen.oneOf(heroes))

    implicit val heroConfigReader: ConfigReader[Hero] =
      ConfigReader[String].coerce
  }
}
