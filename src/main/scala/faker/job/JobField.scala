package faker.job

import org.scalacheck.{Arbitrary, Gen}
import pureconfig.ConfigReader
import pureconfig.generic.semiauto._

import faker.ResourceLoader

final case class JobField private (value: String) extends AnyVal

object JobField {
  def jobFields(implicit loader: ResourceLoader): Seq[JobField] =
    loader.loadKey[Seq[JobField]]("job.fields")

  implicit def jobFieldArbitrary(implicit
      loader: ResourceLoader
  ): Arbitrary[JobField] =
    Arbitrary(Gen.oneOf(jobFields))

  implicit val jobFieldConfigReader: ConfigReader[JobField] =
    deriveReader
}
