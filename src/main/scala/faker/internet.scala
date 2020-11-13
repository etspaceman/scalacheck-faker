package faker

import scala.util.Try

import java.net.IDN

import io.estatico.newtype.macros.newtype
import io.estatico.newtype.ops.toCoercibleIdOps
import org.apache.commons.lang3.StringUtils
import org.scalacheck.{Arbitrary, Gen}
import pureconfig.ConfigReader

import faker.lorem.{LoremCharacters, LoremWord}
import faker.name.{FirstName, LastName, UserName}

object internet {

  object FakerIDN {
    def toASCII(in: String): String =
      Try(IDN.toASCII(in)).getOrElse(
        in.toList
          .flatMap(x => Try(IDN.toASCII(x.toString)).toOption.toList)
          .mkString
      )
  }

  /** Generates a random avatar url based on a collection of profile pictures of real people. All this avatar have been
    * authorized by its awesome users to be used on live websites (not just mockups). For more information, please
    * visit: http://uifaces.com/authorized
    *
    * @see <a href="http://uifaces.com/authorized">Authorized UI Faces</a>
    */
  @newtype final case class Avatar private (value: String)

  object Avatar {
    def avatars(implicit loader: ResourceLoader): Seq[String] =
      loader.loadKey[Seq[String]]("internet.avatars")

    implicit def avatarArbitrary(implicit
        loader: ResourceLoader
    ): Arbitrary[Avatar] =
      Arbitrary(
        Gen
          .oneOf(avatars)
          .map(x => s"https://s3.amazonaws.com/uifaces/faces/twitter/$x")
      ).coerce
  }

  @newtype final case class DomainSuffix private (value: String)

  object DomainSuffix {
    def domainSuffixes(implicit loader: ResourceLoader): Seq[DomainSuffix] =
      loader.loadKey[Seq[DomainSuffix]]("internet.domain-suffixes")

    implicit def domainSuffixArbitrary(implicit
        loader: ResourceLoader
    ): Arbitrary[DomainSuffix] =
      Arbitrary(Gen.oneOf(domainSuffixes))

    implicit val domainSuffixConfigReader: ConfigReader[DomainSuffix] =
      ConfigReader[String].coerce
  }

  @newtype final case class DomainWord private (value: String)

  object DomainWord {
    implicit def domainWordArbitrary(implicit
        loader: ResourceLoader
    ): Arbitrary[DomainWord] =
      Arbitrary(
        Arbitrary
          .arbitrary[LastName]
          .map(ln =>
            FakerIDN.toASCII(
              ln.value.toLowerCase().replaceAll("'", "").replaceAll(" ", "")
            )
          )
      ).coerce
  }

  @newtype final case class DomainName private (value: String)

  object DomainName {
    implicit def domainNameArbitrary(implicit
        loader: ResourceLoader
    ): Arbitrary[DomainName] =
      Arbitrary {
        for {
          word <- Arbitrary.arbitrary[DomainWord]
          suffix <- Arbitrary.arbitrary[DomainSuffix]
        } yield s"${word.value}.${suffix.value}"
      }.coerce
  }

  @newtype final case class EmailAddress private (value: String)

  object EmailAddress {
    def freeEmailDomains(implicit loader: ResourceLoader): Seq[String] =
      loader.loadKey[Seq[String]]("internet.free-email-domains")
    implicit def emailAddressArbitrary(implicit
        loader: ResourceLoader
    ): Arbitrary[EmailAddress] =
      Arbitrary(
        for {
          localPart <-
            Arbitrary
              .arbitrary[UserName]
              .map(x => StringUtils.stripAccents(x.value))
          domain <-
            Gen
              .oneOf(freeEmailDomains)
              .map(FakerIDN.toASCII)
        } yield s"$localPart@$domain"
      ).coerce
  }

