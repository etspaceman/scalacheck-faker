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

import java.time._
import java.util.Locale

import org.scalacheck.Arbitrary
import org.scalacheck.rng.Seed

import faker.syntax.scalacheck._

final class Faker(val locale: Locale) {

  implicit val loader: ResourceLoader = new ResourceLoader(locale)

  // Lorem
  def loremCharacters(seed: Seed): String =
    Arbitrary.arbitrary[lorem.LoremCharacters].one(seed).value
  def loremCharacters(): String = loremCharacters(Seed.random())
  def loremWord(seed: Seed): String =
    Arbitrary.arbitrary[lorem.LoremWord].one(seed).value
  def loremWord(): String = loremWord(Seed.random())
  def loremWords(words: Int, seed: Seed): String =
    Arbitrary
      .arbitrary[lorem.LoremWord]
      .take(words, seed)
      .map(_.value)
      .mkString(" ")
  def loremWords(words: Int): String = loremWords(words, Seed.random())
  def loremWords(seed: Seed): String =
    Arbitrary.arbitrary[lorem.LoremWords].one(seed).value
  def loremWords(): String = loremWords(Seed.random())
  def loremSentence(words: Int, seed: Seed): String =
    loremWords(words, seed) + "."
  def loremSentence(words: Int): String = loremSentence(words, Seed.random())
  def loremSentence(seed: Seed): String =
    Arbitrary.arbitrary[lorem.LoremSentence].one(seed).value
  def loremSentence(): String = loremSentence(Seed.random())
  def loremParagraph(sentences: Int, seed: Seed): String =
    Arbitrary
      .arbitrary[lorem.LoremSentence]
      .take(sentences, seed)
      .map(_.value)
      .mkString(" ")
  def loremParagraph(sentences: Int): String =
    loremParagraph(sentences, Seed.random())
  def loremParagraph(seed: Seed): String =
    Arbitrary.arbitrary[lorem.LoremParagraph].one(seed).value
  def loremParagraph(): String = loremParagraph(Seed.random())
  def loremParagraphs(paragraphs: Int, seed: Seed): String =
    Arbitrary
      .arbitrary[lorem.LoremParagraph]
      .take(paragraphs, seed)
      .map(_.value)
      .mkString("\n")
  def loremParagraphs(paragraphs: Int): String =
    loremParagraphs(paragraphs, Seed.random())
  def loremParagraphs(): String = loremParagraphs(Seed.random())
  def loremParagraphs(seed: Seed): String =
    Arbitrary.arbitrary[lorem.LoremParagraphs].one(seed).value

  // Name
  def firstName(seed: Seed): String =
    Arbitrary.arbitrary[name.FirstName].one(seed).value
  def firstName(): String = firstName(Seed.random())
  def fullName(seed: Seed): String =
    Arbitrary.arbitrary[name.FullName].one(seed).value
  def fullName(): String = fullName(Seed.random())
  def fullNameWithMiddle(seed: Seed): String =
    Arbitrary.arbitrary[name.FullNameWithMiddle].one(seed).value
  def fullNameWithMiddle(): String = fullNameWithMiddle(Seed.random())
  def lastName(seed: Seed): String =
    Arbitrary.arbitrary[name.LastName].one(seed).value
  def lastName(): String = lastName(Seed.random())
  def prefix(seed: Seed): String =
    Arbitrary.arbitrary[name.Prefix].one(seed).value
  def prefix(): String = prefix(Seed.random())
  def suffix(seed: Seed): String =
    Arbitrary.arbitrary[name.Suffix].one(seed).value
  def suffix(): String = suffix(Seed.random())
  def title(seed: Seed): String =
    Arbitrary.arbitrary[name.Title].one(seed).value
  def title(): String = title(Seed.random())
  def userName(seed: Seed): String =
    Arbitrary.arbitrary[name.UserName].one(seed).value
  def userName(): String = userName(Seed.random())

