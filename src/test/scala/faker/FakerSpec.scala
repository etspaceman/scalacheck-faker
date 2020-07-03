package faker

import java.time._

import org.scalatest.freespec.AnyFreeSpecLike

class FakerSpec extends AnyFreeSpecLike {
  "Faker" - {
    "Lorem" - {
      "loremWord should return successfully" in {
        val res = Faker.default.loremWord()
        assert(res.nonEmpty, res)
      }
      "loremWords with n should return successfully" in {
        val res = Faker.default.loremWords(10)
        assert(res.split(" ").length === 10, res)
      }
      "loremWords should return successfully" in {
        val res = Faker.default.loremWords()
        assert(res.split(" ").length > 1, res)
      }
      "loremSentence with n should return successfully" in {
        val res = Faker.default.loremSentence(10)
        assert(res.split(" ").length === 10, res)
      }
      "loremSentence should return successfully" in {
        val res = Faker.default.loremSentence()
        assert(res.split(" ").length > 1, res)
      }
      "loremParagraph with n should return successfully" in {
        val res = Faker.default.loremParagraph(10)
        assert(res.split("\\.").length === 10, res)
      }
      "loremParagraph should return successfully" in {
        val res = Faker.default.loremParagraph()
        assert(res.split("\\.").length > 1, res)
      }
      "loremParagraphs with n should return successfully" in {
        val res = Faker.default.loremParagraphs(10)
        assert(res.split("\n").length === 10, res)
      }
      "loremParagraphs should return successfully" in {
        val res = Faker.default.loremParagraphs()
        assert(res.split("\n").length > 1, res)
      }
    }
    "Name" - {
      "firstName should return successfully" in {
        val res = Faker.default.firstName()
        assert(res.nonEmpty, res)
      }
      "fullName should return successfully" in {
        val res = Faker.default.fullName()
        assert(res.split(" ").length > 1, res)
      }
      "fullNameWithMiddle should return successfully" in {
        val res = Faker.default.fullNameWithMiddle()
        assert(res.split(" ").length > 1, res)
      }
      "lastName should return successfully" in {
        val res = Faker.default.lastName()
        assert(res.nonEmpty, res)
      }
      "prefix should return successfully" in {
        val res = Faker.default.prefix()
        assert(res.nonEmpty, res)
      }
      "suffix should return successfully" in {
        val res = Faker.default.suffix()
        assert(res.nonEmpty, res)
      }
      "title should return successfully" in {
        val res = Faker.default.title()
        assert(res.nonEmpty, res)
      }
      "userName should return successfully" in {
        val res = Faker.default.userName()
        assert(res.split("\\.").length === 2, res)
      }
    }
    "Internet" - {
      "avatar should return successfully" in {
        val res = Faker.default.avatar()
        assert(res.nonEmpty, res)
      }
      "domainName should return successfully" in {
        val res = Faker.default.domainName()
        assert(res.nonEmpty, res)
      }
      "domainWord should return successfully" in {
        val res = Faker.default.domainWord()
        assert(res.nonEmpty, res)
      }
      "domainSuffix should return successfully" in {
        val res = Faker.default.domainSuffix()
        assert(res.nonEmpty, res)
      }
      "emailAddress should return successfully" in {
        val res = Faker.default.emailAddress()
        assert(res.split("@").length === 2, res)
      }
      "image should return successfully" in {
        val res = Faker.default.image()
        assert(res.nonEmpty, res)
      }
      "ipV4Address should return successfully" in {
        val res = Faker.default.ipV4Address()
        assert(res.split("\\.").length === 4, res)
      }
      "ipV4Cidr should return successfully" in {
        val res = Faker.default.ipV4Cidr()
        assert(res.nonEmpty, res)
      }
      "ipV6Address should return successfully" in {
        val res = Faker.default.ipV6Address()
        assert(res.split(":").length === 8, res)
      }
      "ipV6Cidr should return successfully" in {
        val res = Faker.default.ipV6Cidr()
        assert(res.nonEmpty, res)
      }
      "macAddress should return successfully" in {
        val res = Faker.default.macAddress()
        assert(res.split(":").length === 6, res)
      }
      "password should return successfully" in {
        val res = Faker.default.password()
        assert(res.nonEmpty, res)
      }
      "privateIpV4Address should return successfully" in {
        val res = Faker.default.privateIpV4Address()
        assert(res.split("\\.").length === 4, res)
      }
      "publicIpV4Address should return successfully" in {
        val res = Faker.default.publicIpV4Address()
        assert(res.split("\\.").length === 4, res)
      }
      "safeEmailAddress should return successfully" in {
        val res = Faker.default.safeEmailAddress()
        assert(res.split("@").length === 2, res)
      }
      "slug should return successfully" in {
        val res = Faker.default.slug()
        assert(res.nonEmpty, res)
      }
      "url should return successfully" in {
        val res = Faker.default.url()
        assert(res.nonEmpty, res)
      }
      "userAgent should return successfully" in {
        val res = Faker.default.userAgent()
        assert(res.nonEmpty, res)
      }
    }
    "Time" - {
      "currentEraInstant should return successfully" in {
        val res = Faker.default.currentEraInstant()
        assert(Option(res).isDefined, res)
      }
      "currentEraLocalDateTime should return successfully" in {
        val res = Faker.default.currentEraLocalDateTime()
        assert(Option(res).isDefined, res)
      }
      "currentEraOffsetDateTime should return successfully" in {
        val res = Faker.default.currentEraOffsetDateTime()
        assert(Option(res).isDefined, res)
      }
      "currentEraZonedDateTime should return successfully" in {
        val res = Faker.default.currentEraZonedDateTime()
        assert(Option(res).isDefined, res)
      }
      "futureInstant should return successfully" in {
        val compare = Instant.now()
        Thread.sleep(1L)
        val res = Faker.default.futureInstant()
        assert(res.isAfter(compare), s"Res $res vs $compare")
      }
      "futureLocalDateTime should return successfully" in {
        val compare = LocalDateTime.now()
        Thread.sleep(1L)
        val res = Faker.default.futureLocalDateTime()
        assert(res.isAfter(compare), s"Res $res vs $compare")
      }
      "futureOffsetDateTime should return successfully" in {
        val compare = OffsetDateTime.now()
        Thread.sleep(1L)
        val res = Faker.default.futureOffsetDateTime()
        assert(res.isAfter(compare), s"Res $res vs $compare")
      }
      "futureZonedDateTime should return successfully" in {
        val compare = ZonedDateTime.now()
        Thread.sleep(1L)
        val res = Faker.default.futureZonedDateTime()
        assert(res.isAfter(compare), s"Res $res vs $compare")
      }
      "nowInstant should return successfully" in {
        val compare = Instant.now()
        Thread.sleep(1L)
        val res = Faker.default.nowInstant()
        assert(res.isAfter(compare), s"Res $res vs $compare")
      }
      "nowLocalDateTime should return successfully" in {
        val compare = LocalDateTime.now()
        Thread.sleep(1L)
        val res = Faker.default.nowLocalDateTime()
        assert(res.isAfter(compare), s"Res $res vs $compare")
      }
      "nowOffsetDateTime should return successfully" in {
        val compare = OffsetDateTime.now()
        Thread.sleep(1L)
        val res = Faker.default.nowOffsetDateTime()
        assert(res.isAfter(compare), s"Res $res vs $compare")
      }
      "nowZonedDateTime should return successfully" in {
        val compare = ZonedDateTime.now()
        Thread.sleep(1L)
        val res = Faker.default.nowZonedDateTime()
        assert(res.isAfter(compare), s"Res $res vs $compare")
      }
      "pastInstant should return successfully" in {
        val res = Faker.default.pastInstant()
        Thread.sleep(1L)
        val compare = Instant.now()
        assert(res.isBefore(compare), s"Res $res vs $compare")
      }
      "pastLocalDateTime should return successfully" in {
        val res = Faker.default.pastLocalDateTime()
        Thread.sleep(1L)
        val compare = LocalDateTime.now()
        assert(res.isBefore(compare), s"Res $res vs $compare")
      }
      "pastOffsetDateTime should return successfully" in {
        val res = Faker.default.pastOffsetDateTime()
        Thread.sleep(1L)
        val compare = OffsetDateTime.now()
        assert(res.isBefore(compare), s"Res $res vs $compare")
      }
      "pastZonedDateTime should return successfully" in {
        val res = Faker.default.pastZonedDateTime()
        Thread.sleep(1L)
        val compare = ZonedDateTime.now()
        assert(res.isBefore(compare), s"Res $res vs $compare")
      }
      "randomInstant should return successfully" in {
        val res = Faker.default.randomInstant()
        assert(Option(res).isDefined, res)
      }
      "randomLocalDateTime should return successfully" in {
        val res = Faker.default.randomLocalDateTime()
        assert(Option(res).isDefined, res)
      }
      "randomOffsetDateTime should return successfully" in {
        val res = Faker.default.randomOffsetDateTime()
        assert(Option(res).isDefined, res)
      }
      "randomZonedDateTime should return successfully" in {
        val res = Faker.default.randomZonedDateTime()
        assert(Option(res).isDefined, res)
      }
    }
  }
}
