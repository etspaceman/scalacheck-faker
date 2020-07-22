package faker

import java.time._
import java.util.Locale

import org.scalacheck.Arbitrary
import org.scalacheck.rng.Seed

import faker.syntax.scalacheck._

final class Faker(private[faker] val locale: Locale) {

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
  def state(seed: Seed): address.StateLike =
    Arbitrary.arbitrary[address.StateLike].one(seed)
  def state(): address.StateLike = state(Seed.random())
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
}

object Faker {
  // $COVERAGE-OFF$
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
  // $COVERAGE-ON$
}
