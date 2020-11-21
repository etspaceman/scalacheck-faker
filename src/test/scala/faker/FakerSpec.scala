package faker

import java.time.{Instant, LocalDateTime, OffsetDateTime, ZonedDateTime}
import java.util.Locale

import org.scalacheck.Arbitrary
import org.scalatest.freespec.AnyFreeSpecLike
import org.scalatestplus.scalacheck.Checkers

trait FakerSpec extends AnyFreeSpecLike with Checkers {
  def locale: Locale

  def testCanGen[A: Arbitrary](locale: Locale, desc: String)(
      stringF: A => Option[String]
  ): Unit = {
    @SuppressWarnings(Array("DisableSyntax.var"))
    var hasAlerted: Boolean = false
    def maybeAlert(str: String): Unit =
      if (str.contains("Not implemented for this locale") && !hasAlerted) {
        info("Value contains an unimplemented element!")
        hasAlerted = true
      }
    s"$desc should generate faker data successfully for $locale" in {
      check((x: A) => {
        stringF(x).foreach(maybeAlert)
        true
      })
    }
  }

  s"Arbitrary tests for $locale" - {
    implicit val loader: ResourceLoader = new ResourceLoader(locale)
    testCanGen[address.BuildingNumber](locale, "address.BuildingNumber")(x =>
      Some(x.value)
    )
    testCanGen[address.City](locale, "address.City")(x => Some(x.value))
    testCanGen[address.CityPrefix](locale, "address.CityPrefix")(x =>
      Some(x.value)
    )
    testCanGen[address.CitySuffix](locale, "address.CitySuffix")(x =>
      Some(x.value)
    )
    testCanGen[address.Country](locale, "address.Country")(x =>
      Some(s"Code: '${x.code}', Name: '${x.name}''")
    )
    testCanGen[address.DefaultCountry](locale, "address.DefaultCountry")(x =>
      Some(s"Code: '${x.code}', Name: '${x.name}''")
    )
    testCanGen[address.FullAddress](locale, "address.FullAddress")(x =>
      Some(x.value)
    )
    testCanGen[address.Latitude](locale, "address.Latitude")(x => Some(x.value))
    testCanGen[address.Longitude](locale, "address.Longitude")(x =>
      Some(x.value)
    )
    testCanGen[address.PostalCode](locale, "address.PostalCode")(x =>
      Some(x.value)
    )
    testCanGen[address.SecondaryAddress](locale, "address.SecondaryAddress")(
      x => Some(x.value)
    )
    testCanGen[states.StateLike](locale, "states.StateLike")(x =>
      Some(s"Abbr: '${x.abbr}', Name: '${x.name}''")
    )
    testCanGen[address.StreetAddress](locale, "address.StreetAddress")(x =>
      Some(x.value)
    )
    testCanGen[address.StreetName](locale, "address.StreetName")(x =>
      Some(x.value)
    )
    testCanGen[address.StreetPrefix](locale, "address.StreetPrefix")(x =>
      Some(x.value)
    )
    testCanGen[address.StreetSuffix](locale, "address.StreetSuffix")(x =>
      Some(x.value)
    )
    testCanGen[ancient.God](locale, "ancient.God")(x => Some(x.value))
    testCanGen[ancient.Primordial](locale, "ancient.Primordial")(x =>
      Some(x.value)
    )
    testCanGen[ancient.Titan](locale, "ancient.Titan")(x => Some(x.value))
    testCanGen[ancient.Hero](locale, "ancient.Hero")(x => Some(x.value))
    testCanGen[animal.AnimalName](locale, "animal.AnimalName")(x =>
      Some(x.value)
    )
    testCanGen[company.BS](locale, "company.BS")(x => Some(x.value))
    testCanGen[company.BuzzWord](locale, "company.BuzzWord")(x => Some(x.value))
    testCanGen[company.CatchPhrase](locale, "company.CatchPhrase")(x =>
      Some(x.value)
    )
    testCanGen[company.CompanyDomainName](locale, "company.CompanyDomainName")(
      x => Some(x.value)
    )
    testCanGen[company.CompanyName](locale, "company.CompanyName")(x =>
      Some(x.value)
    )
    testCanGen[company.CompanySuffix](locale, "company.CompanySuffix")(x =>
      Some(x.value)
    )
    testCanGen[company.CompanyUrl](locale, "company.CompanyUrl")(x =>
      Some(x.value)
    )
    testCanGen[company.Industry](locale, "company.Industry")(x => Some(x.value))
    testCanGen[company.Logo](locale, "company.Logo")(x => Some(x.value))
    testCanGen[company.Profession](locale, "company.Profession")(x =>
      Some(x.value)
    )
    testCanGen[gender.GenderType](locale, "gender.GenderType")(x =>
      Some(x.value)
    )
    testCanGen[gender.GenderBinaryType](locale, "gender.GenderBinaryType")(x =>
      Some(x.value)
    )
    testCanGen[gender.GenderShortBinaryType](
      locale,
      "gender.GenderShortBinaryType"
    )(x => Some(x.value))
    testCanGen[internet.Avatar](locale, "internet.Avatar")(x => Some(x.value))
    testCanGen[internet.DomainName](locale, "internet.DomainName")(x =>
      Some(x.value)
    )
    testCanGen[internet.DomainSuffix](locale, "internet.DomainSuffix")(x =>
      Some(x.value)
    )
    testCanGen[internet.DomainWord](locale, "internet.DomainWord")(x =>
      Some(x.value)
    )
    testCanGen[internet.EmailAddress](locale, "internet.EmailAddress")(x =>
      Some(x.value)
    )
    testCanGen[internet.Image](locale, "internet.Image")(x => Some(x.value))
    testCanGen[internet.IpV4Address](locale, "internet.IpV4Address")(x =>
      Some(x.value)
    )
    testCanGen[internet.IpV4Cidr](locale, "internet.IpV4Cidr")(x =>
      Some(x.value)
    )
    testCanGen[internet.IpV6Address](locale, "internet.IpV6Address")(x =>
      Some(x.value)
    )
    testCanGen[internet.IpV6Cidr](locale, "internet.IpV6Cidr")(x =>
      Some(x.value)
    )
    testCanGen[internet.MacAddress](locale, "internet.MacAddress")(x =>
      Some(x.value)
    )
    testCanGen[internet.Password](locale, "internet.Password")(x =>
      Some(x.value)
    )
    testCanGen[internet.PrivateIpV4Address](
      locale,
      "internet.PrivateIpV4Address"
    )(x => Some(x.value))
    testCanGen[internet.PublicIpV4Address](
      locale,
      "internet.PublicIpV4Address"
    )(x => Some(x.value))
    testCanGen[internet.SafeEmailAddress](locale, "internet.SafeEmailAddress")(
      x => Some(x.value)
    )
    testCanGen[internet.Slug](locale, "internet.Slug")(x => Some(x.value))
    testCanGen[internet.Url](locale, "internet.Url")(x => Some(x.value))
    testCanGen[internet.UserAgent](locale, "internet.UserAgent")(x =>
      Some(x.value)
    )
    testCanGen[lorem.LoremWord](locale, "lorem.LoremWord")(x => Some(x.value))
    testCanGen[lorem.LoremWords](locale, "lorem.LoremWords")(x => Some(x.value))
    testCanGen[lorem.LoremSentence](locale, "lorem.LoremSentence")(x =>
      Some(x.value)
    )
    testCanGen[lorem.LoremParagraph](locale, "lorem.LoremParagraph")(x =>
      Some(x.value)
    )
    testCanGen[lorem.LoremParagraphs](locale, "lorem.LoremParagraphs")(x =>
      Some(x.value)
    )
    testCanGen[lorem.LoremCharacters](locale, "lorem.LoremCharacters")(x =>
      Some(x.value)
    )
    testCanGen[name.FirstName](locale, "name.FirstName")(x => Some(x.value))
    testCanGen[name.LastName](locale, "name.LastName")(x => Some(x.value))
    testCanGen[name.FullName](locale, "name.FullName")(x => Some(x.value))
    testCanGen[name.FullNameWithMiddle](locale, "name.FullNameWithMiddle")(x =>
      Some(x.value)
    )
    testCanGen[name.Prefix](locale, "name.Prefix")(x => Some(x.value))
    testCanGen[name.Suffix](locale, "name.Suffix")(x => Some(x.value))
    testCanGen[name.Title](locale, "name.Title")(x => Some(x.value))
    testCanGen[name.UserName](locale, "name.UserName")(x => Some(x.value))
    testCanGen[phone.PhoneNumber](locale, "phone.PhoneNumber")(x =>
      Some(x.value)
    )
    testCanGen[phone.CellPhoneNumber](locale, "phone.CellPhoneNumber")(x =>
      Some(x.value)
    )
    testCanGen[pokemon.PokemonName](locale, "pokemon.PokemonName")(x =>
      Some(x.value)
    )
    testCanGen[pokemon.PokemonLocation](locale, "pokemon.PokemonLocation")(x =>
      Some(x.value)
    )
    testCanGen[pokemon.PokemonMove](locale, "pokemon.PokemonMove")(x =>
      Some(x.value)
    )
    testCanGen[time.CurrentEraInstant](locale, "time.CurrentEraInstant")(_ =>
      None
    )
    testCanGen[time.CurrentEraLocalDateTime](
      locale,
      "time.CurrentEraLocalDateTime"
    )(_ => None)
    testCanGen[time.CurrentEraOffsetDateTime](
      locale,
      "time.CurrentEraOffsetDateTime"
    )(_ => None)
    testCanGen[time.CurrentEraZonedDateTime](
      locale,
      "time.CurrentEraZonedDateTime"
    )(_ => None)
    testCanGen[time.FutureInstant](locale, "time.FutureInstant")(_ => None)
    testCanGen[time.FutureLocalDateTime](locale, "time.FutureLocalDateTime")(
      _ => None
    )
    testCanGen[time.FutureOffsetDateTime](locale, "time.FutureOffsetDateTime")(
      _ => None
    )
    testCanGen[time.FutureZonedDateTime](locale, "time.FutureZonedDateTime")(
      _ => None
    )
    testCanGen[time.NowInstant](locale, "time.NowInstant")(_ => None)
    testCanGen[time.NowLocalDateTime](locale, "time.NowLocalDateTime")(_ =>
      None
    )
    testCanGen[time.NowOffsetDateTime](locale, "time.NowOffsetDateTime")(_ =>
      None
    )
    testCanGen[time.NowZonedDateTime](locale, "time.NowZonedDateTime")(_ =>
      None
    )
    testCanGen[time.PastInstant](locale, "time.PastInstant")(_ => None)
    testCanGen[time.PastLocalDateTime](locale, "time.PastLocalDateTime")(_ =>
      None
    )
    testCanGen[time.PastOffsetDateTime](locale, "time.PastOffsetDateTime")(_ =>
      None
    )
    testCanGen[time.PastZonedDateTime](locale, "time.PastZonedDateTime")(_ =>
      None
    )
    testCanGen[time.RandomInstant](locale, "time.RandomInstant")(_ => None)
    testCanGen[time.RandomLocalDateTime](locale, "time.RandomLocalDateTime")(
      _ => None
    )
    testCanGen[time.RandomOffsetDateTime](locale, "time.RandomOffsetDateTime")(
      _ => None
    )
    testCanGen[time.RandomZonedDateTime](locale, "time.RandomZonedDateTime")(
      _ => None
    )
    testCanGen[zelda.ZeldaCharacter](locale, "zelda.ZeldaCharacter")(x =>
      Some(x.value)
    )
    testCanGen[zelda.ZeldaGame](locale, "zelda.ZeldaGame")(x => Some(x.value))
    testCanGen[zelda.ZeldaItem](locale, "zelda.ZeldaItem")(x => Some(x.value))
    testCanGen[zelda.ZeldaLocation](locale, "zelda.ZeldaLocation")(x =>
      Some(x.value)
    )
    testCanGen[slack.emoji.SlackEmoji](locale, "slack.emoji.SlackEmoji")(x =>
      Some(x.value)
    )
    testCanGen[slack.emoji.SlackEmojiActivity](
      locale,
      "slack.emoji.SlackEmojiActivity"
    )(x => Some(x.value))
    testCanGen[slack.emoji.SlackEmojiCelebration](
      locale,
      "slack.emoji.SlackEmojiCelebration"
    )(x => Some(x.value))
    testCanGen[slack.emoji.SlackEmojiCustom](
      locale,
      "slack.emoji.SlackEmojiCustom"
    )(x => Some(x.value))
    testCanGen[slack.emoji.SlackEmojiFood](
      locale,
      "slack.emoji.SlackEmojiFood"
    )(x => Some(x.value))
    testCanGen[slack.emoji.SlackEmojiNature](
      locale,
      "slack.emoji.SlackEmojiNature"
    )(x => Some(x.value))
    testCanGen[slack.emoji.SlackEmojiObject](
      locale,
      "slack.emoji.SlackEmojiObject"
    )(x => Some(x.value))
    testCanGen[slack.emoji.SlackEmojiPerson](
      locale,
      "slack.emoji.SlackEmojiPerson"
    )(x => Some(x.value))
    testCanGen[slack.emoji.SlackEmojiTravel](
      locale,
      "slack.emoji.SlackEmojiTravel"
    )(x => Some(x.value))
    testCanGen[weather.WeatherDescription](
      locale,
      "weather.WeatherDescription"
    )(x => Some(x.value))
    testCanGen[weather.TemperatureCelsius](
      locale,
      "weather.TemperatureCelsius"
    )(x => Some(x.value))
    testCanGen[weather.TemperatureFahrenheit](
      locale,
      "weather.TemperatureFahrenheit"
    )(x => Some(x.value))
    testCanGen[music.MusicAlbum](locale, "music.MusicAlbum")(x => Some(x.value))
    testCanGen[music.MusicalGenre](locale, "music.MusicalGenre")(x =>
      Some(x.value)
    )
    testCanGen[music.MusicalInstrument](locale, "music.MusicalInstrument")(x =>
      Some(x.value)
    )
    testCanGen[music.MusicBand](locale, "music.MusicBand")(x => Some(x.value))
    testCanGen[dragonball.DragonBallCharacter](
      locale,
      "dragonball.DragonBallCharacter"
    )(x => Some(x.value))
    testCanGen[job.JobField](locale, "job.JobField")(x => Some(x.value))
    testCanGen[job.JobSeniority](locale, "job.JobSeniority")(x => Some(x.value))
    testCanGen[job.JobPosition](locale, "job.JobPosition")(x => Some(x.value))
    testCanGen[job.JobKeySkill](locale, "job.JobKeySkill")(x => Some(x.value))
    testCanGen[job.JobEmploymentType](locale, "job.JobEmploymentType")(x =>
      Some(x.value)
    )
    testCanGen[job.JobEducationLevel](locale, "job.JobEducationLevel")(x =>
      Some(x.value)
    )
    testCanGen[job.JobTitle](locale, "job.JobTitle")(x => Some(x.value))
    testCanGen[currency.CurrencyCode](locale, "currency.CurrencyCode")(x =>
      Some(x.value)
    )
    testCanGen[currency.CurrencyName](locale, "currency.CurrencyName")(x =>
      Some(x.value)
    )
    testCanGen[currency.CurrencySymbol](locale, "currency.CurrencySymbol")(x =>
      Some(x.value)
    )
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
    "Job" - {
      "jobField should return successfully" in {
        val res = faker.jobField()
        assert(res.nonEmpty, res)
      }
      "jobSeniority should return successfully" in {
        val res = faker.jobSeniority()
        assert(res.nonEmpty, res)
      }
      "jobPosition should return successfully" in {
        val res = faker.jobPosition()
        assert(res.nonEmpty, res)
      }
      "jobKeySkill should return successfully" in {
        val res = faker.jobKeySkill()
        assert(res.nonEmpty, res)
      }
      "jobEmploymentType should return successfully" in {
        val res = faker.jobEmploymentType()
        assert(res.nonEmpty, res)
      }
      "jobEducationLevel should return successfully" in {
        val res = faker.jobEducationLevel()
        assert(res.nonEmpty, res)
      }
      "jobTitle should return successfully" in {
        val res = faker.jobTitle()
        assert(res.nonEmpty, res)
      }
    }
    "Currency" - {
      "currencyCode should return successfully" in {
        val res = faker.currencyCode()
        assert(res.nonEmpty, res)
      }
      "currencyName should return successfully" in {
        val res = faker.currencyName()
        assert(res.nonEmpty, res)
      }
      "currencySymbol should return successfully" in {
        val res = faker.currencySymbol()
        assert(res.nonEmpty, res)
      }
    }
  }
}
