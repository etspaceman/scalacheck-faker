package faker

import io.estatico.newtype.macros.newtype
import io.estatico.newtype.ops._
import org.scalacheck.{Arbitrary, Gen}
import pureconfig.ConfigReader

object job {
  @newtype final case class JobEducationLevel private (value: String)

  object JobEducationLevel {
    def jobEducationLevels(implicit
        loader: ResourceLoader
    ): Seq[JobEducationLevel] =
      loader.loadKey[Seq[JobEducationLevel]]("job.education-levels")

    implicit def jobEducationLevelArbitrary(implicit
        loader: ResourceLoader
    ): Arbitrary[JobEducationLevel] =
      Arbitrary(Gen.oneOf(jobEducationLevels))

    implicit val jobEducationLevelConfigReader
        : ConfigReader[JobEducationLevel] =
      ConfigReader[String].coerce
  }

  @newtype final case class JobEmploymentType private (value: String)

  object JobEmploymentType {
    def jobEmploymentTypes(implicit
        loader: ResourceLoader
    ): Seq[JobEmploymentType] =
      loader.loadKey[Seq[JobEmploymentType]]("job.employment-types")

    implicit def jobEmploymentTypeArbitrary(implicit
        loader: ResourceLoader
    ): Arbitrary[JobEmploymentType] =
      Arbitrary(Gen.oneOf(jobEmploymentTypes))

    implicit val jobEmploymentTypeConfigReader
        : ConfigReader[JobEmploymentType] =
      ConfigReader[String].coerce
  }

  @newtype final case class JobField private (value: String)

  object JobField {
    def jobFields(implicit loader: ResourceLoader): Seq[JobField] =
      loader.loadKey[Seq[JobField]]("job.fields")

    implicit def jobFieldArbitrary(implicit
        loader: ResourceLoader
    ): Arbitrary[JobField] =
      Arbitrary(Gen.oneOf(jobFields))

    implicit val jobFieldConfigReader: ConfigReader[JobField] =
      ConfigReader[String].coerce
  }

  @newtype final case class JobKeySkill private (value: String)

  object JobKeySkill {
    def jobKeySkills(implicit loader: ResourceLoader): Seq[JobKeySkill] =
      loader.loadKey[Seq[JobKeySkill]]("job.key-skills")

    implicit def jobKeySkillArbitrary(implicit
        loader: ResourceLoader
    ): Arbitrary[JobKeySkill] =
      Arbitrary(Gen.oneOf(jobKeySkills))

    implicit val jobKeySkillConfigReader: ConfigReader[JobKeySkill] =
      ConfigReader[String].coerce
  }

  @newtype final case class JobPosition private (value: String)

  object JobPosition {
    def jobPositions(implicit loader: ResourceLoader): Seq[JobPosition] =
      loader.loadKey[Seq[JobPosition]]("job.positions")

    implicit def jobPositionArbitrary(implicit
        loader: ResourceLoader
    ): Arbitrary[JobPosition] =
      Arbitrary(Gen.oneOf(jobPositions))

    implicit val jobPositionConfigReader: ConfigReader[JobPosition] =
      ConfigReader[String].coerce
  }

  @newtype final case class JobSeniority private (value: String)

  object JobSeniority {
    def jobSeniority(implicit loader: ResourceLoader): Seq[JobSeniority] =
      loader.loadKey[Seq[JobSeniority]]("job.seniority")

    implicit def jobSeniorityArbitrary(implicit
        loader: ResourceLoader
    ): Arbitrary[JobSeniority] =
      Arbitrary(Gen.oneOf(jobSeniority))

    implicit val jobSeniorityConfigReader: ConfigReader[JobSeniority] =
      ConfigReader[String].coerce
  }

  @newtype final case class JobTitle private (value: String)

  object JobTitle {
    implicit def jobTitleArbitrary(implicit
        loader: ResourceLoader
    ): Arbitrary[JobTitle] =
      Arbitrary(
        loader
          .loadKey[StringGenBuilder]("job.title-builder")
          .gen
      ).coerce

    implicit val jobTitleConfigReader: ConfigReader[JobTitle] =
      ConfigReader[String].coerce
  }
}
