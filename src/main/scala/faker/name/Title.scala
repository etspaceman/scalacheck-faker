package faker.name

import org.scalacheck.{Arbitrary, Gen}

import faker.ResourceLoader

final case class Title private (value: String) extends AnyVal

object Title {
  private val titleDescriptors: Seq[String] =
    ResourceLoader.default.loadStringList("name.title.descriptors")
  private val titleLevels: Seq[String] =
    ResourceLoader.default.loadStringList("name.title.levels")
  private val titleJobs: Seq[String] =
    ResourceLoader.default.loadStringList("name.title.jobs")
  implicit val titleArbitrary: Arbitrary[Title] = Arbitrary {
    for {
      descriptor <- Gen.oneOf(titleDescriptors)
      level <- Gen.oneOf(titleLevels)
      job <- Gen.oneOf(titleJobs)
    } yield Title(s"$descriptor $level $job")
  }
}
