package faker

import scala.reflect.ClassTag

import java.time.{Instant, LocalDateTime, OffsetDateTime, ZonedDateTime}
import java.util.Locale

import org.scalacheck.Arbitrary
import org.scalatest.freespec.AnyFreeSpecLike
import org.scalatestplus.scalacheck.Checkers

trait FakerSpec extends AnyFreeSpecLike with Checkers {
  def locale: Locale

  def testCanGen[A: Arbitrary](locale: Locale)(stringF: A => Option[String])(
      implicit CT: ClassTag[A]
  ): Unit = {
    @SuppressWarnings(Array("DisableSyntax.var"))
    var hasAlerted: Boolean = false
    def maybeAlert(str: String): Unit =
      if (str.contains("Not implemented for this locale") && !hasAlerted) {
        info("Value contains an unimplemented element!")
        hasAlerted = true
      }
    s"${CT.runtimeClass.getName} should generate faker data successfully for $locale" in {
      check((x: A) => {
        stringF(x).foreach(maybeAlert)
        true
      })
    }
  }

  s"Arbitrary tests for $locale" - {
    implicit val loader: ResourceLoader = new ResourceLoader(locale)
    testCanGen[address.BuildingNumber](locale)(x => Some(x.value))
    testCanGen[address.City](locale)(x => Some(x.value))
    testCanGen[address.CityPrefix](locale)(x => Some(x.value))
    testCanGen[address.CitySuffix](locale)(x => Some(x.value))
    testCanGen[address.Country](locale)(x =>
      Some(s"Code: '${x.code}', Name: '${x.name}''")
    )
    testCanGen[address.DefaultCountry](locale)(x =>
      Some(s"Code: '${x.code}', Name: '${x.name}''")
    )
    testCanGen[address.FullAddress](locale)(x => Some(x.value))
    testCanGen[address.Latitude](locale)(x => Some(x.value))
    testCanGen[address.Longitude](locale)(x => Some(x.value))
    testCanGen[address.PostalCode](locale)(x => Some(x.value))
    testCanGen[address.SecondaryAddress](locale)(x => Some(x.value))
    testCanGen[address.StateLike](locale)(x =>
      Some(s"Abbr: '${x.abbr}', Name: '${x.name}''")
    )
    testCanGen[address.StreetAddress](locale)(x => Some(x.value))
    testCanGen[address.StreetName](locale)(x => Some(x.value))
    testCanGen[address.StreetPrefix](locale)(x => Some(x.value))
    testCanGen[address.StreetSuffix](locale)(x => Some(x.value))
    testCanGen[animal.AnimalName](locale)(x => Some(x.value))
    testCanGen[company.BS](locale)(x => Some(x.value))
    testCanGen[company.BuzzWord](locale)(x => Some(x.value))
    testCanGen[company.CatchPhrase](locale)(x => Some(x.value))
    testCanGen[company.CompanyDomainName](locale)(x => Some(x.value))
    testCanGen[company.CompanyName](locale)(x => Some(x.value))
    testCanGen[company.CompanySuffix](locale)(x => Some(x.value))
    testCanGen[company.CompanyUrl](locale)(x => Some(x.value))
    testCanGen[company.Industry](locale)(x => Some(x.value))
    testCanGen[company.Logo](locale)(x => Some(x.value))
    testCanGen[company.Profession](locale)(x => Some(x.value))
    testCanGen[gender.GenderType](locale)(x => Some(x.value))
    testCanGen[gender.GenderBinaryType](locale)(x => Some(x.value))
    testCanGen[gender.GenderShortBinaryType](locale)(x => Some(x.value))
    testCanGen[internet.Avatar](locale)(x => Some(x.value))
    testCanGen[internet.DomainName](locale)(x => Some(x.value))
    testCanGen[internet.DomainSuffix](locale)(x => Some(x.value))
    testCanGen[internet.DomainWord](locale)(x => Some(x.value))
    testCanGen[internet.EmailAddress](locale)(x => Some(x.value))
    testCanGen[internet.Image](locale)(x => Some(x.value))
    testCanGen[internet.IpV4Address](locale)(x => Some(x.value))
    testCanGen[internet.IpV4Cidr](locale)(x => Some(x.value))
    testCanGen[internet.IpV6Address](locale)(x => Some(x.value))
    testCanGen[internet.IpV6Cidr](locale)(x => Some(x.value))
    testCanGen[internet.MacAddress](locale)(x => Some(x.value))
    testCanGen[internet.Password](locale)(x => Some(x.value))
    testCanGen[internet.PrivateIpV4Address](locale)(x => Some(x.value))
    testCanGen[internet.PublicIpV4Address](locale)(x => Some(x.value))
    testCanGen[internet.SafeEmailAddress](locale)(x => Some(x.value))
    testCanGen[internet.Slug](locale)(x => Some(x.value))
    testCanGen[internet.Url](locale)(x => Some(x.value))
    testCanGen[internet.UserAgent](locale)(x => Some(x.value))
    testCanGen[lorem.LoremWord](locale)(x => Some(x.value))
    testCanGen[lorem.LoremWords](locale)(x => Some(x.value))
    testCanGen[lorem.LoremSentence](locale)(x => Some(x.value))
    testCanGen[lorem.LoremParagraph](locale)(x => Some(x.value))
    testCanGen[lorem.LoremParagraphs](locale)(x => Some(x.value))
    testCanGen[lorem.LoremCharacters](locale)(x => Some(x.value))
    testCanGen[name.FirstName](locale)(x => Some(x.value))
    testCanGen[name.LastName](locale)(x => Some(x.value))
    testCanGen[name.FullName](locale)(x => Some(x.value))
    testCanGen[name.FullNameWithMiddle](locale)(x => Some(x.value))
    testCanGen[name.Prefix](locale)(x => Some(x.value))
    testCanGen[name.Suffix](locale)(x => Some(x.value))
    testCanGen[name.Title](locale)(x => Some(x.value))
    testCanGen[name.UserName](locale)(x => Some(x.value))
    testCanGen[phone.PhoneNumber](locale)(x => Some(x.value))
    testCanGen[phone.CellPhoneNumber](locale)(x => Some(x.value))
    testCanGen[pokemon.PokemonName](locale)(x => Some(x.value))
    testCanGen[pokemon.PokemonLocation](locale)(x => Some(x.value))
    testCanGen[pokemon.PokemonMove](locale)(x => Some(x.value))
    testCanGen[time.CurrentEraInstant](locale)(_ => None)
    testCanGen[time.CurrentEraLocalDateTime](locale)(_ => None)
    testCanGen[time.CurrentEraOffsetDateTime](locale)(_ => None)
    testCanGen[time.CurrentEraZonedDateTime](locale)(_ => None)
    testCanGen[time.FutureInstant](locale)(_ => None)
    testCanGen[time.FutureLocalDateTime](locale)(_ => None)
    testCanGen[time.FutureOffsetDateTime](locale)(_ => None)
    testCanGen[time.FutureZonedDateTime](locale)(_ => None)
    testCanGen[time.NowInstant](locale)(_ => None)
    testCanGen[time.NowLocalDateTime](locale)(_ => None)
    testCanGen[time.NowOffsetDateTime](locale)(_ => None)
    testCanGen[time.NowZonedDateTime](locale)(_ => None)
    testCanGen[time.PastInstant](locale)(_ => None)
    testCanGen[time.PastLocalDateTime](locale)(_ => None)
    testCanGen[time.PastOffsetDateTime](locale)(_ => None)
    testCanGen[time.PastZonedDateTime](locale)(_ => None)
    testCanGen[time.RandomInstant](locale)(_ => None)
    testCanGen[time.RandomLocalDateTime](locale)(_ => None)
    testCanGen[time.RandomOffsetDateTime](locale)(_ => None)
    testCanGen[time.RandomZonedDateTime](locale)(_ => None)
    testCanGen[zelda.ZeldaCharacter](locale)(x => Some(x.value))
    testCanGen[zelda.ZeldaGame](locale)(x => Some(x.value))
    testCanGen[zelda.ZeldaItem](locale)(x => Some(x.value))
    testCanGen[zelda.ZeldaLocation](locale)(x => Some(x.value))
    testCanGen[slack.emoji.SlackEmoji](locale)(x => Some(x.value))
    testCanGen[slack.emoji.SlackEmojiActivity](locale)(x => Some(x.value))
    testCanGen[slack.emoji.SlackEmojiCelebration](locale)(x => Some(x.value))
    testCanGen[slack.emoji.SlackEmojiCustom](locale)(x => Some(x.value))
    testCanGen[slack.emoji.SlackEmojiFood](locale)(x => Some(x.value))
    testCanGen[slack.emoji.SlackEmojiNature](locale)(x => Some(x.value))
    testCanGen[slack.emoji.SlackEmojiObject](locale)(x => Some(x.value))
    testCanGen[slack.emoji.SlackEmojiPerson](locale)(x => Some(x.value))
    testCanGen[slack.emoji.SlackEmojiTravel](locale)(x => Some(x.value))
    testCanGen[weather.WeatherDescription](locale)(x => Some(x.value))
    testCanGen[weather.TemperatureCelsius](locale)(x => Some(x.value))
    testCanGen[weather.TemperatureFahrenheit](locale)(x => Some(x.value))
    testCanGen[music.MusicAlbum](locale)(x => Some(x.value))
    testCanGen[music.MusicalGenre](locale)(x => Some(x.value))
    testCanGen[music.MusicalInstrument](locale)(x => Some(x.value))
    testCanGen[music.MusicBand](locale)(x => Some(x.value))
    testCanGen[dragonball.DragonBallCharacter](locale)(x => Some(x.value))
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
    "Pokemon" - {
      "pokemonName should return successfully" in {
        val res = faker.pokemonName()
        assert(res.nonEmpty, res)
      }
      "pokemonLocation should return successfully" in {
        val res = faker.pokemonLocation()
        assert(res.nonEmpty, res)
      }
      "pokemonMove should return successfully" in {
        val res = faker.pokemonMove()
        assert(res.nonEmpty, res)
      }
    }
    "Animal" - {
      "animalName should return successfully" in {
        val res = faker.animalName()
        assert(res.nonEmpty, res)
      }
    }
    "Gender" - {
      "genderType should return successfully" in {
        val res = faker.genderType()
        assert(res.nonEmpty, res)
      }
      "genderBinaryType should return successfully" in {
        val res = faker.genderBinaryType()
        assert(res.nonEmpty, res)
      }
      "genderShortBinaryType should return successfully" in {
        val res = faker.genderShortBinaryType()
        assert(res.nonEmpty, res)
      }
    }
    "Zelda" - {
      "zeldaCharacter should return successfully" in {
        val res = faker.zeldaCharacter()
        assert(res.nonEmpty, res)
      }
      "zeldaGame should return successfully" in {
        val res = faker.zeldaGame()
        assert(res.nonEmpty, res)
      }
      "zeldaItem should return successfully" in {
        val res = faker.zeldaItem()
        assert(res.nonEmpty, res)
      }
      "zeldaLocation should return successfully" in {
        val res = faker.zeldaLocation()
        assert(res.nonEmpty, res)
      }
    }
    "Slack Emojis" - {
      "slackEmoji should return successfully" in {
        val res = faker.slackEmoji()
        assert(res.nonEmpty, res)
      }
      "slackEmojiActivity should return successfully" in {
        val res = faker.slackEmojiActivity()
        assert(res.nonEmpty, res)
      }
      "slackEmojiCelebration should return successfully" in {
        val res = faker.slackEmojiCelebration()
        assert(res.nonEmpty, res)
      }
      "slackEmojiCustom should return successfully" in {
        val res = faker.slackEmojiCustom()
        assert(res.nonEmpty, res)
      }
      "slackEmojiFood should return successfully" in {
        val res = faker.slackEmojiFood()
        assert(res.nonEmpty, res)
      }
      "slackEmojiNature should return successfully" in {
        val res = faker.slackEmojiNature()
        assert(res.nonEmpty, res)
      }
      "slackEmojiObject should return successfully" in {
        val res = faker.slackEmojiObject()
        assert(res.nonEmpty, res)
      }
      "slackEmojiPerson should return successfully" in {
        val res = faker.slackEmojiPerson()
        assert(res.nonEmpty, res)
      }
      "slackEmojiTravel should return successfully" in {
        val res = faker.slackEmojiTravel()
        assert(res.nonEmpty, res)
      }
    }
    "Weather" - {
      "weatherDescription should return successfully" in {
        val res = faker.weatherDescription()
        assert(res.nonEmpty, res)
      }
      "temperatureCelsius should return successfully" in {
        val res = faker.temperatureCelsius()
        assert(res.nonEmpty, res)
      }
      "temperatureFahrenheit should return successfully" in {
        val res = faker.temperatureFahrenheit()
        assert(res.nonEmpty, res)
      }
    }
    "Music" - {
      "musicAlbum should return successfully" in {
        val res = faker.musicAlbum()
        assert(res.nonEmpty, res)
      }
      "musicalGenre should return successfully" in {
        val res = faker.musicalGenre()
        assert(res.nonEmpty, res)
      }
      "musicalInstrument should return successfully" in {
        val res = faker.musicalInstrument()
        assert(res.nonEmpty, res)
      }
      "musicBand should return successfully" in {
        val res = faker.musicBand()
        assert(res.nonEmpty, res)
      }
    }
    "Dragonball" - {
      "dragonballCharacter should return successfully" in {
        val res = faker.dragonballCharacter()
        assert(res.nonEmpty, res)
      }
    }
  }
}