  // Internet
  def avatar(seed: Seed): String =
    Arbitrary.arbitrary[internet.Avatar].one(seed).value
  def avatar(): String = avatar(Seed.random())
  def domainName(seed: Seed): String =
    Arbitrary.arbitrary[internet.DomainName].one(seed).value
  def domainName(): String = domainName(Seed.random())
  def domainSuffix(seed: Seed): String =
    Arbitrary.arbitrary[internet.DomainSuffix].one(seed).value
  def domainSuffix(): String = domainSuffix(Seed.random())
  def domainWord(seed: Seed): String =
    Arbitrary.arbitrary[internet.DomainWord].one(seed).value
  def domainWord(): String = domainWord(Seed.random())
  def emailAddress(seed: Seed): String =
    Arbitrary.arbitrary[internet.EmailAddress].one(seed).value
  def emailAddress(): String = emailAddress(Seed.random())
  def image(seed: Seed): String =
    Arbitrary.arbitrary[internet.Image].one(seed).value
  def image(): String = image(Seed.random())
  def ipV4Address(seed: Seed): String =
    Arbitrary.arbitrary[internet.IpV4Address].one(seed).value
  def ipV4Address(): String = ipV4Address(Seed.random())
  def ipV4Cidr(seed: Seed): String =
    Arbitrary.arbitrary[internet.IpV4Cidr].one(seed).value
  def ipV4Cidr(): String = ipV4Cidr(Seed.random())
  def ipV6Address(seed: Seed): String =
    Arbitrary.arbitrary[internet.IpV6Address].one(seed).value
  def ipV6Address(): String = ipV6Address(Seed.random())
  def ipV6Cidr(seed: Seed): String =
    Arbitrary.arbitrary[internet.IpV6Cidr].one(seed).value
  def ipV6Cidr(): String = ipV6Cidr(Seed.random())
  def macAddress(seed: Seed): String =
    Arbitrary.arbitrary[internet.MacAddress].one(seed).value
  def macAddress(): String = macAddress(Seed.random())
  def password(seed: Seed): String =
    Arbitrary.arbitrary[internet.Password].one(seed).value
  def password(): String = password(Seed.random())
  def privateIpV4Address(seed: Seed): String =
    Arbitrary.arbitrary[internet.PrivateIpV4Address].one(seed).value
  def privateIpV4Address(): String = privateIpV4Address(Seed.random())
  def publicIpV4Address(seed: Seed): String =
    Arbitrary.arbitrary[internet.PublicIpV4Address].one(seed).value
  def publicIpV4Address(): String = publicIpV4Address(Seed.random())
  def safeEmailAddress(seed: Seed): String =
    Arbitrary.arbitrary[internet.SafeEmailAddress].one(seed).value
  def safeEmailAddress(): String = safeEmailAddress(Seed.random())
  def slug(seed: Seed): String =
    Arbitrary.arbitrary[internet.Slug].one(seed).value
  def slug(): String = slug(Seed.random())
  def url(seed: Seed): String =
    Arbitrary.arbitrary[internet.Url].one(seed).value
  def url(): String = url(Seed.random())
  def userAgent(seed: Seed): String =
    Arbitrary.arbitrary[internet.UserAgent].one(seed).value
  def userAgent(): String = userAgent(Seed.random())

