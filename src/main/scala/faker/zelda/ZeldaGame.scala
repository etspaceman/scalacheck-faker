package faker.zelda

import org.scalacheck.{Arbitrary, Gen}
import pureconfig.ConfigReader
import pureconfig.generic.semiauto._

import faker.ResourceLoader

final case class ZeldaGame private (value: String) extends AnyVal

object ZeldaGame {
  def zeldaGames(implicit loader: ResourceLoader): Seq[ZeldaGame] =
    loader.loadKey[Seq[ZeldaGame]]("zelda.games")

  implicit def zeldaGameArbitrary(implicit
      loader: ResourceLoader
  ): Arbitrary[ZeldaGame] =
    Arbitrary(Gen.oneOf(zeldaGames))

  implicit val zeldaGameConfigReader: ConfigReader[ZeldaGame] =
    deriveReader
}
