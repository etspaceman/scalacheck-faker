package faker.job

import org.scalacheck.{Arbitrary, Gen}
import pureconfig.ConfigReader
import pureconfig.generic.semiauto._

import faker.ResourceLoader

final case class JobEmploymentType private (value: String) extends AnyVal

object JobEmploymentType {
  def jobEmploymentTypes(implicit
      loader: ResourceLoader
  ): Seq[JobEmploymentType] =
    loader.loadKey[Seq[JobEmploymentType]]("job.employment-types")

  implicit def jobEmploymentTypeArbitrary(implicit
      loader: ResourceLoader
  ): Arbitrary[JobEmploymentType] =
    Arbitrary(Gen.oneOf(jobEmploymentTypes))

  implicit val jobEmploymentTypeConfigReader: ConfigReader[JobEmploymentType] =
    deriveReader
}