  // Time
  def currentEraInstant(seed: Seed): Instant =
    Arbitrary.arbitrary[time.CurrentEraInstant].one(seed).value
  def currentEraInstant(): Instant = currentEraInstant(Seed.random())
  def currentEraLocalDateTime(seed: Seed): LocalDateTime =
    Arbitrary.arbitrary[time.CurrentEraLocalDateTime].one(seed).value
  def currentEraLocalDateTime(): LocalDateTime =
    currentEraLocalDateTime(Seed.random())
  def currentEraOffsetDateTime(seed: Seed): OffsetDateTime =
    Arbitrary.arbitrary[time.CurrentEraOffsetDateTime].one(seed).value
  def currentEraOffsetDateTime(): OffsetDateTime =
    currentEraOffsetDateTime(Seed.random())
  def currentEraZonedDateTime(seed: Seed): ZonedDateTime =
    Arbitrary.arbitrary[time.CurrentEraZonedDateTime].one(seed).value
  def currentEraZonedDateTime(): ZonedDateTime =
    currentEraZonedDateTime(Seed.random())
  def futureInstant(seed: Seed): Instant =
    Arbitrary.arbitrary[time.FutureInstant].one(seed).value
  def futureInstant(): Instant = futureInstant(Seed.random())
  def futureLocalDateTime(seed: Seed): LocalDateTime =
    Arbitrary.arbitrary[time.FutureLocalDateTime].one(seed).value
  def futureLocalDateTime(): LocalDateTime =
    futureLocalDateTime(Seed.random())
  def futureOffsetDateTime(seed: Seed): OffsetDateTime =
    Arbitrary.arbitrary[time.FutureOffsetDateTime].one(seed).value
  def futureOffsetDateTime(): OffsetDateTime =
    futureOffsetDateTime(Seed.random())
  def futureZonedDateTime(seed: Seed): ZonedDateTime =
    Arbitrary.arbitrary[time.FutureZonedDateTime].one(seed).value
  def futureZonedDateTime(): ZonedDateTime =
    futureZonedDateTime(Seed.random())
  def nowInstant(seed: Seed): Instant =
    Arbitrary.arbitrary[time.NowInstant].one(seed).value
  def nowInstant(): Instant = nowInstant(Seed.random())
  def nowLocalDateTime(seed: Seed): LocalDateTime =
    Arbitrary.arbitrary[time.NowLocalDateTime].one(seed).value
  def nowLocalDateTime(): LocalDateTime =
    nowLocalDateTime(Seed.random())
  def nowOffsetDateTime(seed: Seed): OffsetDateTime =
    Arbitrary.arbitrary[time.NowOffsetDateTime].one(seed).value
  def nowOffsetDateTime(): OffsetDateTime =
    nowOffsetDateTime(Seed.random())
  def nowZonedDateTime(seed: Seed): ZonedDateTime =
    Arbitrary.arbitrary[time.NowZonedDateTime].one(seed).value
  def nowZonedDateTime(): ZonedDateTime =
    nowZonedDateTime(Seed.random())
  def pastInstant(seed: Seed): Instant =
    Arbitrary.arbitrary[time.PastInstant].one(seed).value
  def pastInstant(): Instant = pastInstant(Seed.random())
  def pastLocalDateTime(seed: Seed): LocalDateTime =
    Arbitrary.arbitrary[time.PastLocalDateTime].one(seed).value
  def pastLocalDateTime(): LocalDateTime =
    pastLocalDateTime(Seed.random())
  def pastOffsetDateTime(seed: Seed): OffsetDateTime =
    Arbitrary.arbitrary[time.PastOffsetDateTime].one(seed).value
  def pastOffsetDateTime(): OffsetDateTime =
    pastOffsetDateTime(Seed.random())
  def pastZonedDateTime(seed: Seed): ZonedDateTime =
    Arbitrary.arbitrary[time.PastZonedDateTime].one(seed).value
  def pastZonedDateTime(): ZonedDateTime =
    pastZonedDateTime(Seed.random())
  def randomInstant(seed: Seed): Instant =
    Arbitrary.arbitrary[time.RandomInstant].one(seed).value
  def randomInstant(): Instant = randomInstant(Seed.random())
  def randomLocalDateTime(seed: Seed): LocalDateTime =
    Arbitrary.arbitrary[time.RandomLocalDateTime].one(seed).value
  def randomLocalDateTime(): LocalDateTime =
    randomLocalDateTime(Seed.random())
  def randomOffsetDateTime(seed: Seed): OffsetDateTime =
    Arbitrary.arbitrary[time.RandomOffsetDateTime].one(seed).value
  def randomOffsetDateTime(): OffsetDateTime =
    randomOffsetDateTime(Seed.random())
  def randomZonedDateTime(seed: Seed): ZonedDateTime =
    Arbitrary.arbitrary[time.RandomZonedDateTime].one(seed).value
  def randomZonedDateTime(): ZonedDateTime =
    randomZonedDateTime(Seed.random())

