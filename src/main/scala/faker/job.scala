/*
 * Copyright (c) 2020 etspaceman
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of
 * this software and associated documentation files (the "Software"), to deal in
 * the Software without restriction, including without limitation the rights to
 * use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of
 * the Software, and to permit persons to whom the Software is furnished to do so,
 * subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
 * FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 * COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER
 * IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
 * CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package faker

import org.scalacheck.{Arbitrary, Gen}
import pureconfig.ConfigReader

object job {
  type JobEducationLevel = JobEducationLevel.Type

  object JobEducationLevel extends Newtype[String] { self =>
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
      ConfigReader[String].map(self.apply)
  }

  type JobEmploymentType = JobEmploymentType.Type

  object JobEmploymentType extends Newtype[String] { self =>
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
      ConfigReader[String].map(self.apply)
  }

  type JobField = JobField.Type

  object JobField extends Newtype[String] { self =>
    def jobFields(implicit loader: ResourceLoader): Seq[JobField] =
      loader.loadKey[Seq[JobField]]("job.fields")

    implicit def jobFieldArbitrary(implicit
        loader: ResourceLoader
    ): Arbitrary[JobField] =
      Arbitrary(Gen.oneOf(jobFields))

    implicit val jobFieldConfigReader: ConfigReader[JobField] =
      ConfigReader[String].map(self.apply)
  }

  type JobKeySkill = JobKeySkill.Type

  object JobKeySkill extends Newtype[String] { self =>
    def jobKeySkills(implicit loader: ResourceLoader): Seq[JobKeySkill] =
      loader.loadKey[Seq[JobKeySkill]]("job.key-skills")

    implicit def jobKeySkillArbitrary(implicit
        loader: ResourceLoader
    ): Arbitrary[JobKeySkill] =
      Arbitrary(Gen.oneOf(jobKeySkills))

    implicit val jobKeySkillConfigReader: ConfigReader[JobKeySkill] =
      ConfigReader[String].map(self.apply)
  }

  type JobPosition = JobPosition.Type

  object JobPosition extends Newtype[String] { self =>
    def jobPositions(implicit loader: ResourceLoader): Seq[JobPosition] =
      loader.loadKey[Seq[JobPosition]]("job.positions")

    implicit def jobPositionArbitrary(implicit
        loader: ResourceLoader
    ): Arbitrary[JobPosition] =
      Arbitrary(Gen.oneOf(jobPositions))

    implicit val jobPositionConfigReader: ConfigReader[JobPosition] =
      ConfigReader[String].map(self.apply)
  }

  type JobSeniority = JobSeniority.Type

  object JobSeniority extends Newtype[String] { self =>
    def jobSeniority(implicit loader: ResourceLoader): Seq[JobSeniority] =
      loader.loadKey[Seq[JobSeniority]]("job.seniority")

    implicit def jobSeniorityArbitrary(implicit
        loader: ResourceLoader
    ): Arbitrary[JobSeniority] =
      Arbitrary(Gen.oneOf(jobSeniority))

    implicit val jobSeniorityConfigReader: ConfigReader[JobSeniority] =
      ConfigReader[String].map(self.apply)
  }

  type JobTitle = JobTitle.Type

  object JobTitle extends Newtype[String] { self =>
    implicit def jobTitleArbitrary(implicit
        loader: ResourceLoader
    ): Arbitrary[JobTitle] =
      Arbitrary(
        loader
          .loadKey[StringGenBuilder]("job.title-builder")
          .gen
          .map(self.apply)
      )

    implicit val jobTitleConfigReader: ConfigReader[JobTitle] =
      ConfigReader[String].map(self.apply)
  }
}
