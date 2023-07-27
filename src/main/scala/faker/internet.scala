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

import scala.util.Try

import java.net.IDN

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

  /** Generates a random avatar url based on a collection of profile pictures of
    * real people. All this avatar have been authorized by its awesome users to
    * be used on live websites (not just mockups). For more information, please
    * visit: http://uifaces.com/authorized
    *
    * @see
    *   <a href="http://uifaces.com/authorized">Authorized UI Faces</a>
    */
  type Avatar = Avatar.Type

  object Avatar extends Newtype[String] { self =>
    def avatars(implicit loader: ResourceLoader): Seq[String] =
      loader.loadKey[Seq[String]]("internet.avatars")

    implicit def avatarArbitrary(implicit
        loader: ResourceLoader
    ): Arbitrary[Avatar] =
      Arbitrary(
        Gen
          .oneOf(avatars)
          .map(x => s"https://s3.amazonaws.com/uifaces/faces/twitter/$x")
          .map(self.apply)
      )
  }

  type DomainSuffix = DomainSuffix.Type

  object DomainSuffix extends Newtype[String] { self =>
    def domainSuffixes(implicit loader: ResourceLoader): Seq[DomainSuffix] =
      loader.loadKey[Seq[DomainSuffix]]("internet.domain-suffixes")

    implicit def domainSuffixArbitrary(implicit
        loader: ResourceLoader
    ): Arbitrary[DomainSuffix] =
      Arbitrary(Gen.oneOf(domainSuffixes))

    implicit val domainSuffixConfigReader: ConfigReader[DomainSuffix] =
      ConfigReader[String].map(self.apply)
  }

  type DomainWord = DomainWord.Type

  object DomainWord extends Newtype[String] { self =>
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
          .map(self.apply)
      )
  }

  type DomainName = DomainName.Type

  object DomainName extends Newtype[String] { self =>
    implicit def domainNameArbitrary(implicit
        loader: ResourceLoader
    ): Arbitrary[DomainName] =
      Arbitrary {
        (for {
          word <- Arbitrary.arbitrary[DomainWord]
          suffix <- Arbitrary.arbitrary[DomainSuffix]
        } yield s"${word.value}.${suffix.value}").map(self.apply)
      }
  }

  type EmailAddress = EmailAddress.Type

  object EmailAddress extends Newtype[String] { self =>
    def freeEmailDomains(implicit loader: ResourceLoader): Seq[String] =
      loader.loadKey[Seq[String]]("internet.free-email-domains")
    implicit def emailAddressArbitrary(implicit
        loader: ResourceLoader
    ): Arbitrary[EmailAddress] =
      Arbitrary(
        (for {
          localPart <-
            Arbitrary
              .arbitrary[UserName]
              .map(x => StringUtils.stripAccents(x.value))
          domain <-
            Gen
              .oneOf(freeEmailDomains)
              .map(FakerIDN.toASCII)
        } yield s"$localPart@$domain").map(self.apply)
      )
  }

  /** Generates a random image url based on the lorempixel service. All the
    * images provided by this service are released under the creative commons
    * license (CC BY-SA). For more information, please visit:
    * http://lorempixel.com/
    *
    * @see
    *   <a href="http://loremflickr.com/">lorempixel</a>
    */
  type Image = Image.Type

  object Image extends Newtype[String] { self =>
    def imageDimensions(implicit loader: ResourceLoader): Seq[String] =
      loader.loadKey[Seq[String]]("internet.image.dimensions")
    def imageCategories(implicit loader: ResourceLoader): Seq[String] =
      loader.loadKey[Seq[String]]("internet.image.categories")
    implicit def imageArbitrary(implicit
        loader: ResourceLoader
    ): Arbitrary[Image] =
      Arbitrary {
        (for {
          dimension <- Gen.oneOf(imageDimensions)
          category <- Gen.oneOf(imageCategories)
          split = dimension.split("x").map(_.trim)
        } yield s"https://loremflickr.com/${split.head}/${split(1)}/$category")
          .map(self.apply)
      }
  }

  type IpV4Address = IpV4Address.Type

  object IpV4Address extends Newtype[String] { self =>
    implicit val ipV4AddressArbitrary: Arbitrary[IpV4Address] =
      Arbitrary(
        (for {
          part1 <- Gen.choose(2, 255)
          part2 <- Gen.choose(0, 255)
          part3 <- Gen.choose(0, 255)
          part4 <- Gen.choose(0, 255)
        } yield s"$part1.$part2.$part3.$part4").map(self.apply)
      )
  }

  type IpV4Cidr = IpV4Cidr.Type

  object IpV4Cidr extends Newtype[String] { self =>
    implicit val ipV4CidrArbitrary: Arbitrary[IpV4Cidr] =
      Arbitrary(
        (for {
          ipV4Address <- Arbitrary.arbitrary[IpV4Address]
          cidrPart <- Gen.choose(1, 31)
        } yield s"${ipV4Address.value}/$cidrPart").map(self.apply)
      )
  }

  type IpV6Address = IpV6Address.Type

  object IpV6Address extends Newtype[String] { self =>
    def ipV6Char: Gen[String] = Gen.choose(0, 15).map(Integer.toHexString)
    def ipV6Part: Gen[String] = Gen.listOfN(4, ipV6Char).map(_.mkString)
    implicit val ipV6AddressArbitrary: Arbitrary[IpV6Address] = Arbitrary(
      Gen.listOfN(8, ipV6Part).map(x => x.mkString(":")).map(self.apply)
    )
  }

  type IpV6Cidr = IpV6Cidr.Type

  object IpV6Cidr extends Newtype[String] { self =>
    implicit val ipV6CidrArbitrary: Arbitrary[IpV6Cidr] = Arbitrary(
      (for {
        ipv6 <- Arbitrary.arbitrary[IpV6Address]
        cidrPart <- Gen.choose(1, 127)
      } yield s"${ipv6.value}/$cidrPart").map(self.apply)
    )
  }

  type MacAddress = MacAddress.Type

  object MacAddress extends Newtype[String] { self =>
    private def macAddressChar: Gen[String] =
      Gen.choose(0, 15).map(Integer.toHexString)
    private def macAddressSection =
      macAddressChar.flatMap(x => macAddressChar.map(y => x + y))
    implicit val macAddressArbitrary: Arbitrary[MacAddress] = Arbitrary(
      Gen
        .listOfN(6, macAddressSection)
        .map(x => x.mkString(":"))
        .map(self.apply)
    )
  }

  type Password = Password.Type

  object Password extends Newtype[String] { self =>
    def passwordSpecialCharacters(implicit
        loader: ResourceLoader
    ): Seq[Char] =
      loader.loadKey[Seq[Char]]("internet.password.special-characters")
    implicit def passwordArbitrary(implicit
        loader: ResourceLoader
    ): Arbitrary[Password] =
      Arbitrary(
        (for {
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
        } yield pass).map(self.apply)
      )
  }

  type PrivateIpV4Address = PrivateIpV4Address.Type

  object PrivateIpV4Address extends Newtype[String] { self =>
    private val privateFirstParts: Seq[Int] = Seq(10, 127, 169, 192, 172)
    private val privateSecondPartsFor172: Seq[Int] =
      Seq(16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31)
    implicit val privateIpV4AddressArbitrary: Arbitrary[PrivateIpV4Address] =
      Arbitrary(
        (for {
          part1 <- Gen.oneOf(privateFirstParts)
          part2 <- part1 match {
            case 172 => Gen.oneOf(privateSecondPartsFor172)
            case 192 => Gen.const(168)
            case 169 => Gen.const(254)
            case _   => Gen.choose(2, 255)
          }
          part3 <- Gen.choose(0, 255)
          part4 <- Gen.choose(0, 255)
        } yield s"$part1.$part2.$part3.$part4").map(self.apply)
      )
  }

  type PublicIpV4Address = PublicIpV4Address.Type

  object PublicIpV4Address extends Newtype[String] { self =>
    private val privateFirstParts: Seq[Int] = Seq(10, 127, 169, 192, 172)
    private val publicIpV4FirstPart: Gen[Int] =
      Gen.choose(2, 255).retryUntil(!privateFirstParts.contains(_))
    implicit val publicIpV4AddressArbitrary: Arbitrary[PublicIpV4Address] =
      Arbitrary(
        (for {
          ipAddress <- Arbitrary.arbitrary[IpV4Address]
          origParts = ipAddress.value.split("\\.").toList.map(_.toInt)
          publicFirstPart <- publicIpV4FirstPart
          newParts = publicFirstPart +: origParts.drop(1)
        } yield newParts.mkString(".")).map(self.apply)
      )
  }

  type SafeEmailAddress = SafeEmailAddress.Type

  object SafeEmailAddress extends Newtype[String] { self =>
    def safeEmailDomains(implicit loader: ResourceLoader): Seq[String] =
      loader.loadKey[Seq[String]]("internet.safe-email-domains")
    implicit def safeEmailAddressArbitrary(implicit
        loader: ResourceLoader
    ): Arbitrary[SafeEmailAddress] =
      Arbitrary(
        (for {
          localPart <-
            Arbitrary
              .arbitrary[UserName]
              .map(x => StringUtils.stripAccents(x.value))
          domain <- Gen.oneOf(safeEmailDomains).map(FakerIDN.toASCII)
        } yield s"$localPart@$domain").map(self.apply)
      )
  }

  type Slug = Slug.Type

  object Slug extends Newtype[String] { self =>
    implicit def slugArbitrary(implicit
        loader: ResourceLoader
    ): Arbitrary[Slug] =
      Arbitrary(
        Gen
          .listOfN(2, Arbitrary.arbitrary[LoremWord])
          .map(x => x.map(_.value).mkString("_"))
          .map(self.apply)
      )
  }

  type Url = Url.Type

  object Url extends Newtype[String] { self =>
    implicit def urlArbitrary(implicit loader: ResourceLoader): Arbitrary[Url] =
      Arbitrary(
        (for {
          firstName <- Arbitrary.arbitrary[FirstName]
          firstNameProcessed = firstName.value.toLowerCase().replaceAll("'", "")
          domainWord <- Arbitrary.arbitrary[DomainWord]
          domainSuffix <- Arbitrary.arbitrary[DomainSuffix]
        } yield s"www.$firstNameProcessed-${domainWord.value}.${domainSuffix.value}")
          .map(self.apply)
      )
  }

  type UserAgent = UserAgent.Type

  object UserAgent extends Newtype[String] { self =>
    def userAgents(loader: ResourceLoader): Map[String, Seq[String]] =
      loader.loadKey[Map[String, Seq[String]]](s"internet.user-agents")
    implicit def userAgentArbitrary(implicit
        loader: ResourceLoader
    ): Arbitrary[UserAgent] =
      Arbitrary(
        Gen.oneOf(userAgents(loader).values.flatten).map(self.apply)
      )
  }
}