  // Address
  def buildingNumber(seed: Seed): String =
    Arbitrary.arbitrary[address.BuildingNumber].one(seed).value
  def buildingNumber(): String = buildingNumber(Seed.random())
  def city(seed: Seed): String =
    Arbitrary.arbitrary[address.City].one(seed).value
  def city(): String = city(Seed.random())
  def cityPrefix(seed: Seed): String =
    Arbitrary.arbitrary[address.CityPrefix].one(seed).value
  def cityPrefix(): String = cityPrefix(Seed.random())
  def citySuffix(seed: Seed): String =
    Arbitrary.arbitrary[address.CitySuffix].one(seed).value
  def citySuffix(): String = citySuffix(Seed.random())
  def country(seed: Seed): address.Country =
    Arbitrary.arbitrary[address.Country].one(seed)
  def country(): address.Country = country(Seed.random())
  def countryCode(seed: Seed): String = country(seed).code
  def countryCode(): String = countryCode(Seed.random())
  def countryName(seed: Seed): String = country(seed).name
  def countryName(): String = countryName(Seed.random())
  def defaultCountry(seed: Seed): address.DefaultCountry =
    Arbitrary.arbitrary[address.DefaultCountry].one(seed)
  def defaultCountry(): address.DefaultCountry = defaultCountry(Seed.random())
  def defaultCountryCode(seed: Seed): String =
    defaultCountry(seed).code
  def defaultCountryCode(): String = defaultCountryCode(Seed.random())
  def defaultCountryName(seed: Seed): String =
    defaultCountry(seed).name
  def defaultCountryName(): String = defaultCountryName(Seed.random())
  def fullAddress(seed: Seed): String =
    Arbitrary.arbitrary[address.FullAddress].one(seed).value
  def fullAddress(): String = fullAddress(Seed.random())
  def latitude(seed: Seed): String =
    Arbitrary.arbitrary[address.Latitude].one(seed).value
  def latitude(): String = latitude(Seed.random())
  def longitude(seed: Seed): String =
    Arbitrary.arbitrary[address.Longitude].one(seed).value
  def longitude(): String = longitude(Seed.random())
  def postalCode(seed: Seed): String =
    Arbitrary.arbitrary[address.PostalCode].one(seed).value
  def postalCode(): String = postalCode(Seed.random())
  def secondaryAddress(seed: Seed): String =
    Arbitrary.arbitrary[address.SecondaryAddress].one(seed).value
  def secondaryAddress(): String = secondaryAddress(Seed.random())
  def state(seed: Seed): states.StateLike =
    Arbitrary.arbitrary[states.StateLike].one(seed)
  def state(): states.StateLike = state(Seed.random())
  def stateAbbr(seed: Seed): String = state(seed).abbr
  def stateAbbr(): String = stateAbbr(Seed.random())
  def stateZip(seed: Seed): String =
    state(seed).postalCodeGen.one(seed)
  def stateZip(): String = stateZip(Seed.random())
  def streetAddress(seed: Seed): String =
    Arbitrary.arbitrary[address.StreetAddress].one(seed).value
  def streetAddress(): String = streetAddress(Seed.random())
  def streetName(seed: Seed): String =
    Arbitrary.arbitrary[address.StreetName].one(seed).value
  def streetName(): String = streetName(Seed.random())
  def streetPrefix(seed: Seed): String =
    Arbitrary.arbitrary[address.StreetPrefix].one(seed).value
  def streetPrefix(): String = streetPrefix(Seed.random())
  def streetSuffix(seed: Seed): String =
    Arbitrary.arbitrary[address.StreetSuffix].one(seed).value
  def streetSuffix(): String = streetSuffix(Seed.random())

  // Company
  def bs(seed: Seed): String =
    Arbitrary.arbitrary[company.BS].one(seed).value
  def bs(): String = bs(Seed.random())
  def buzzWord(seed: Seed): String =
    Arbitrary.arbitrary[company.BuzzWord].one(seed).value
  def buzzWord(): String = buzzWord(Seed.random())
  def catchPhrase(seed: Seed): String =
    Arbitrary.arbitrary[company.CatchPhrase].one(seed).value
  def catchPhrase(): String = catchPhrase(Seed.random())
  def companyDomainName(seed: Seed): String =
    Arbitrary.arbitrary[company.CompanyDomainName].one(seed).value
  def companyDomainName(): String = companyDomainName(Seed.random())
  def companyName(seed: Seed): String =
    Arbitrary.arbitrary[company.CompanyName].one(seed).value
  def companyName(): String = companyName(Seed.random())
  def companySuffix(seed: Seed): String =
    Arbitrary.arbitrary[company.CompanySuffix].one(seed).value
  def companySuffix(): String = companySuffix(Seed.random())
  def companyUrl(seed: Seed): String =
    Arbitrary.arbitrary[company.CompanyUrl].one(seed).value
  def companyUrl(): String = companyUrl(Seed.random())
  def industry(seed: Seed): String =
    Arbitrary.arbitrary[company.Industry].one(seed).value
  def industry(): String = industry(Seed.random())
  def logo(seed: Seed): String =
    Arbitrary.arbitrary[company.Logo].one(seed).value
  def logo(): String = logo(Seed.random())
  def profession(seed: Seed): String =
    Arbitrary.arbitrary[company.Profession].one(seed).value
  def profession(): String = profession(Seed.random())

  // Phone Number
  def phoneNumber(seed: Seed): String =
    Arbitrary.arbitrary[phone.PhoneNumber].one(seed).value
  def phoneNumber(): String = phoneNumber(Seed.random())
  def cellPhoneNumber(seed: Seed): String =
    Arbitrary.arbitrary[phone.CellPhoneNumber].one(seed).value
  def cellPhoneNumber(): String = cellPhoneNumber(Seed.random())

