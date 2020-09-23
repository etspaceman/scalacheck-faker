package faker.job

import org.scalacheck.{Arbitrary, Gen}
import pureconfig.ConfigReader
import pureconfig.generic.semiauto._

import faker.ResourceLoader

final case class JobEducationLevel private (value: String) extends AnyVal

object JobEducationLevel {
  def jobEducationLevels(implicit
      loader: ResourceLoader
  ): Seq[JobEducationLevel] =
    loader.loadKey[Seq[JobEducationLevel]]("job.education-levels")

  implicit def jobEducationLevelArbitrary(implicit
      loader: ResourceLoader
  ): Arbitrary[JobEducationLevel] =
    Arbitrary(Gen.oneOf(jobEducationLevels))

  implicit val jobEducationLevelConfigReader: ConfigReader[JobEducationLevel] =
    deriveReader
}
