package faker

import io.estatico.newtype.macros.newtype
import io.estatico.newtype.ops.toCoercibleIdOps
import org.scalacheck.{Arbitrary, Gen}
import pureconfig.ConfigReader

import faker.internet.{DomainSuffix, FakerIDN}

object company {
  @newtype final case class BS private (value: String)

  object BS {
    implicit def bsArbitrary(implicit
        loader: ResourceLoader
    ): Arbitrary[BS] =
      Arbitrary(
        loader.loadKey[StringGenBuilder]("company.bs-builder").gen
      ).coerce
  }

  @newtype final case class BuzzWord private (value: String)

  object BuzzWord {
    def buzzWords(implicit loader: ResourceLoader): Seq[BuzzWord] =
      loader.loadKey[Seq[BuzzWord]]("company.buzz-words")
    implicit def buzzWordArbitrary(implicit
        loader: ResourceLoader
    ): Arbitrary[BuzzWord] = Arbitrary(Gen.oneOf(buzzWords))

    implicit val buzzWordConfigReader: ConfigReader[BuzzWord] =
      ConfigReader[String].coerce
  }

  @newtype final case class CatchPhrase private (value: String)

  object CatchPhrase {
    implicit def catchPhraseArbitrary(implicit
        loader: ResourceLoader
    ): Arbitrary[CatchPhrase] =
      Arbitrary(
        loader
          .loadKey[StringGenBuilder]("company.catch-phrase-builder")
          .gen
      ).coerce
  }

  @newtype final case class CompanyName private (value: String)

  object CompanyName {
    implicit def companyNameArbitrary(implicit
        loader: ResourceLoader
    ): Arbitrary[CompanyName] =
      Arbitrary(
        loader
          .loadKey[StringGenBuilder]("company.company-name-builder")
          .gen
      ).coerce
  }

  @newtype final case class CompanyDomainName private (value: String)

  object CompanyDomainName {
    implicit def companyDomainNameArbitrary(implicit
        loader: ResourceLoader
    ): Arbitrary[CompanyDomainName] =
      Arbitrary(
        Arbitrary
          .arbitrary[CompanyName]
          .map(ln =>
            FakerIDN.toASCII(
              ln.value
                .toLowerCase()
                .replaceAll("'", "")
                .replaceAll(" ", "")
                .replaceAll(",", "\\.")
            )
          )
      ).coerce
  }

  @newtype final case class CompanySuffix private (value: String)

  object CompanySuffix {
    def companySuffixes(implicit loader: ResourceLoader): Seq[CompanySuffix] =
      loader.loadKey[Seq[CompanySuffix]]("company.suffixes")

    implicit def companySuffixArbitrary(implicit
        loader: ResourceLoader
    ): Arbitrary[CompanySuffix] =
      Arbitrary(Gen.oneOf(companySuffixes))

    implicit val companySuffixConfigReader: ConfigReader[CompanySuffix] =
      ConfigReader[String].coerce
  }

  @newtype final case class CompanyUrl private (value: String)

  object CompanyUrl {
    implicit def companyUrlArbitrary(implicit
        loader: ResourceLoader
    ): Arbitrary[CompanyUrl] =
      Arbitrary(
        for {
          domainName <- Arbitrary.arbitrary[CompanyDomainName]
          domainSuffix <- Arbitrary.arbitrary[DomainSuffix]
        } yield s"www.${domainName.value}.${domainSuffix.value}"
      ).coerce
  }

  @newtype final case class Industry private (value: String)

  object Industry {
    def industries(implicit loader: ResourceLoader): Seq[Industry] =
      loader.loadKey[Seq[Industry]]("company.industries")
    implicit def industryArbitrary(implicit
        loader: ResourceLoader
    ): Arbitrary[Industry] = Arbitrary(Gen.oneOf(industries))

    implicit val industryConfigReader: ConfigReader[Industry] =
      ConfigReader[String].coerce
  }

  @newtype final case class Logo private (value: String)

  object Logo {
    implicit val logoArbitrary: Arbitrary[Logo] =
      Arbitrary(
        Gen
          .choose(1, 13)
          .map(x =>
            s"https://pigment.github.io/fake-logos/logos/medium/color/$x.png"
          )
      ).coerce
  }

  @newtype final case class Profession private (value: String)

  object Profession {
    def professions(implicit loader: ResourceLoader): Seq[Profession] =
      loader.loadKey[Seq[Profession]]("company.professions")
    implicit def professionArbitrary(implicit
        loader: ResourceLoader
    ): Arbitrary[Profession] = Arbitrary(Gen.oneOf(professions))

    implicit val professionConfigReader: ConfigReader[Profession] =
      ConfigReader[String].coerce
  }
}