  // Pokemon
  def pokemonName(seed: Seed): String =
    Arbitrary.arbitrary[pokemon.PokemonName].one(seed).value
  def pokemonName(): String = pokemonName(Seed.random())
  def pokemonLocation(seed: Seed): String =
    Arbitrary.arbitrary[pokemon.PokemonLocation].one(seed).value
  def pokemonLocation(): String = pokemonLocation(Seed.random())
  def pokemonMove(seed: Seed): String =
    Arbitrary.arbitrary[pokemon.PokemonMove].one(seed).value
  def pokemonMove(): String = pokemonMove(Seed.random())

  // Animal
  def animalName(seed: Seed): String =
    Arbitrary.arbitrary[animal.AnimalName].one(seed).value
  def animalName(): String = animalName(Seed.random())

  // Gender
  def genderType(seed: Seed): String =
    Arbitrary.arbitrary[gender.GenderType].one(seed).value
  def genderType(): String = genderType(Seed.random())
  def genderBinaryType(seed: Seed): String =
    Arbitrary.arbitrary[gender.GenderBinaryType].one(seed).value
  def genderBinaryType(): String = genderBinaryType(Seed.random())
  def genderShortBinaryType(seed: Seed): String =
    Arbitrary.arbitrary[gender.GenderShortBinaryType].one(seed).value
  def genderShortBinaryType(): String = genderShortBinaryType(Seed.random())

  // Zelda
  def zeldaGame(seed: Seed): String =
    Arbitrary.arbitrary[zelda.ZeldaGame].one(seed).value
  def zeldaGame(): String = zeldaGame(Seed.random())
  def zeldaCharacter(seed: Seed): String =
    Arbitrary.arbitrary[zelda.ZeldaCharacter].one(seed).value
  def zeldaCharacter(): String = zeldaCharacter(Seed.random())
  def zeldaLocation(seed: Seed): String =
    Arbitrary.arbitrary[zelda.ZeldaLocation].one(seed).value
  def zeldaLocation(): String = zeldaLocation(Seed.random())
  def zeldaItem(seed: Seed): String =
    Arbitrary.arbitrary[zelda.ZeldaItem].one(seed).value
  def zeldaItem(): String = zeldaItem(Seed.random())

  // Slack Emojis
  def slackEmoji(seed: Seed): String =
    Arbitrary.arbitrary[slack.emoji.SlackEmoji].one(seed).value
  def slackEmoji(): String = slackEmoji(Seed.random())
  def slackEmojiActivity(seed: Seed): String =
    Arbitrary.arbitrary[slack.emoji.SlackEmojiActivity].one(seed).value
  def slackEmojiActivity(): String = slackEmojiActivity(Seed.random())
  def slackEmojiCelebration(seed: Seed): String =
    Arbitrary.arbitrary[slack.emoji.SlackEmojiCelebration].one(seed).value
  def slackEmojiCelebration(): String = slackEmojiCelebration(Seed.random())
  def slackEmojiCustom(seed: Seed): String =
    Arbitrary.arbitrary[slack.emoji.SlackEmojiCustom].one(seed).value
  def slackEmojiCustom(): String = slackEmojiCustom(Seed.random())
  def slackEmojiFood(seed: Seed): String =
    Arbitrary.arbitrary[slack.emoji.SlackEmojiFood].one(seed).value
  def slackEmojiFood(): String = slackEmojiFood(Seed.random())
  def slackEmojiNature(seed: Seed): String =
    Arbitrary.arbitrary[slack.emoji.SlackEmojiNature].one(seed).value
  def slackEmojiNature(): String = slackEmojiNature(Seed.random())
  def slackEmojiObject(seed: Seed): String =
    Arbitrary.arbitrary[slack.emoji.SlackEmojiObject].one(seed).value
  def slackEmojiObject(): String = slackEmojiObject(Seed.random())
  def slackEmojiPerson(seed: Seed): String =
    Arbitrary.arbitrary[slack.emoji.SlackEmojiPerson].one(seed).value
  def slackEmojiPerson(): String = slackEmojiPerson(Seed.random())
  def slackEmojiTravel(seed: Seed): String =
    Arbitrary.arbitrary[slack.emoji.SlackEmojiTravel].one(seed).value
  def slackEmojiTravel(): String = slackEmojiTravel(Seed.random())

