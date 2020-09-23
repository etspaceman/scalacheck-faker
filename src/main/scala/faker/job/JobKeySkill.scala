package faker.job

import org.scalacheck.{Arbitrary, Gen}
import pureconfig.ConfigReader
import pureconfig.generic.semiauto._

import faker.ResourceLoader

final case class JobKeySkill private (value: String) extends AnyVal

object JobKeySkill {
  def jobKeySkills(implicit loader: ResourceLoader): Seq[JobKeySkill] =
    loader.loadKey[Seq[JobKeySkill]]("job.key-skills")

  implicit def jobKeySkillArbitrary(implicit
      loader: ResourceLoader
  ): Arbitrary[JobKeySkill] =
    Arbitrary(Gen.oneOf(jobKeySkills))

  implicit val jobKeySkillConfigReader: ConfigReader[JobKeySkill] =
    deriveReader
}
