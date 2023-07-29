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

import java.time.{Instant, LocalDateTime, OffsetDateTime, ZonedDateTime}
import java.util.Locale

import org.scalacheck.Arbitrary
import org.scalacheck.Prop._

trait FakerSpec extends munit.ScalaCheckSuite {
  override val scalaCheckTestParameters =
    super.scalaCheckTestParameters.withMinSuccessfulTests(10).withWorkers(5)

  def locale: Locale

  def testCanGen[A: Arbitrary](locale: Locale, desc: String)(
      stringF: A => Option[String]
  ): Unit = {
    @SuppressWarnings(Array("DisableSyntax.var"))
    var hasAlerted: Boolean = false
    def maybeAlert(str: String): Unit =
      if (str.contains("Not implemented for this locale") && !hasAlerted) {
        println("Value contains an unimplemented element!")
        hasAlerted = true
      }
    property(s"$desc should generate faker data successfully for $locale") {
      forAll { (x: A) =>
        stringF(x).foreach(maybeAlert)
        true
      }
    }
  }

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
  testCanGen[address.Longitude](locale, "address.Longitude")(x => Some(x.value))
  testCanGen[address.PostalCode](locale, "address.PostalCode")(x =>
    Some(x.value)
  )
  testCanGen[address.SecondaryAddress](locale, "address.SecondaryAddress")(x =>
    Some(x.value)
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
  testCanGen[animal.AnimalName](locale, "animal.AnimalName")(x => Some(x.value))
  testCanGen[aviation.Aircraft](locale, "aviation.Aircraft")(x => Some(x.value))
  testCanGen[aviation.Airport](locale, "aviation.Airport")(x => Some(x.value))
  testCanGen[aviation.Metar](locale, "aviation.Metar")(x => Some(x.value))
  testCanGen[cat.Name](locale, "cat.Name")(x => Some(x.value))
  testCanGen[cat.Breed](locale, "cat.Breed")(x => Some(x.value))
  testCanGen[cat.Registry](locale, "cat.Registry")(x => Some(x.value))
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
  testCanGen[gender.GenderType](locale, "gender.GenderType")(x => Some(x.value))
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
  testCanGen[internet.IpV4Cidr](locale, "internet.IpV4Cidr")(x => Some(x.value))
  testCanGen[internet.IpV6Address](locale, "internet.IpV6Address")(x =>
    Some(x.value)
  )
  testCanGen[internet.IpV6Cidr](locale, "internet.IpV6Cidr")(x => Some(x.value))
  testCanGen[internet.MacAddress](locale, "internet.MacAddress")(x =>
    Some(x.value)
  )
  testCanGen[internet.Password](locale, "internet.Password")(x => Some(x.value))
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
  testCanGen[phone.PhoneNumber](locale, "phone.PhoneNumber")(x => Some(x.value))
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
  testCanGen[time.FutureLocalDateTime](locale, "time.FutureLocalDateTime")(_ =>
    None
  )
  testCanGen[time.FutureOffsetDateTime](locale, "time.FutureOffsetDateTime")(
    _ => None
  )
  testCanGen[time.FutureZonedDateTime](locale, "time.FutureZonedDateTime")(_ =>
    None
  )
  testCanGen[time.NowInstant](locale, "time.NowInstant")(_ => None)
  testCanGen[time.NowLocalDateTime](locale, "time.NowLocalDateTime")(_ => None)
  testCanGen[time.NowOffsetDateTime](locale, "time.NowOffsetDateTime")(_ =>
    None
  )
  testCanGen[time.NowZonedDateTime](locale, "time.NowZonedDateTime")(_ => None)
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
  testCanGen[time.RandomLocalDateTime](locale, "time.RandomLocalDateTime")(_ =>
    None
  )
  testCanGen[time.RandomOffsetDateTime](locale, "time.RandomOffsetDateTime")(
    _ => None
  )
  testCanGen[time.RandomZonedDateTime](locale, "time.RandomZonedDateTime")(_ =>
    None
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
  testCanGen[yoda.YodaQuote](locale, "yoda.YodaQuote")(x => Some(x.value))
  testCanGen[programmingLanguage.ProgrammingLanguageName](
    locale,
    "programmingLanguage.ProgrammingLanguageName"
  )(x => Some(x.value))
  testCanGen[programmingLanguage.ProgrammingLanguageCreator](
    locale,
    "programmingLanguage.ProgrammingLanguageCreator"
  )(x => Some(x.value))
  testCanGen[basketball.BasketballPlayer](
    locale,
    "basketball.BasketballPlayer"
  )(x => Some(x.value))
  testCanGen[basketball.BasketballTeam](
    locale,
    "basketball.BasketballTeam"
  )(x => Some(x.value))
  testCanGen[basketball.BasketballCoach](
    locale,
    "basketball.BasketballCoach"
  )(x => Some(x.value))
  testCanGen[basketball.BasketballPosition](
    locale,
    "basketball.BasketballPosition"
  )(x => Some(x.value))

  val faker: Faker = new Faker(locale)

  test(s"loremWord should return successfully for locale $locale") {
    val res = faker.loremWord()
    assert(res.nonEmpty, res)
  }
  test(s"loremWords with n should return successfully for locale $locale") {
    val res = faker.loremWords(10)
    assertEquals(res.split(" ").length, 10, res)
  }
  test(s"loremWords should return successfully for locale $locale") {
    val res = faker.loremWords()
    assert(res.split(" ").length > 1, res)
  }
  test(s"loremSentence with n should return successfully for locale $locale") {
    val res = faker.loremSentence(10)
    assertEquals(res.split(" ").length, 10, res)
  }
  test(s"loremSentence should return successfully for locale $locale") {
    val res = faker.loremSentence()
    assert(res.split(" ").length > 1, res)
  }
  test(s"loremParagraph with n should return successfully for locale $locale") {
    val res = faker.loremParagraph(10)
    assertEquals(res.split("\\.").length, 10, res)
  }
  test(s"loremParagraph should return successfully for locale $locale") {
    val res = faker.loremParagraph()
    assert(res.split("\\.").length > 1, res)
  }
  test(
    s"loremParagraphs with n should return successfully for locale $locale"
  ) {
    val res = faker.loremParagraphs(10)
    assertEquals(res.split("\n").length, 10, res)
  }
  test(s"loremParagraphs should return successfully for locale $locale") {
    val res = faker.loremParagraphs()
    assert(res.split("\n").length > 1, res)
  }

  test(s"firstName should return successfully for locale $locale") {
    val res = faker.firstName()
    assert(res.nonEmpty, res)
  }
  test(s"fullName should return successfully for locale $locale") {
    val res = faker.fullName()
    assert(res.split(" ").length > 1, res)
  }
  test(s"fullNameWithMiddle should return successfully for locale $locale") {
    val res = faker.fullNameWithMiddle()
    assert(res.split(" ").length > 1, res)
  }
  test(s"lastName should return successfully for locale $locale") {
    val res = faker.lastName()
    assert(res.nonEmpty, res)
  }
  test(s"prefix should return successfully for locale $locale") {
    val res = faker.prefix()
    assert(res.nonEmpty, res)
  }
  test(s"suffix should return successfully for locale $locale") {
    val res = faker.suffix()
    assert(res.nonEmpty, res)
  }
  test(s"title should return successfully for locale $locale") {
    val res = faker.title()
    assert(res.nonEmpty, res)
  }
  test(s"userName should return successfully for locale $locale") {
    val res = faker.userName()
    assertEquals(res.split("\\.").length, 2, res)
  }
  test(s"avatar should return successfully for locale $locale") {
    val res = faker.avatar()
    assert(res.nonEmpty, res)
  }
  test(s"domainName should return successfully for locale $locale") {
    val res = faker.domainName()
    assert(res.nonEmpty, res)
  }
  test(s"domainWord should return successfully for locale $locale") {
    val res = faker.domainWord()
    assert(res.nonEmpty, res)
  }
  test(s"domainSuffix should return successfully for locale $locale") {
    val res = faker.domainSuffix()
    assert(res.nonEmpty, res)
  }
  test(s"emailAddress should return successfully for locale $locale") {
    val res = faker.emailAddress()
    assertEquals(res.split("@").length, 2, res)
  }
  test(s"image should return successfully for locale $locale") {
    val res = faker.image()
    assert(res.nonEmpty, res)
  }
  test(s"ipV4Address should return successfully for locale $locale") {
    val res = faker.ipV4Address()
    assertEquals(res.split("\\.").length, 4, res)
  }
  test(s"ipV4Cidr should return successfully for locale $locale") {
    val res = faker.ipV4Cidr()
    assert(res.nonEmpty, res)
  }
  test(s"ipV6Address should return successfully for locale $locale") {
    val res = faker.ipV6Address()
    assertEquals(res.split(":").length, 8, res)
  }
  test(s"ipV6Cidr should return successfully for locale $locale") {
    val res = faker.ipV6Cidr()
    assert(res.nonEmpty, res)
  }
  test(s"macAddress should return successfully for locale $locale") {
    val res = faker.macAddress()
    assertEquals(res.split(":").length, 6, res)
  }
  test(s"password should return successfully for locale $locale") {
    val res = faker.password()
    assert(res.nonEmpty, res)
  }
  test(s"privateIpV4Address should return successfully for locale $locale") {
    val res = faker.privateIpV4Address()
    assertEquals(res.split("\\.").length, 4, res)
  }
  test(s"publicIpV4Address should return successfully for locale $locale") {
    val res = faker.publicIpV4Address()
    assertEquals(res.split("\\.").length, 4, res)
  }
  test(s"safeEmailAddress should return successfully for locale $locale") {
    val res = faker.safeEmailAddress()
    assertEquals(res.split("@").length, 2, res)
  }
  test(s"slug should return successfully for locale $locale") {
    val res = faker.slug()
    assert(res.nonEmpty, res)
  }
  test(s"url should return successfully for locale $locale") {
    val res = faker.url()
    assert(res.nonEmpty, res)
  }
  test(s"userAgent should return successfully for locale $locale") {
    val res = faker.userAgent()
    assert(res.nonEmpty, res)
  }
  test(s"currentEraInstant should return successfully for locale $locale") {
    val res = faker.currentEraInstant()
    assert(Option(res).isDefined, res)
  }
  test(
    s"currentEraLocalDateTime should return successfully for locale $locale"
  ) {
    val res = faker.currentEraLocalDateTime()
    assert(Option(res).isDefined, res)
  }
  test(
    s"currentEraOffsetDateTime should return successfully for locale $locale"
  ) {
    val res = faker.currentEraOffsetDateTime()
    assert(Option(res).isDefined, res)
  }
  test(
    s"currentEraZonedDateTime should return successfully for locale $locale"
  ) {
    val res = faker.currentEraZonedDateTime()
    assert(Option(res).isDefined, res)
  }
  test(s"futureInstant should return successfully for locale $locale") {
    val compare = Instant.now()
    Thread.sleep(1L)
    val res = faker.futureInstant()
    assert(res.isAfter(compare), s"Res $res vs $compare")
  }
  test(s"futureLocalDateTime should return successfully for locale $locale") {
    val compare = LocalDateTime.now()
    Thread.sleep(1L)
    val res = faker.futureLocalDateTime()
    assert(res.isAfter(compare), s"Res $res vs $compare")
  }
  test(s"futureOffsetDateTime should return successfully for locale $locale") {
    val compare = OffsetDateTime.now()
    Thread.sleep(1L)
    val res = faker.futureOffsetDateTime()
    assert(res.isAfter(compare), s"Res $res vs $compare")
  }
  test(s"futureZonedDateTime should return successfully for locale $locale") {
    val compare = ZonedDateTime.now()
    Thread.sleep(1L)
    val res = faker.futureZonedDateTime()
    assert(res.isAfter(compare), s"Res $res vs $compare")
  }
  test(s"nowInstant should return successfully for locale $locale") {
    val compare = Instant.now()
    Thread.sleep(1L)
    val res = faker.nowInstant()
    assert(res.isAfter(compare), s"Res $res vs $compare")
  }
  test(s"nowLocalDateTime should return successfully for locale $locale") {
    val compare = LocalDateTime.now()
    Thread.sleep(1L)
    val res = faker.nowLocalDateTime()
    assert(res.isAfter(compare), s"Res $res vs $compare")
  }
  test(s"nowOffsetDateTime should return successfully for locale $locale") {
    val compare = OffsetDateTime.now()
    Thread.sleep(1L)
    val res = faker.nowOffsetDateTime()
    assert(res.isAfter(compare), s"Res $res vs $compare")
  }
  test(s"nowZonedDateTime should return successfully for locale $locale") {
    val compare = ZonedDateTime.now()
    Thread.sleep(1L)
    val res = faker.nowZonedDateTime()
    assert(res.isAfter(compare), s"Res $res vs $compare")
  }
  test(s"pastInstant should return successfully for locale $locale") {
    val res = faker.pastInstant()
    Thread.sleep(1L)
    val compare = Instant.now()
    assert(res.isBefore(compare), s"Res $res vs $compare")
  }
  test(s"pastLocalDateTime should return successfully for locale $locale") {
    val res = faker.pastLocalDateTime()
    Thread.sleep(1L)
    val compare = LocalDateTime.now()
    assert(res.isBefore(compare), s"Res $res vs $compare")
  }
  test(s"pastOffsetDateTime should return successfully for locale $locale") {
    val res = faker.pastOffsetDateTime()
    Thread.sleep(1L)
    val compare = OffsetDateTime.now()
    assert(res.isBefore(compare), s"Res $res vs $compare")
  }
  test(s"pastZonedDateTime should return successfully for locale $locale") {
    val res = faker.pastZonedDateTime()
    Thread.sleep(1L)
    val compare = ZonedDateTime.now()
    assert(res.isBefore(compare), s"Res $res vs $compare")
  }
  test(s"randomInstant should return successfully for locale $locale") {
    val res = faker.randomInstant()
    assert(Option(res).isDefined, res)
  }
  test(s"randomLocalDateTime should return successfully for locale $locale") {
    val res = faker.randomLocalDateTime()
    assert(Option(res).isDefined, res)
  }
  test(s"randomOffsetDateTime should return successfully for locale $locale") {
    val res = faker.randomOffsetDateTime()
    assert(Option(res).isDefined, res)
  }
  test(s"randomZonedDateTime should return successfully for locale $locale") {
    val res = faker.randomZonedDateTime()
    assert(Option(res).isDefined, res)
  }
  test(s"buildingNumber should return successfully for locale $locale") {
    val res = faker.buildingNumber()
    assert(res.nonEmpty, res)
  }
  test(s"city should return successfully for locale $locale") {
    val res = faker.city()
    assert(res.nonEmpty, res)
  }
  test(s"cityPrefix should return successfully for locale $locale") {
    val res = faker.cityPrefix()
    assert(res.nonEmpty, res)
  }
  test(s"citySuffix should return successfully for locale $locale") {
    val res = faker.citySuffix()
    assert(res.nonEmpty, res)
  }
  test(s"country should return successfully for locale $locale") {
    val res = faker.country()
    assert(res.code.nonEmpty && res.name.nonEmpty, res)
  }
  test(s"countryCode should return successfully for locale $locale") {
    val res = faker.countryCode()
    assert(res.nonEmpty, res)
  }
  test(s"countryName should return successfully for locale $locale") {
    val res = faker.countryName()
    assert(res.nonEmpty, res)
  }
  test(s"defaultCountry should return successfully for locale $locale") {
    val res = faker.defaultCountry()
    assert(res.code.nonEmpty && res.name.nonEmpty, res)
  }
  test(s"defaultCountryCode should return successfully for locale $locale") {
    val res = faker.defaultCountryCode()
    assert(res.nonEmpty, res)
  }
  test(s"defaultCountryName should return successfully for locale $locale") {
    val res = faker.defaultCountryName()
    assert(res.nonEmpty, res)
  }
  test(s"fullAddress should return successfully for locale $locale") {
    val res = faker.fullAddress()
    assert(res.nonEmpty, res)
  }
  test(s"latitude should return successfully for locale $locale") {
    val res = faker.latitude()
    assert(res.nonEmpty, res)
  }
  test(s"longitude should return successfully for locale $locale") {
    val res = faker.longitude()
    assert(res.nonEmpty, res)
  }
  test(s"secondaryAddress should return successfully for locale $locale") {
    val res = faker.secondaryAddress()
    assert(res.nonEmpty, res)
  }
  test(s"state should return successfully for locale $locale") {
    val res = faker.state()
    assert(res.abbr.nonEmpty && res.name.nonEmpty, res)
  }
  test(s"stateAbbr should return successfully for locale $locale") {
    val res = faker.stateAbbr()
    assert(res.nonEmpty, res)
  }
  test(s"stateZip should return successfully for locale $locale") {
    val res = faker.stateZip()
    assert(res.nonEmpty, res)
  }
  test(s"streetAddress should return successfully for locale $locale") {
    val res = faker.streetAddress()
    assert(res.nonEmpty, res)
  }
  test(s"streetName should return successfully for locale $locale") {
    val res = faker.streetName()
    assert(res.nonEmpty, res)
  }
  test(s"streetPrefix should return successfully for locale $locale") {
    val res = faker.streetPrefix()
    assert(res.nonEmpty, res)
  }
  test(s"streetSuffix should return successfully for locale $locale") {
    val res = faker.streetSuffix()
    assert(res.nonEmpty, res)
  }
  test(s"bs should return successfully for locale $locale") {
    val res = faker.bs()
    assert(res.nonEmpty, res)
  }
  test(s"buzzWord should return successfully for locale $locale") {
    val res = faker.buzzWord()
    assert(res.nonEmpty, res)
  }
  test(s"catchPhrase should return successfully for locale $locale") {
    val res = faker.catchPhrase()
    assert(res.nonEmpty, res)
  }
  test(s"companyDomainName should return successfully for locale $locale") {
    val res = faker.companyDomainName()
    assert(res.nonEmpty, res)
  }
  test(s"companyName should return successfully for locale $locale") {
    val res = faker.companyName()
    assert(res.nonEmpty, res)
  }
  test(s"companySuffix should return successfully for locale $locale") {
    val res = faker.companySuffix()
    assert(res.nonEmpty, res)
  }
  test(s"companyUrl should return successfully for locale $locale") {
    val res = faker.companyUrl()
    assert(res.nonEmpty, res)
  }
  test(s"industry should return successfully for locale $locale") {
    val res = faker.industry()
    assert(res.nonEmpty, res)
  }
  test(s"logo should return successfully for locale $locale") {
    val res = faker.logo()
    assert(res.nonEmpty, res)
  }
  test(s"profession should return successfully for locale $locale") {
    val res = faker.profession()
    assert(res.nonEmpty, res)
  }
  test(s"phoneNumber should return successfully for locale $locale") {
    val res = faker.phoneNumber()
    assert(res.nonEmpty, res)
  }
  test(s"cellPhoneNumber should return successfully for locale $locale") {
    val res = faker.cellPhoneNumber()
    assert(res.nonEmpty, res)
  }
  test(s"pokemonName should return successfully for locale $locale") {
    val res = faker.pokemonName()
    assert(res.nonEmpty, res)
  }
  test(s"pokemonLocation should return successfully for locale $locale") {
    val res = faker.pokemonLocation()
    assert(res.nonEmpty, res)
  }
  test(s"pokemonMove should return successfully for locale $locale") {
    val res = faker.pokemonMove()
    assert(res.nonEmpty, res)
  }
  test(s"animalName should return successfully for locale $locale") {
    val res = faker.animalName()
    assert(res.nonEmpty, res)
  }
  test(s"genderType should return successfully for locale $locale") {
    val res = faker.genderType()
    assert(res.nonEmpty, res)
  }
  test(s"genderBinaryType should return successfully for locale $locale") {
    val res = faker.genderBinaryType()
    assert(res.nonEmpty, res)
  }
  test(s"genderShortBinaryType should return successfully for locale $locale") {
    val res = faker.genderShortBinaryType()
    assert(res.nonEmpty, res)
  }
  test(s"zeldaCharacter should return successfully for locale $locale") {
    val res = faker.zeldaCharacter()
    assert(res.nonEmpty, res)
  }
  test(s"zeldaGame should return successfully for locale $locale") {
    val res = faker.zeldaGame()
    assert(res.nonEmpty, res)
  }
  test(s"zeldaItem should return successfully for locale $locale") {
    val res = faker.zeldaItem()
    assert(res.nonEmpty, res)
  }
  test(s"zeldaLocation should return successfully for locale $locale") {
    val res = faker.zeldaLocation()
    assert(res.nonEmpty, res)
  }
  test(s"slackEmoji should return successfully for locale $locale") {
    val res = faker.slackEmoji()
    assert(res.nonEmpty, res)
  }
  test(s"slackEmojiActivity should return successfully for locale $locale") {
    val res = faker.slackEmojiActivity()
    assert(res.nonEmpty, res)
  }
  test(s"slackEmojiCelebration should return successfully for locale $locale") {
    val res = faker.slackEmojiCelebration()
    assert(res.nonEmpty, res)
  }
  test(s"slackEmojiCustom should return successfully for locale $locale") {
    val res = faker.slackEmojiCustom()
    assert(res.nonEmpty, res)
  }
  test(s"slackEmojiFood should return successfully for locale $locale") {
    val res = faker.slackEmojiFood()
    assert(res.nonEmpty, res)
  }
  test(s"slackEmojiNature should return successfully for locale $locale") {
    val res = faker.slackEmojiNature()
    assert(res.nonEmpty, res)
  }
  test(s"slackEmojiObject should return successfully for locale $locale") {
    val res = faker.slackEmojiObject()
    assert(res.nonEmpty, res)
  }
  test(s"slackEmojiPerson should return successfully for locale $locale") {
    val res = faker.slackEmojiPerson()
    assert(res.nonEmpty, res)
  }
  test(s"slackEmojiTravel should return successfully for locale $locale") {
    val res = faker.slackEmojiTravel()
    assert(res.nonEmpty, res)
  }
  test(s"weatherDescription should return successfully for locale $locale") {
    val res = faker.weatherDescription()
    assert(res.nonEmpty, res)
  }
  test(s"temperatureCelsius should return successfully for locale $locale") {
    val res = faker.temperatureCelsius()
    assert(res.nonEmpty, res)
  }
  test(s"temperatureFahrenheit should return successfully for locale $locale") {
    val res = faker.temperatureFahrenheit()
    assert(res.nonEmpty, res)
  }
  test(s"musicAlbum should return successfully for locale $locale") {
    val res = faker.musicAlbum()
    assert(res.nonEmpty, res)
  }
  test(s"musicalGenre should return successfully for locale $locale") {
    val res = faker.musicalGenre()
    assert(res.nonEmpty, res)
  }
  test(s"musicalInstrument should return successfully for locale $locale") {
    val res = faker.musicalInstrument()
    assert(res.nonEmpty, res)
  }
  test(s"musicBand should return successfully for locale $locale") {
    val res = faker.musicBand()
    assert(res.nonEmpty, res)
  }
  test(s"dragonballCharacter should return successfully for locale $locale") {
    val res = faker.dragonballCharacter()
    assert(res.nonEmpty, res)
  }
  test(s"jobField should return successfully for locale $locale") {
    val res = faker.jobField()
    assert(res.nonEmpty, res)
  }
  test(s"jobSeniority should return successfully for locale $locale") {
    val res = faker.jobSeniority()
    assert(res.nonEmpty, res)
  }
  test(s"jobPosition should return successfully for locale $locale") {
    val res = faker.jobPosition()
    assert(res.nonEmpty, res)
  }
  test(s"jobKeySkill should return successfully for locale $locale") {
    val res = faker.jobKeySkill()
    assert(res.nonEmpty, res)
  }
  test(s"jobEmploymentType should return successfully for locale $locale") {
    val res = faker.jobEmploymentType()
    assert(res.nonEmpty, res)
  }
  test(s"jobEducationLevel should return successfully for locale $locale") {
    val res = faker.jobEducationLevel()
    assert(res.nonEmpty, res)
  }
  test(s"jobTitle should return successfully for locale $locale") {
    val res = faker.jobTitle()
    assert(res.nonEmpty, res)
  }
  test(s"currencyCode should return successfully for locale $locale") {
    val res = faker.currencyCode()
    assert(res.nonEmpty, res)
  }
  test(s"currencyName should return successfully for locale $locale") {
    val res = faker.currencyName()
    assert(res.nonEmpty, res)
  }
  test(s"currencySymbol should return successfully for locale $locale") {
    val res = faker.currencySymbol()
    assert(res.nonEmpty, res)
  }
  test(s"god should return successfully for locale $locale") {
    val res = faker.god()
    assert(res.nonEmpty, res)
  }
  test(s"primordial should return successfully for locale $locale") {
    val res = faker.primordial()
    assert(res.nonEmpty, res)
  }
  test(s"titan should return successfully for locale $locale") {
    val res = faker.titan()
    assert(res.nonEmpty, res)
  }
  test(s"hero should return successfully for locale $locale") {
    val res = faker.hero()
    assert(res.nonEmpty, res)
  }
  test(s"aircraft should return successfully for locale $locale") {
    val res = faker.aircraft()
    assert(res.nonEmpty, res)
  }
  test(s"airport should return successfully for locale $locale") {
    val res = faker.airport()
    assert(res.nonEmpty, res)
  }
  test(s"metar should return successfully for locale $locale") {
    val res = faker.metar()
    assert(res.nonEmpty, res)
  }
  test(s"name should return successfully for locale $locale") {
    val res = faker.catName()
    assert(res.nonEmpty, res)
  }
  test(s"breed should return successfully for locale $locale") {
    val res = faker.catBreed()
    assert(res.nonEmpty, res)
  }
  test(s"registry should return successfully for locale $locale") {
    val res = faker.catRegistry()
    assert(res.nonEmpty, res)
  }
  test(s"yodaQuote should return successfully for locale $locale") {
    val res = faker.yodaQuote()
    assert(res.nonEmpty, res)
  }
  test(
    s"programmingLanguageName should return successfully for locale $locale"
  ) {
    val res = faker.programmingLanguageName()
    assert(res.nonEmpty, res)
  }
  test(
    s"programmingLanguageCreator should return successfully for locale $locale"
  ) {
    val res = faker.programmingLanguageCreator()
    assert(res.nonEmpty, res)
  }
  test(
    s"basketballPlayer should return successfully for locale $locale"
  ) {
    val res = faker.basketballPlayer()
    assert(res.nonEmpty, res)
  }
  test(
    s"basketballTeam should return successfully for locale $locale"
  ) {
    val res = faker.basketballTeam()
    assert(res.nonEmpty, res)
  }
  test(
    s"basketballCoach should return successfully for locale $locale"
  ) {
    val res = faker.basketballCoach()
    assert(res.nonEmpty, res)
  }
  test(
    s"basketballPosition should return successfully for locale $locale"
  ) {
    val res = faker.basketballPosition()
    assert(res.nonEmpty, res)
  }
}
