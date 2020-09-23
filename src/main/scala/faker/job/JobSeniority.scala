package faker.job

import org.scalacheck.{Arbitrary, Gen}
import pureconfig.ConfigReader
import pureconfig.generic.semiauto._

import faker.ResourceLoader

final case class JobSeniority private (value: String) extends AnyVal

object JobSeniority {
  def jobSeniority(implicit loader: ResourceLoader): Seq[JobSeniority] =
    loader.loadKey[Seq[JobSeniority]]("job.seniority")

  implicit def jobSeniorityArbitrary(implicit
      loader: ResourceLoader
  ): Arbitrary[JobSeniority] =
    Arbitrary(Gen.oneOf(jobSeniority))

  implicit val jobSeniorityConfigReader: ConfigReader[JobSeniority] =
    deriveReader
}
