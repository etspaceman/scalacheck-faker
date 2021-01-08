package faker

import io.estatico.newtype.macros.newtype
import io.estatico.newtype.ops._
import org.scalacheck.{Arbitrary, Gen}
import pureconfig.ConfigReader

object aviation {
  @newtype final case class Aircraft private (value: String)

  object Aircraft {
    def aircrafts(implicit loader: ResourceLoader): Seq[Aircraft] =
      loader.loadKey[Seq[Aircraft]]("aviation.aircrafts")

    implicit def aircraftArbitrary(implicit
        loader: ResourceLoader
    ): Arbitrary[Aircraft] =
      Arbitrary(Gen.oneOf(aircrafts))

    implicit val aviationConfigReader: ConfigReader[Aircraft] =
      ConfigReader[String].coerce
  }

  @newtype final case class Airport private (value: String)

  object Airport {
    def airports(implicit loader: ResourceLoader): Seq[Airport] =
      loader.loadKey[Seq[Airport]]("aviation.airports")

    implicit def airportArbitrary(implicit
      loader: ResourceLoader
    ): Arbitrary[Airport] =
      Arbitrary(Gen.oneOf(airports))

    implicit val airportConfigReader: ConfigReader[Airport] =
      ConfigReader[String].coerce
  }


  @newtype final case class Metar private (value: String)

  object Metar {
    def metars(implicit loader: ResourceLoader): Seq[Metar] =
      loader.loadKey[Seq[Metar]]("aviation.metars")

    implicit def metarArbitrary(implicit
      loader: ResourceLoader
    ): Arbitrary[Metar] =
      Arbitrary(Gen.oneOf(metars))

    implicit val metarConfigReader: ConfigReader[Metar] =
      ConfigReader[String].coerce
  }
}
