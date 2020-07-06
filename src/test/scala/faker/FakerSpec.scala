package faker

import java.time.{Instant, LocalDateTime, OffsetDateTime, ZonedDateTime}

import org.scalatest.freespec.AnyFreeSpecLike

class FakerSpec extends AnyFreeSpecLike {
  val fakers: Seq[Faker] = Seq(Faker.en_US, Faker.en_CA)

  fakers.foreach(faker =>
    s"Faker - ${faker.locale}" - {
      "Lorem" - {
        "loremWord should return successfully" in {
          val res = faker.loremWord()
          assert(res.nonEmpty, res)
        }
        "loremWords with n should return successfully" in {
          val res = faker.loremWords(10)
          assert(res.split(" ").length === 10, res)
        }
        "loremWords should return successfully" in {
          val res = faker.loremWords()
          assert(res.split(" ").length > 1, res)
        }
        "loremSentence with n should return successfully" in {
          val res = faker.loremSentence(10)
          assert(res.split(" ").length === 10, res)
        }
        "loremSentence should return successfully" in {
          val res = faker.loremSentence()
          assert(res.split(" ").length > 1, res)
        }
        "loremParagraph with n should return successfully" in {
          val res = faker.loremParagraph(10)
          assert(res.split("\\.").length === 10, res)
        }
        "loremParagraph should return successfully" in {
          val res = faker.loremParagraph()
          assert(res.split("\\.").length > 1, res)
        }
        "loremParagraphs with n should return successfully" in {
          val res = faker.loremParagraphs(10)
          assert(res.split("\n").length === 10, res)
        }
        "loremParagraphs should return successfully" in {
          val res = faker.loremParagraphs()
          assert(res.split("\n").length > 1, res)
        }
      }
      "Name" - {
        "firstName should return successfully" in {
          val res = faker.firstName()
          assert(res.nonEmpty, res)
        }
        "fullName should return successfully" in {
          val res = faker.fullName()
          assert(res.split(" ").length > 1, res)
        }
        "fullNameWithMiddle should return successfully" in {
          val res = faker.fullNameWithMiddle()
          assert(res.split(" ").length > 1, res)
        }
        "lastName should return successfully" in {
          val res = faker.lastName()
          assert(res.nonEmpty, res)
        }
        "prefix should return successfully" in {
          val res = faker.prefix()
          assert(res.nonEmpty, res)
        }
        "suffix should return successfully" in {
          val res = faker.suffix()
          assert(res.nonEmpty, res)
        }
        "title should return successfully" in {
          val res = faker.title()
          assert(res.nonEmpty, res)
        }
        "userName should return successfully" in {
          val res = faker.userName()
          assert(res.split("\\.").length === 2, res)
        }
      }
      "Internet" - {
        "avatar should return successfully" in {
          val res = faker.avatar()
          assert(res.nonEmpty, res)
        }
        "domainName should return successfully" in {
          val res = faker.domainName()
          assert(res.nonEmpty, res)
        }
        "domainWord should return successfully" in {
          val res = faker.domainWord()
          assert(res.nonEmpty, res)
        }
        "domainSuffix should return successfully" in {
          val res = faker.domainSuffix()
          assert(res.nonEmpty, res)
        }
        "emailAddress should return successfully" in {
          val res = faker.emailAddress()
          assert(res.split("@").length === 2, res)
        }
        "image should return successfully" in {
          val res = faker.image()
          assert(res.nonEmpty, res)
        }
        "ipV4Address should return successfully" in {
          val res = faker.ipV4Address()
          assert(res.split("\\.").length === 4, res)
        }
        "ipV4Cidr should return successfully" in {
          val res = faker.ipV4Cidr()
          assert(res.nonEmpty, res)
        }
        "ipV6Address should return successfully" in {
          val res = faker.ipV6Address()
          assert(res.split(":").length === 8, res)
        }
        "ipV6Cidr should return successfully" in {
          val res = faker.ipV6Cidr()
          assert(res.nonEmpty, res)
        }
        "macAddress should return successfully" in {
          val res = faker.macAddress()
          assert(res.split(":").length === 6, res)
        }
        "password should return successfully" in {
          val res = faker.password()
          assert(res.nonEmpty, res)
        }
        "privateIpV4Address should return successfully" in {
          val res = faker.privateIpV4Address()
          assert(res.split("\\.").length === 4, res)
        }
        "publicIpV4Address should return successfully" in {
          val res = faker.publicIpV4Address()
          assert(res.split("\\.").length === 4, res)
        }
        "safeEmailAddress should return successfully" in {
          val res = faker.safeEmailAddress()
          assert(res.split("@").length === 2, res)
        }
        "slug should return successfully" in {
          val res = faker.slug()
          assert(res.nonEmpty, res)
        }
        "url should return successfully" in {
          val res = faker.url()
          assert(res.nonEmpty, res)
        }
        "userAgent should return successfully" in {
          val res = faker.userAgent()
          assert(res.nonEmpty, res)
        }
      }
      "Time" - {
        "currentEraInstant should return successfully" in {
          val res = faker.currentEraInstant()
          assert(Option(res).isDefined, res)
        }
        "currentEraLocalDateTime should return successfully" in {
          val res = faker.currentEraLocalDateTime()
          assert(Option(res).isDefined, res)
        }
        "currentEraOffsetDateTime should return successfully" in {
          val res = faker.currentEraOffsetDateTime()
          assert(Option(res).isDefined, res)
        }
        "currentEraZonedDateTime should return successfully" in {
          val res = faker.currentEraZonedDateTime()
          assert(Option(res).isDefined, res)
        }
        "futureInstant should return successfully" in {
          val compare = Instant.now()
          Thread.sleep(1L)
          val res = faker.futureInstant()
          assert(res.isAfter(compare), s"Res $res vs $compare")
        }
        "futureLocalDateTime should return successfully" in {
          val compare = LocalDateTime.now()
          Thread.sleep(1L)
          val res = faker.futureLocalDateTime()
          assert(res.isAfter(compare), s"Res $res vs $compare")
        }
        "futureOffsetDateTime should return successfully" in {
          val compare = OffsetDateTime.now()
          Thread.sleep(1L)
          val res = faker.futureOffsetDateTime()
          assert(res.isAfter(compare), s"Res $res vs $compare")
        }
        "futureZonedDateTime should return successfully" in {
          val compare = ZonedDateTime.now()
          Thread.sleep(1L)
          val res = faker.futureZonedDateTime()
          assert(res.isAfter(compare), s"Res $res vs $compare")
        }
        "nowInstant should return successfully" in {
          val compare = Instant.now()
          Thread.sleep(1L)
          val res = faker.nowInstant()
          assert(res.isAfter(compare), s"Res $res vs $compare")
        }
        "nowLocalDateTime should return successfully" in {
          val compare = LocalDateTime.now()
          Thread.sleep(1L)
          val res = faker.nowLocalDateTime()
          assert(res.isAfter(compare), s"Res $res vs $compare")
        }
        "nowOffsetDateTime should return successfully" in {
          val compare = OffsetDateTime.now()
          Thread.sleep(1L)
          val res = faker.nowOffsetDateTime()
          assert(res.isAfter(compare), s"Res $res vs $compare")
        }
        "nowZonedDateTime should return successfully" in {
          val compare = ZonedDateTime.now()
          Thread.sleep(1L)
          val res = faker.nowZonedDateTime()
          assert(res.isAfter(compare), s"Res $res vs $compare")
        }
        "pastInstant should return successfully" in {
          val res = faker.pastInstant()
          Thread.sleep(1L)
          val compare = Instant.now()
          assert(res.isBefore(compare), s"Res $res vs $compare")
        }
        "pastLocalDateTime should return successfully" in {
          val res = faker.pastLocalDateTime()
          Thread.sleep(1L)
          val compare = LocalDateTime.now()
          assert(res.isBefore(compare), s"Res $res vs $compare")
        }
        "pastOffsetDateTime should return successfully" in {
          val res = faker.pastOffsetDateTime()
          Thread.sleep(1L)
          val compare = OffsetDateTime.now()
          assert(res.isBefore(compare), s"Res $res vs $compare")
        }
        "pastZonedDateTime should return successfully" in {
          val res = faker.pastZonedDateTime()
          Thread.sleep(1L)
          val compare = ZonedDateTime.now()
          assert(res.isBefore(compare), s"Res $res vs $compare")
        }
        "randomInstant should return successfully" in {
          val res = faker.randomInstant()
          assert(Option(res).isDefined, res)
        }
        "randomLocalDateTime should return successfully" in {
          val res = faker.randomLocalDateTime()
          assert(Option(res).isDefined, res)
        }
        "randomOffsetDateTime should return successfully" in {
          val res = faker.randomOffsetDateTime()
          assert(Option(res).isDefined, res)
        }
        "randomZonedDateTime should return successfully" in {
          val res = faker.randomZonedDateTime()
          assert(Option(res).isDefined, res)
        }
      }
      "Address" - {
        "buildingNumber should return successfully" in {
          val res = faker.buildingNumber()
          assert(res.nonEmpty, res)
        }
        "city should return successfully" in {
          val res = faker.city()
          assert(res.nonEmpty, res)
        }
        "cityPrefix should return successfully" in {
          val res = faker.cityPrefix()
          assert(res.nonEmpty, res)
        }
        "citySuffix should return successfully" in {
          val res = faker.citySuffix()
          assert(res.nonEmpty, res)
        }
        "country should return successfully" in {
          val res = faker.country()
          assert(res.code.nonEmpty && res.name.nonEmpty, res)
        }
        "countryCode should return successfully" in {
          val res = faker.countryCode()
          assert(res.nonEmpty, res)
        }
        "countryName should return successfully" in {
          val res = faker.countryName()
          assert(res.nonEmpty, res)
        }
        "defaultCountry should return successfully" in {
          val res = faker.defaultCountry()
          assert(res.code.nonEmpty && res.name.nonEmpty, res)
        }
        "defaultCountryCode should return successfully" in {
          val res = faker.defaultCountryCode()
          assert(res.nonEmpty, res)
        }
        "defaultCountryName should return successfully" in {
          val res = faker.defaultCountryName()
          assert(res.nonEmpty, res)
        }
        "fullAddress should return successfully" in {
          val res = faker.fullAddress()
          assert(res.nonEmpty, res)
        }
        "latitude should return successfully" in {
          val res = faker.latitude()
          assert(res.nonEmpty, res)
        }
        "longitude should return successfully" in {
          val res = faker.longitude()
          assert(res.nonEmpty, res)
        }
        "secondaryAddress should return successfully" in {
          val res = faker.secondaryAddress()
          assert(res.nonEmpty, res)
        }
        "state should return successfully" in {
          val res = faker.state()
          assert(res.abbr.nonEmpty && res.name.nonEmpty, res)
        }
        "stateAbbr should return successfully" in {
          val res = faker.stateAbbr()
          assert(res.nonEmpty, res)
        }
        "stateZip should return successfully" in {
          val res = faker.stateZip()
          assert(res.nonEmpty, res)
        }
        "streetAddress should return successfully" in {
          val res = faker.streetAddress()
          assert(res.nonEmpty, res)
        }
        "streetName should return successfully" in {
          val res = faker.streetName()
          assert(res.nonEmpty, res)
        }
        "streetPrefix should return successfully" in {
          val res = faker.streetPrefix()
          assert(res.nonEmpty, res)
        }
        "streetSuffix should return successfully" in {
          val res = faker.streetSuffix()
          assert(res.nonEmpty, res)
        }
        "bs should return successfully" in {
          val res = faker.bs()
          assert(res.nonEmpty, res)
        }
        "buzzWord should return successfully" in {
          val res = faker.buzzWord()
          assert(res.nonEmpty, res)
        }
        "catchPhrase should return successfully" in {
          val res = faker.catchPhrase()
          assert(res.nonEmpty, res)
        }
        "companyDomainName should return successfully" in {
          val res = faker.companyDomainName()
          assert(res.nonEmpty, res)
        }
        "companyName should return successfully" in {
          val res = faker.companyName()
          assert(res.nonEmpty, res)
        }
        "companySuffix should return successfully" in {
          val res = faker.companySuffix()
          assert(res.nonEmpty, res)
        }
        "companyUrl should return successfully" in {
          val res = faker.companyUrl()
          assert(res.nonEmpty, res)
        }
        "industry should return successfully" in {
          val res = faker.industry()
          assert(res.nonEmpty, res)
        }
        "logo should return successfully" in {
          val res = faker.logo()
          assert(res.nonEmpty, res)
        }
        "profession should return successfully" in {
          val res = faker.profession()
          assert(res.nonEmpty, res)
        }
        "phoneNumber should return successfully" in {
          val res = faker.phoneNumber()
          assert(res.nonEmpty, res)
        }
        "cellPhoneNumber should return successfully" in {
          val res = faker.cellPhoneNumber()
          assert(res.nonEmpty, res)
        }
      }
    }
  )
}
