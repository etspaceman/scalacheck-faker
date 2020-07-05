package faker.company

import org.scalacheck.{Arbitrary, Gen}
import pureconfig.ConfigReader
import pureconfig.generic.semiauto._

import faker.ResourceLoader

final case class CompanySuffix private (value: String) extends AnyVal

object CompanySuffix {
  def companySuffixes(implicit loader: ResourceLoader): Seq[CompanySuffix] =
    loader.loadKey[Seq[CompanySuffix]]("company.suffixes")

  implicit def companySuffixArbitrary(implicit
      loader: ResourceLoader
  ): Arbitrary[CompanySuffix] =
    Arbitrary(Gen.oneOf(companySuffixes))

  implicit val companySuffixConfigReader: ConfigReader[CompanySuffix] =
    deriveReader
}
