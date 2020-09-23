package faker.job

import org.scalacheck.{Arbitrary, Gen}
import pureconfig.ConfigReader
import pureconfig.generic.semiauto._

import faker.ResourceLoader

final case class JobPosition private (value: String) extends AnyVal

object JobPosition {
  def jobPositions(implicit loader: ResourceLoader): Seq[JobPosition] =
    loader.loadKey[Seq[JobPosition]]("job.positions")

  implicit def jobPositionArbitrary(implicit
      loader: ResourceLoader
  ): Arbitrary[JobPosition] =
    Arbitrary(Gen.oneOf(jobPositions))

  implicit val jobPositionConfigReader: ConfigReader[JobPosition] =
    deriveReader
}