  /** Generates a random image url based on the lorempixel service. All the images provided by this service are released
    * under the creative commons license (CC BY-SA). For more information, please visit: http://lorempixel.com/
    *
    * @see <a href="http://loremflickr.com/">lorempixel</a>
    */
  @newtype final case class Image private (value: String)

  object Image {
    def imageDimensions(implicit loader: ResourceLoader): Seq[String] =
      loader.loadKey[Seq[String]]("internet.image.dimensions")
    def imageCategories(implicit loader: ResourceLoader): Seq[String] =
      loader.loadKey[Seq[String]]("internet.image.categories")
    implicit def imageArbitrary(implicit
        loader: ResourceLoader
    ): Arbitrary[Image] =
      Arbitrary {
        for {
          dimension <- Gen.oneOf(imageDimensions)
          category <- Gen.oneOf(imageCategories)
          split = dimension.split("x").map(_.trim)
        } yield s"https://loremflickr.com/${split.head}/${split(1)}/$category"
      }.coerce
  }

  @newtype final case class IpV4Address private (value: String)

  object IpV4Address {
    implicit val ipV4AddressArbitrary: Arbitrary[IpV4Address] =
      Arbitrary(
        for {
          part1 <- Gen.choose(2, 255)
          part2 <- Gen.choose(0, 255)
          part3 <- Gen.choose(0, 255)
          part4 <- Gen.choose(0, 255)
        } yield s"$part1.$part2.$part3.$part4"
      ).coerce
  }

  @newtype final case class IpV4Cidr private (value: String)

  object IpV4Cidr {
    implicit val ipV4CidrArbitrary: Arbitrary[IpV4Cidr] =
      Arbitrary(
        for {
          ipV4Address <- Arbitrary.arbitrary[IpV4Address]
          cidrPart <- Gen.choose(1, 31)
        } yield s"${ipV4Address.value}/$cidrPart"
      ).coerce
  }

  @newtype final case class IpV6Address private (value: String)

  object IpV6Address {
    def ipV6Char: Gen[String] = Gen.choose(0, 15).map(Integer.toHexString)
    def ipV6Part: Gen[String] = Gen.listOfN(4, ipV6Char).map(_.mkString)
    implicit val ipV6AddressArbitrary: Arbitrary[IpV6Address] = Arbitrary(
      Gen.listOfN(8, ipV6Part).map(x => x.mkString(":"))
    ).coerce
  }

  @newtype final case class IpV6Cidr private (value: String)

  object IpV6Cidr {
    implicit val ipV6CidrArbitrary: Arbitrary[IpV6Cidr] = Arbitrary(
      for {
        ipv6 <- Arbitrary.arbitrary[IpV6Address]
        cidrPart <- Gen.choose(1, 127)
      } yield s"${ipv6.value}/$cidrPart"
    ).coerce
  }

  @newtype final case class MacAddress private (value: String)

  object MacAddress {
    private def macAddressChar: Gen[String] =
      Gen.choose(0, 15).map(Integer.toHexString)
    private def macAddressSection =
      macAddressChar.flatMap(x => macAddressChar.map(y => x + y))
    implicit val macAddressArbitrary: Arbitrary[MacAddress] = Arbitrary(
      Gen.listOfN(6, macAddressSection).map(x => x.mkString(":"))
    ).coerce
  }

  @newtype final case class Password private (value: String)

  object Password {
    def passwordSpecialCharacters(implicit
        loader: ResourceLoader
    ): Seq[String] =
      loader.loadKey[Seq[String]]("internet.password.special-characters")
    implicit def passwordArbitrary(implicit
        loader: ResourceLoader
    ): Arbitrary[Password] =
      Arbitrary(
        for {
          charNum <- Gen.choose(8, 20)
          pass <-
            Gen
              .listOfN(
                charNum,
                Gen.frequency(
                  4 -> Gen.oneOf(LoremCharacters.loremCharacters),
                  1 -> Gen.oneOf(passwordSpecialCharacters)
                )
              )
              .map(_.mkString)
        } yield pass
      ).coerce
  }

