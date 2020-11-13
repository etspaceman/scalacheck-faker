package faker

import io.estatico.newtype.macros.newtype
import io.estatico.newtype.ops._
import org.scalacheck.{Arbitrary, Gen}
import pureconfig.ConfigReader

object name {
  @newtype final case class FirstName private (value: String)

  object FirstName {
    def firstNames(implicit loader: ResourceLoader): Seq[FirstName] =
      loader.loadKey[Seq[FirstName]]("name.first.names")

    implicit def firstNameArbitrary(implicit
        loader: ResourceLoader
    ): Arbitrary[FirstName] =
      Arbitrary(Gen.oneOf(firstNames))

    implicit val firstNameConfigReader: ConfigReader[FirstName] =
      ConfigReader[String].coerce
  }

  @newtype final case class FullName private (value: String)

  object FullName {
    implicit def fullNameArbitrary(implicit
        loader: ResourceLoader
    ): Arbitrary[FullName] =
      Arbitrary(
        loader
          .loadKey[StringGenBuilder]("name.full-name-builder")
          .gen
      ).coerce
  }

  @newtype final case class FullNameWithMiddle private (value: String)

  object FullNameWithMiddle {
    implicit def fullNameWithMiddleArbitrary(implicit
        loader: ResourceLoader
    ): Arbitrary[FullNameWithMiddle] =
      Arbitrary(
        loader
          .loadKey[StringGenBuilder]("name.full-name-with-middle-builder")
          .gen
      ).coerce
  }

  @newtype final case class LastName private (value: String)

  object LastName {
    def lastNames(implicit loader: ResourceLoader): Seq[LastName] =
      loader.loadKey[Seq[LastName]]("name.last.names")
    implicit def lastNameArbitrary(implicit
        loader: ResourceLoader
    ): Arbitrary[LastName] =
      Arbitrary(Gen.oneOf(lastNames))

    implicit val lastNameConfigReader: ConfigReader[LastName] =
      ConfigReader[String].coerce
  }

  @newtype final case class Prefix private (value: String)

  object Prefix {
    def prefixes(implicit loader: ResourceLoader): Seq[Prefix] =
      loader.loadKey[Seq[Prefix]]("name.prefixes")
    implicit def prefixArbitrary(implicit
        loader: ResourceLoader
    ): Arbitrary[Prefix] =
      Arbitrary(Gen.oneOf(prefixes))

    implicit val prefixConfigReader: ConfigReader[Prefix] =
      ConfigReader[String].coerce
  }

  @newtype final case class Suffix private (value: String)

  object Suffix {
    def suffixes(implicit loader: ResourceLoader): Seq[Suffix] =
      loader.loadKey[Seq[Suffix]]("name.suffixes")
    implicit def suffixArbitrary(implicit
        loader: ResourceLoader
    ): Arbitrary[Suffix] = Arbitrary(Gen.oneOf(suffixes))

    implicit val suffixConfigReader: ConfigReader[Suffix] =
      ConfigReader[String].coerce
  }

  @newtype final case class Title private (value: String)

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
        } yield s"$descriptor $level $job"
      }.coerce
  }

  @newtype final case class UserName private (value: String)

  object UserName {
    implicit def userNameArbitrary(implicit
        loader: ResourceLoader
    ): Arbitrary[UserName] =
      Arbitrary {
        for {
          firstName <-
            Arbitrary
              .arbitrary[FirstName]
              .map(
                _.value.replaceAll("'", "").replaceAll(" ", "").toLowerCase()
              )
          lastName <-
            Arbitrary
              .arbitrary[LastName]
              .map(
                _.value.replaceAll("'", "").replaceAll(" ", "").toLowerCase()
              )
        } yield s"$firstName.$lastName"
      }.coerce
  }
}
