package faker

import scala.reflect.ClassTag

import java.time.{Instant, LocalDateTime, OffsetDateTime, ZonedDateTime}
import java.util.Locale

import org.scalacheck.Arbitrary
import org.scalatest.Assertion
import org.scalatest.freespec.AnyFreeSpecLike
import org.scalatestplus.scalacheck.Checkers

trait FakerSpec extends AnyFreeSpecLike with Checkers {
  def locale: Locale

  @SuppressWarnings(Array("DisableSyntax.defaultArgs"))
  def testCanGen[A: Arbitrary](locale: Locale, shouldRun: Boolean = true)(
      implicit CT: ClassTag[A]
  ): Unit = {
    val desc =
      s"${CT.runtimeClass.getName} should generate faker data successfully for $locale"
    if (shouldRun) desc in { check((_: A) => true) }
    else desc ignore {}
  }

  val countriesWithoutStates = Seq("GB")

  def ignorableTest[A](desc: String, faker: Faker)(
      shouldRun: Faker => Boolean
  )(fakerF: Faker => A)(assertion: A => Assertion): Unit =
    if (shouldRun(faker)) {
      desc in {
        val res = fakerF(faker)
        assertion(res)
      }
    } else { desc ignore {} }

  s"Arbitrary tests for $locale" - {
    implicit val loader: ResourceLoader = new ResourceLoader(locale)
    testCanGen[address.BuildingNumber](locale)
    testCanGen[address.City](locale)
    testCanGen[address.CityPrefix](locale)
    testCanGen[address.CitySuffix](locale)
    testCanGen[address.Country](locale)
    testCanGen[address.DefaultCountry](locale)
    testCanGen[address.FullAddress](locale)
    testCanGen[address.Latitude](locale)
    testCanGen[address.Longitude](locale)
    testCanGen[address.PostalCode](locale)
    testCanGen[address.SecondaryAddress](locale)
    testCanGen[address.StateLike](
      locale,
      !countriesWithoutStates.contains(locale.getCountry)
    )
    testCanGen[address.StreetAddress](locale)
    testCanGen[address.StreetName](locale)
    testCanGen[address.StreetPrefix](locale)
    testCanGen[address.StreetSuffix](locale)
    testCanGen[company.BS](locale)
    testCanGen[company.BuzzWord](locale)
    testCanGen[company.CatchPhrase](locale)
    testCanGen[company.CompanyDomainName](locale)
    testCanGen[company.CompanyName](locale)
    testCanGen[company.CompanySuffix](locale)
    testCanGen[company.CompanyUrl](locale)
    testCanGen[company.Industry](locale)
    testCanGen[company.Logo](locale)
    testCanGen[company.Profession](locale)
    testCanGen[internet.Avatar](locale)
    testCanGen[internet.DomainName](locale)
    testCanGen[internet.DomainSuffix](locale)
    testCanGen[internet.DomainWord](locale)
    testCanGen[internet.EmailAddress](locale)
    testCanGen[internet.Image](locale)
    testCanGen[internet.IpV4Address](locale)
    testCanGen[internet.IpV4Cidr](locale)
    testCanGen[internet.IpV6Address](locale)
    testCanGen[internet.IpV6Cidr](locale)
    testCanGen[internet.MacAddress](locale)
    testCanGen[internet.Password](locale)
    testCanGen[internet.PrivateIpV4Address](locale)
    testCanGen[internet.PublicIpV4Address](locale)
    testCanGen[internet.SafeEmailAddress](locale)
    testCanGen[internet.Slug](locale)
    testCanGen[internet.Url](locale)
    testCanGen[internet.UserAgent](locale)
    testCanGen[lorem.LoremWord](locale)
    testCanGen[lorem.LoremWords](locale)
    testCanGen[lorem.LoremSentence](locale)
    testCanGen[lorem.LoremParagraph](locale)
    testCanGen[lorem.LoremParagraphs](locale)
    testCanGen[lorem.LoremCharacters](locale)
    testCanGen[name.FirstName](locale)
    testCanGen[name.LastName](locale)
    testCanGen[name.FullName](locale)
    testCanGen[name.FullNameWithMiddle](locale)
    testCanGen[name.Prefix](locale)
    testCanGen[name.Suffix](locale)
    testCanGen[name.Title](locale)
    testCanGen[name.UserName](locale)
    testCanGen[phone.PhoneNumber](locale)
    testCanGen[phone.CellPhoneNumber](locale)
    testCanGen[time.CurrentEraInstant](locale)
    testCanGen[time.CurrentEraLocalDateTime](locale)
    testCanGen[time.CurrentEraOffsetDateTime](locale)
    testCanGen[time.CurrentEraZonedDateTime](locale)
    testCanGen[time.FutureInstant](locale)
    testCanGen[time.FutureLocalDateTime](locale)
    testCanGen[time.FutureOffsetDateTime](locale)
    testCanGen[time.FutureZonedDateTime](locale)
    testCanGen[time.NowInstant](locale)
    testCanGen[time.NowLocalDateTime](locale)
    testCanGen[time.NowOffsetDateTime](locale)
    testCanGen[time.NowZonedDateTime](locale)
    testCanGen[time.PastInstant](locale)
    testCanGen[time.PastLocalDateTime](locale)
    testCanGen[time.PastOffsetDateTime](locale)
    testCanGen[time.PastZonedDateTime](locale)
    testCanGen[time.RandomInstant](locale)
    testCanGen[time.RandomLocalDateTime](locale)
    testCanGen[time.RandomOffsetDateTime](locale)
    testCanGen[time.RandomZonedDateTime](locale)
  }
  s"Faker tests for $locale" - {
    val faker: Faker = new Faker(locale)
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
      ignorableTest("state should return successfully", faker)(x =>
        !countriesWithoutStates.contains(x.locale.getCountry)
      )(_.state())(res => assert(res.abbr.nonEmpty && res.name.nonEmpty, res))

      ignorableTest("stateAbbr should return successfully", faker)(x =>
        !countriesWithoutStates.contains(x.locale.getCountry)
      )(_.stateAbbr())(res => assert(res.nonEmpty, res))

      ignorableTest("stateZip should return successfully", faker)(x =>
        !countriesWithoutStates.contains(x.locale.getCountry)
      )(_.stateZip())(res => assert(res.nonEmpty, res))

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
}