  @newtype final case class PrivateIpV4Address private (value: String)

  object PrivateIpV4Address {
    private val privateFirstParts: Seq[Int] = Seq(10, 127, 169, 192, 172)
    private val privateSecondPartsFor172: Seq[Int] =
      Seq(16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31)
    implicit val privateIpV4AddressArbitrary: Arbitrary[PrivateIpV4Address] =
      Arbitrary(
        for {
          part1 <- Gen.oneOf(privateFirstParts)
          part2 <- part1 match {
            case 172 => Gen.oneOf(privateSecondPartsFor172)
            case 192 => Gen.const(168)
            case 169 => Gen.const(254)
            case _   => Gen.choose(2, 255)
          }
          part3 <- Gen.choose(0, 255)
          part4 <- Gen.choose(0, 255)
        } yield s"$part1.$part2.$part3.$part4"
      ).coerce
  }

  @newtype final case class PublicIpV4Address private (value: String)

  object PublicIpV4Address {
    private val privateFirstParts: Seq[Int] = Seq(10, 127, 169, 192, 172)
    private val publicIpV4FirstPart: Gen[Int] =
      Gen.choose(2, 255).retryUntil(!privateFirstParts.contains(_))
    implicit val publicIpV4AddressArbitrary: Arbitrary[PublicIpV4Address] =
      Arbitrary(
        for {
          ipAddress <- Arbitrary.arbitrary[IpV4Address]
          origParts = ipAddress.value.split("\\.").toList.map(_.toInt)
          publicFirstPart <- publicIpV4FirstPart
          newParts = publicFirstPart +: origParts.drop(1)
        } yield newParts.mkString(".")
      ).coerce
  }

  @newtype final case class SafeEmailAddress private (value: String)

  object SafeEmailAddress {
    def safeEmailDomains(implicit loader: ResourceLoader): Seq[String] =
      loader.loadKey[Seq[String]]("internet.safe-email-domains")
    implicit def safeEmailAddressArbitrary(implicit
        loader: ResourceLoader
    ): Arbitrary[SafeEmailAddress] =
      Arbitrary(
        for {
          localPart <-
            Arbitrary
              .arbitrary[UserName]
              .map(x => StringUtils.stripAccents(x.value))
          domain <- Gen.oneOf(safeEmailDomains).map(FakerIDN.toASCII)
        } yield s"$localPart@$domain"
      ).coerce
  }

  @newtype final case class Slug private (value: String)

  object Slug {
    implicit def slugArbitrary(implicit
        loader: ResourceLoader
    ): Arbitrary[Slug] =
      Arbitrary(
        Gen
          .listOfN(2, Arbitrary.arbitrary[LoremWord])
          .map(x => x.map(_.value).mkString("_"))
      ).coerce
  }

  @newtype final case class Url private (value: String)

  object Url {
    implicit def urlArbitrary(implicit loader: ResourceLoader): Arbitrary[Url] =
      Arbitrary(
        for {
          firstName <- Arbitrary.arbitrary[FirstName]
          firstNameProcessed = firstName.value.toLowerCase().replaceAll("'", "")
          domainWord <- Arbitrary.arbitrary[DomainWord]
          domainSuffix <- Arbitrary.arbitrary[DomainSuffix]
        } yield s"www.$firstNameProcessed-${domainWord.value}.${domainSuffix.value}"
      ).coerce
  }

  @newtype final case class UserAgent private (value: String)

  object UserAgent {
    def userAgents(loader: ResourceLoader): Map[String, Seq[String]] =
      loader.loadKey[Map[String, Seq[String]]](s"internet.user-agents")
    implicit def userAgentArbitrary(implicit
        loader: ResourceLoader
    ): Arbitrary[UserAgent] =
      Arbitrary(
        Gen.oneOf(userAgents(loader).values.flatten)
      ).coerce
  }
}