  // Weather
  def weatherDescription(seed: Seed): String =
    Arbitrary.arbitrary[weather.WeatherDescription].one(seed).value
  def weatherDescription(): String = weatherDescription(Seed.random())
  def temperatureCelsius(seed: Seed): String =
    Arbitrary.arbitrary[weather.TemperatureCelsius].one(seed).value
  def temperatureCelsius(): String = temperatureCelsius(Seed.random())
  def temperatureFahrenheit(seed: Seed): String =
    Arbitrary.arbitrary[weather.TemperatureFahrenheit].one(seed).value
  def temperatureFahrenheit(): String = temperatureFahrenheit(Seed.random())

  // Music
  def musicAlbum(seed: Seed): String =
    Arbitrary.arbitrary[music.MusicAlbum].one(seed).value
  def musicAlbum(): String = musicAlbum(Seed.random())
  def musicalGenre(seed: Seed): String =
    Arbitrary.arbitrary[music.MusicalGenre].one(seed).value
  def musicalGenre(): String = musicalGenre(Seed.random())
  def musicalInstrument(seed: Seed): String =
    Arbitrary.arbitrary[music.MusicalInstrument].one(seed).value
  def musicalInstrument(): String = musicalInstrument(Seed.random())
  def musicBand(seed: Seed): String =
    Arbitrary.arbitrary[music.MusicBand].one(seed).value
  def musicBand(): String = musicBand(Seed.random())

  // Dragonball
  def dragonballCharacter(seed: Seed): String =
    Arbitrary.arbitrary[dragonball.DragonBallCharacter].one(seed).value
  def dragonballCharacter(): String = dragonballCharacter(Seed.random())

  // Job
  def jobField(seed: Seed): String =
    Arbitrary.arbitrary[job.JobField].one(seed).value
  def jobField(): String = jobField(Seed.random())
  def jobSeniority(seed: Seed): String =
    Arbitrary.arbitrary[job.JobSeniority].one(seed).value
  def jobSeniority(): String = jobSeniority(Seed.random())
  def jobPosition(seed: Seed): String =
    Arbitrary.arbitrary[job.JobPosition].one(seed).value
  def jobPosition(): String = jobPosition(Seed.random())
  def jobKeySkill(seed: Seed): String =
    Arbitrary.arbitrary[job.JobKeySkill].one(seed).value
  def jobKeySkill(): String = jobKeySkill(Seed.random())
  def jobEmploymentType(seed: Seed): String =
    Arbitrary.arbitrary[job.JobEmploymentType].one(seed).value
  def jobEmploymentType(): String = jobEmploymentType(Seed.random())
  def jobEducationLevel(seed: Seed): String =
    Arbitrary.arbitrary[job.JobEducationLevel].one(seed).value
  def jobEducationLevel(): String = jobEducationLevel(Seed.random())
  def jobTitle(seed: Seed): String =
    Arbitrary.arbitrary[job.JobTitle].one(seed).value
  def jobTitle(): String = jobTitle(Seed.random())

  // currency
  def currencyCode(seed: Seed): String =
    Arbitrary.arbitrary[currency.CurrencyCode].one(seed).value
  def currencyCode(): String = currencyCode(Seed.random())
  def currencyName(seed: Seed): String =
    Arbitrary.arbitrary[currency.CurrencyName].one(seed).value
  def currencyName(): String = currencyName(Seed.random())
  def currencySymbol(seed: Seed): String =
    Arbitrary.arbitrary[currency.CurrencySymbol].one(seed).value
  def currencySymbol(): String = currencySymbol(Seed.random())

  // Ancient
  def god(seed: Seed): String =
    Arbitrary.arbitrary[ancient.God].one(seed).value
  def god(): String = god(Seed.random())
  def primordial(seed: Seed): String =
    Arbitrary.arbitrary[ancient.Primordial].one(seed).value
  def primordial(): String = primordial(Seed.random())
  def titan(seed: Seed): String =
    Arbitrary.arbitrary[ancient.Titan].one(seed).value
  def titan(): String = titan(Seed.random())
  def hero(seed: Seed): String =
    Arbitrary.arbitrary[ancient.Hero].one(seed).value
  def hero(): String = hero(Seed.random())

  // Aviation
  def aircraft(seed: Seed): String =
    Arbitrary.arbitrary[aviation.Aircraft].one(seed).value
  def aircraft(): String = aircraft(Seed.random())
  def airport(seed: Seed): String =
    Arbitrary.arbitrary[aviation.Airport].one(seed).value
  def airport(): String = airport(Seed.random())
  def metar(seed: Seed): String =
    Arbitrary.arbitrary[aviation.Metar].one(seed).value
  def metar(): String = metar(Seed.random())

