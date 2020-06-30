package faker.name

import faker.ResourceLoader
import org.scalacheck.{Arbitrary, Gen}

final case class Title private (value: String) extends AnyVal

object Title {
  private val titleDescriptors: Seq[String] =
    ResourceLoader.loadStringList("name.title.descriptors")
  private val titleLevels: Seq[String] =
    ResourceLoader.loadStringList("name.title.levels")
  private val titleJobs: Seq[String] =
    ResourceLoader.loadStringList("name.title.jobs")
  implicit val titleArbitrary: Arbitrary[Title] = Arbitrary {
    for {
      descriptor <- Gen.oneOf(titleDescriptors)
      level <- Gen.oneOf(titleLevels)
      job <- Gen.oneOf(titleJobs)
    } yield Title(s"$descriptor $level $job")
  }
}
