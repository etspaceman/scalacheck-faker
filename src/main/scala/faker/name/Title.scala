package faker.name

import org.scalacheck.{Arbitrary, Gen}

import faker.ResourceLoader

final case class Title private (value: String) extends AnyVal

object Title {
  def titleDescriptors(implicit loader: ResourceLoader): Seq[String] =
    loader.loadKey[Seq[String]]("name.title.descriptors")
  def titleLevels(implicit loader: ResourceLoader): Seq[String] =
    loader.loadKey[Seq[String]]("name.title.levels")
  def titleJobs(implicit loader: ResourceLoader): Seq[String] =
    loader.loadKey[Seq[String]]("name.title.jobs")
  implicit def titleArbitrary(implicit
      loader: ResourceLoader
  ): Arbitrary[Title] =
    Arbitrary {
      for {
        descriptor <- Gen.oneOf(titleDescriptors)
        level <- Gen.oneOf(titleLevels)
        job <- Gen.oneOf(titleJobs)
      } yield Title(s"$descriptor $level $job")
    }
}