  // Cat
  def catName(seed: Seed): String =
    Arbitrary.arbitrary[cat.Name].one(seed).value
  def catName(): String = catName(Seed.random())
  def catBreed(seed: Seed): String =
    Arbitrary.arbitrary[cat.Breed].one(seed).value
  def catBreed(): String = catBreed(Seed.random())
  def catRegistry(seed: Seed): String =
    Arbitrary.arbitrary[cat.Registry].one(seed).value
  def catRegistry(): String = catRegistry(Seed.random())

  // Yoda
  def yodaQuote(seed: Seed): String =
    Arbitrary.arbitrary[yoda.YodaQuote].one(seed).value
  def yodaQuote(): String = yodaQuote(Seed.random())

  // Programming Language
  def programmingLanguageName(seed: Seed): String =
    Arbitrary
      .arbitrary[programmingLanguage.ProgrammingLanguageName]
      .one(seed)
      .value
  def programmingLanguageName(): String = programmingLanguageName(Seed.random())
  def programmingLanguageCreator(seed: Seed): String =
    Arbitrary
      .arbitrary[programmingLanguage.ProgrammingLanguageCreator]
      .one(seed)
      .value
  def programmingLanguageCreator(): String = programmingLanguageCreator(
    Seed.random()
  )

  // Basketball
  def basketballTeam(seed: Seed): String =
    Arbitrary.arbitrary[basketball.BasketballTeam].one(seed).value
  def basketballTeam(): String = basketballTeam(Seed.random())
  def basketballPlayer(seed: Seed): String =
    Arbitrary.arbitrary[basketball.BasketballPlayer].one(seed).value
  def basketballPlayer(): String = basketballPlayer(Seed.random())
  def basketballCoach(seed: Seed): String =
    Arbitrary.arbitrary[basketball.BasketballCoach].one(seed).value
  def basketballCoach(): String = basketballCoach(Seed.random())
  def basketballPosition(seed: Seed): String =
    Arbitrary.arbitrary[basketball.BasketballPosition].one(seed).value
  def basketballPosition(): String = basketballPosition(Seed.random())

  // Color
  def colorName(seed: Seed): String =
    Arbitrary.arbitrary[color.ColorName].one(seed).value
  def colorName(): String = colorName(Seed.random())

  // App
  def appName(seed: Seed): String =
    Arbitrary.arbitrary[app.AppName].one(seed).value
  def appName(): String = appName(Seed.random())
  def appVersion(seed: Seed): String =
    Arbitrary.arbitrary[app.AppVersion].one(seed).value
  def appVersion(): String = appVersion(Seed.random())
  def appAuthor(seed: Seed): String =
    Arbitrary.arbitrary[app.AppAuthor].one(seed).value
  def appAuthor(): String = appAuthor(Seed.random())

  // Artist
  def artistName(seed: Seed): String =
    Arbitrary.arbitrary[artist.ArtistName].one(seed).value
  def artistName(): String = artistName(Seed.random())

  // Aqua Teen Hunger Force
  def aquaTeenHungerForceCharacter(seed: Seed): String =
    Arbitrary.arbitrary[aquaTeenHungerForce.Character].one(seed).value
  def aquaTeenHungerForceCharacter(): String = aquaTeenHungerForceCharacter(
    Seed.random()
  )

  // Back To The Future
  def backToTheFutureCharacter(seed: Seed): String =
    Arbitrary.arbitrary[backToTheFuture.Character].one(seed).value
  def backToTheFutureCharacter(): String = backToTheFutureCharacter(
    Seed.random()
  )
  def backToTheFutureQuote(seed: Seed): String =
    Arbitrary.arbitrary[backToTheFuture.Quote].one(seed).value
  def backToTheFutureQuote(): String = backToTheFutureQuote(
    Seed.random()
  )
  def backToTheFutureDate(seed: Seed): Instant =
    Arbitrary.arbitrary[backToTheFuture.Date].one(seed).value
  def backToTheFutureDate(): Instant = backToTheFutureDate(
    Seed.random()
  )
}

