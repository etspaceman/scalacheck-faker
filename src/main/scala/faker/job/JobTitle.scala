package faker.job

import org.scalacheck.Arbitrary
import pureconfig.ConfigReader
import pureconfig.generic.semiauto._

import faker.{ResourceLoader, StringGenBuilder}

final case class JobTitle private (value: String) extends AnyVal

object JobTitle {
  implicit def jobTitleArbitrary(implicit
      loader: ResourceLoader
  ): Arbitrary[JobTitle] =
    Arbitrary(
      loader
        .loadKey[StringGenBuilder]("job.title-builder")
        .gen
        .map(x => JobTitle(x))
    )

  implicit val jobTitleConfigReader: ConfigReader[JobTitle] =
    deriveReader
}