object Faker {
  val default: Faker = new Faker(SupportedLocales.default)
  val ar: Faker = new Faker(SupportedLocales.ar)
  val bg: Faker = new Faker(SupportedLocales.bg)
  val by: Faker = new Faker(SupportedLocales.by)
  val ca: Faker = new Faker(SupportedLocales.ca)
  val ca_CAT: Faker = new Faker(SupportedLocales.ca_CAT)
  val cs_CZ: Faker = new Faker(SupportedLocales.cs_CZ)
  val da_DK: Faker = new Faker(SupportedLocales.da_DK)
  val de: Faker = new Faker(SupportedLocales.de)
  val de_AT: Faker = new Faker(SupportedLocales.de_AT)
  val de_CH: Faker = new Faker(SupportedLocales.de_CH)
  val ee: Faker = new Faker(SupportedLocales.ee)
  val en: Faker = new Faker(SupportedLocales.en)
  val en_US: Faker = new Faker(SupportedLocales.en_US)
  val en_CA: Faker = new Faker(SupportedLocales.en_CA)
  val en_GB: Faker = new Faker(SupportedLocales.en_GB)
  val en_IND: Faker = new Faker(SupportedLocales.en_IND)
  val en_AU: Faker = new Faker(SupportedLocales.en_AU)
  val en_MS: Faker = new Faker(SupportedLocales.en_MS)
  val en_NEP: Faker = new Faker(SupportedLocales.en_NEP)
  val en_NG: Faker = new Faker(SupportedLocales.en_NG)
  val en_NZ: Faker = new Faker(SupportedLocales.en_NZ)
  val en_PAK: Faker = new Faker(SupportedLocales.en_PAK)
  val en_SG: Faker = new Faker(SupportedLocales.en_SG)
  val en_UG: Faker = new Faker(SupportedLocales.en_UG)
  val en_ZA: Faker = new Faker(SupportedLocales.en_ZA)
  val es: Faker = new Faker(SupportedLocales.es)
  val es_MX: Faker = new Faker(SupportedLocales.es_MX)
  val fa: Faker = new Faker(SupportedLocales.fa)
  val fi_FI: Faker = new Faker(SupportedLocales.fi_FI)
  val fr: Faker = new Faker(SupportedLocales.fr)
  val fr_CA: Faker = new Faker(SupportedLocales.fr_CA)
  val fr_CH: Faker = new Faker(SupportedLocales.fr_CH)
  val he: Faker = new Faker(SupportedLocales.he)
  val hu: Faker = new Faker(SupportedLocales.hu)
  val hy: Faker = new Faker(SupportedLocales.hy)
  val id: Faker = new Faker(SupportedLocales.id)
  val in_ID: Faker = new Faker(SupportedLocales.in_ID)
  val it: Faker = new Faker(SupportedLocales.it)
  val ja: Faker = new Faker(SupportedLocales.ja)
  val ko: Faker = new Faker(SupportedLocales.ko)
  val lv: Faker = new Faker(SupportedLocales.lv)
  val nb_NO: Faker = new Faker(SupportedLocales.nb_NO)
  val nl: Faker = new Faker(SupportedLocales.nl)
  val pl: Faker = new Faker(SupportedLocales.pl)
  val pt: Faker = new Faker(SupportedLocales.pt)
  val pt_BR: Faker = new Faker(SupportedLocales.pt_BR)
  val ru: Faker = new Faker(SupportedLocales.ru)
  val sk: Faker = new Faker(SupportedLocales.sk)
  val sv: Faker = new Faker(SupportedLocales.sv)
  val th: Faker = new Faker(SupportedLocales.th)
  val tr: Faker = new Faker(SupportedLocales.tr)
  val uk: Faker = new Faker(SupportedLocales.uk)
  val vi: Faker = new Faker(SupportedLocales.vi)
  val zh_CN: Faker = new Faker(SupportedLocales.zh_CN)
  val zh_TW: Faker = new Faker(SupportedLocales.zh_TW)

  val all = List(
    default,
    ar,
    bg,
    by,
    ca,
    ca_CAT,
    cs_CZ,
    da_DK,
    de,
    de_AT,
    de_CH,
    ee,
    en,
    en_US,
    en_CA,
    en_GB,
    en_IND,
    en_AU,
    en_MS,
    en_NEP,
    en_NG,
    en_NZ,
    en_PAK,
    en_SG,
    en_UG,
    en_ZA,
    es,
    es_MX,
    fa,
    fi_FI,
    fr,
    fr_CA,
    fr_CH,
    he,
    hu,
    hy,
    id,
    in_ID,
    it,
    ja,
    ko,
    lv,
    nb_NO,
    nl,
    pl,
    pt,
    pt_BR,
    ru,
    sk,
    sv,
    th,
    tr,
    uk,
    vi,
    zh_CN,
    zh_TW
  )
}
