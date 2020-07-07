package faker

import java.time._
import java.util.Locale

import org.scalacheck.Arbitrary
import org.scalacheck.rng.Seed

import faker.syntax.scalacheck._

@SuppressWarnings(Array("DisableSyntax.defaultArgs"))
final class Faker(private[faker] val locale: Locale) {

  implicit val loader: ResourceLoader = new ResourceLoader(locale)

  // Lorem
  def loremCharacters(seed: Seed = Seed.random()): String =
    Arbitrary.arbitrary[lorem.LoremCharacters].one(seed).value
  def loremWord(seed: Seed = Seed.random()): String =
    Arbitrary.arbitrary[lorem.LoremWord].one(seed).value
  def loremWords(words: Int, seed: Seed = Seed.random()): String =
    Arbitrary
      .arbitrary[lorem.LoremWord]
      .take(words, seed)
      .map(_.value)
      .mkString(" ")
  def loremWords(seed: Seed): String =
    Arbitrary.arbitrary[lorem.LoremWords].one(seed).value
  def loremWords(): String = loremWords(Seed.random())
  def loremSentence(words: Int, seed: Seed = Seed.random()): String =
    loremWords(words, seed) + "."
  def loremSentence(seed: Seed): String =
    Arbitrary.arbitrary[lorem.LoremSentence].one(seed).value
  def loremSentence(): String = loremSentence(Seed.random())
  def loremParagraph(sentences: Int, seed: Seed = Seed.random()): String =
    Arbitrary
      .arbitrary[lorem.LoremSentence]
      .take(sentences, seed)
      .map(_.value)
      .mkString(" ")
  def loremParagraph(seed: Seed): String =
    Arbitrary.arbitrary[lorem.LoremParagraph].one(seed).value
  def loremParagraph(): String = loremParagraph(Seed.random())
  def loremParagraphs(paragraphs: Int, seed: Seed = Seed.random()): String =
    Arbitrary
      .arbitrary[lorem.LoremParagraph]
      .take(paragraphs, seed)
      .map(_.value)
      .mkString("\n")
  def loremParagraphs(): String = loremParagraphs(Seed.random())
  def loremParagraphs(seed: Seed): String =
    Arbitrary.arbitrary[lorem.LoremParagraphs].one(seed).value

  // Name
  def firstName(seed: Seed = Seed.random()): String =
    Arbitrary.arbitrary[name.FirstName].one(seed).value
  def fullName(seed: Seed = Seed.random()): String =
    Arbitrary.arbitrary[name.FullName].one(seed).value
  def fullNameWithMiddle(seed: Seed = Seed.random()): String =
    Arbitrary.arbitrary[name.FullNameWithMiddle].one(seed).value
  def lastName(seed: Seed = Seed.random()): String =
    Arbitrary.arbitrary[name.LastName].one(seed).value
  def prefix(seed: Seed = Seed.random()): String =
    Arbitrary.arbitrary[name.Prefix].one(seed).value
  def suffix(seed: Seed = Seed.random()): String =
    Arbitrary.arbitrary[name.Suffix].one(seed).value
  def title(seed: Seed = Seed.random()): String =
    Arbitrary.arbitrary[name.Title].one(seed).value
  def userName(seed: Seed = Seed.random()): String =
    Arbitrary.arbitrary[name.UserName].one(seed).value

  // Internet
  def avatar(seed: Seed = Seed.random()): String =
    Arbitrary.arbitrary[internet.Avatar].one(seed).value
  def domainName(seed: Seed = Seed.random()): String =
    Arbitrary.arbitrary[internet.DomainName].one(seed).value
  def domainSuffix(seed: Seed = Seed.random()): String =
    Arbitrary.arbitrary[internet.DomainSuffix].one(seed).value
  def domainWord(seed: Seed = Seed.random()): String =
    Arbitrary.arbitrary[internet.DomainWord].one(seed).value
  def emailAddress(seed: Seed = Seed.random()): String =
    Arbitrary.arbitrary[internet.EmailAddress].one(seed).value
  def image(seed: Seed = Seed.random()): String =
    Arbitrary.arbitrary[internet.Image].one(seed).value
  def ipV4Address(seed: Seed = Seed.random()): String =
    Arbitrary.arbitrary[internet.IpV4Address].one(seed).value
  def ipV4Cidr(seed: Seed = Seed.random()): String =
    Arbitrary.arbitrary[internet.IpV4Cidr].one(seed).value
  def ipV6Address(seed: Seed = Seed.random()): String =
    Arbitrary.arbitrary[internet.IpV6Address].one(seed).value
  def ipV6Cidr(seed: Seed = Seed.random()): String =
    Arbitrary.arbitrary[internet.IpV6Cidr].one(seed).value
  def macAddress(seed: Seed = Seed.random()): String =
    Arbitrary.arbitrary[internet.MacAddress].one(seed).value
  def password(seed: Seed = Seed.random()): String =
    Arbitrary.arbitrary[internet.Password].one(seed).value
  def privateIpV4Address(seed: Seed = Seed.random()): String =
    Arbitrary.arbitrary[internet.PrivateIpV4Address].one(seed).value
  def publicIpV4Address(seed: Seed = Seed.random()): String =
    Arbitrary.arbitrary[internet.PublicIpV4Address].one(seed).value
  def safeEmailAddress(seed: Seed = Seed.random()): String =
    Arbitrary.arbitrary[internet.SafeEmailAddress].one(seed).value
  def slug(seed: Seed = Seed.random()): String =
    Arbitrary.arbitrary[internet.Slug].one(seed).value
  def url(seed: Seed = Seed.random()): String =
    Arbitrary.arbitrary[internet.Url].one(seed).value
  def userAgent(seed: Seed = Seed.random()): String =
    Arbitrary.arbitrary[internet.UserAgent].one(seed).value

  // Time
  def currentEraInstant(seed: Seed = Seed.random()): Instant =
    Arbitrary.arbitrary[time.CurrentEraInstant].one(seed).value
  def currentEraLocalDateTime(seed: Seed = Seed.random()): LocalDateTime =
    Arbitrary.arbitrary[time.CurrentEraLocalDateTime].one(seed).value
  def currentEraOffsetDateTime(seed: Seed = Seed.random()): OffsetDateTime =
    Arbitrary.arbitrary[time.CurrentEraOffsetDateTime].one(seed).value
  def currentEraZonedDateTime(seed: Seed = Seed.random()): ZonedDateTime =
    Arbitrary.arbitrary[time.CurrentEraZonedDateTime].one(seed).value
  def futureInstant(seed: Seed = Seed.random()): Instant =
    Arbitrary.arbitrary[time.FutureInstant].one(seed).value
  def futureLocalDateTime(seed: Seed = Seed.random()): LocalDateTime =
    Arbitrary.arbitrary[time.FutureLocalDateTime].one(seed).value
  def futureOffsetDateTime(seed: Seed = Seed.random()): OffsetDateTime =
    Arbitrary.arbitrary[time.FutureOffsetDateTime].one(seed).value
  def futureZonedDateTime(seed: Seed = Seed.random()): ZonedDateTime =
    Arbitrary.arbitrary[time.FutureZonedDateTime].one(seed).value
  def nowInstant(seed: Seed = Seed.random()): Instant =
    Arbitrary.arbitrary[time.NowInstant].one(seed).value
  def nowLocalDateTime(seed: Seed = Seed.random()): LocalDateTime =
    Arbitrary.arbitrary[time.NowLocalDateTime].one(seed).value
  def nowOffsetDateTime(seed: Seed = Seed.random()): OffsetDateTime =
    Arbitrary.arbitrary[time.NowOffsetDateTime].one(seed).value
  def nowZonedDateTime(seed: Seed = Seed.random()): ZonedDateTime =
    Arbitrary.arbitrary[time.NowZonedDateTime].one(seed).value
  def pastInstant(seed: Seed = Seed.random()): Instant =
    Arbitrary.arbitrary[time.PastInstant].one(seed).value
  def pastLocalDateTime(seed: Seed = Seed.random()): LocalDateTime =
    Arbitrary.arbitrary[time.PastLocalDateTime].one(seed).value
  def pastOffsetDateTime(seed: Seed = Seed.random()): OffsetDateTime =
    Arbitrary.arbitrary[time.PastOffsetDateTime].one(seed).value
  def pastZonedDateTime(seed: Seed = Seed.random()): ZonedDateTime =
    Arbitrary.arbitrary[time.PastZonedDateTime].one(seed).value
  def randomInstant(seed: Seed = Seed.random()): Instant =
    Arbitrary.arbitrary[time.RandomInstant].one(seed).value
  def randomLocalDateTime(seed: Seed = Seed.random()): LocalDateTime =
    Arbitrary.arbitrary[time.RandomLocalDateTime].one(seed).value
  def randomOffsetDateTime(seed: Seed = Seed.random()): OffsetDateTime =
    Arbitrary.arbitrary[time.RandomOffsetDateTime].one(seed).value
  def randomZonedDateTime(seed: Seed = Seed.random()): ZonedDateTime =
    Arbitrary.arbitrary[time.RandomZonedDateTime].one(seed).value

  // Address
  def buildingNumber(seed: Seed = Seed.random()): String =
    Arbitrary.arbitrary[address.BuildingNumber].one(seed).value
  def city(seed: Seed = Seed.random()): String =
    Arbitrary.arbitrary[address.City].one(seed).value
  def cityPrefix(seed: Seed = Seed.random()): String =
    Arbitrary.arbitrary[address.CityPrefix].one(seed).value
  def citySuffix(seed: Seed = Seed.random()): String =
    Arbitrary.arbitrary[address.CitySuffix].one(seed).value
  def country(seed: Seed = Seed.random()): address.Country =
    Arbitrary.arbitrary[address.Country].one(seed)
  def countryCode(seed: Seed = Seed.random()): String = country(seed).code
  def countryName(seed: Seed = Seed.random()): String = country(seed).name
  def defaultCountry(seed: Seed = Seed.random()): address.DefaultCountry =
    Arbitrary.arbitrary[address.DefaultCountry].one(seed)
  def defaultCountryCode(seed: Seed = Seed.random()): String =
    defaultCountry(seed).code
  def defaultCountryName(seed: Seed = Seed.random()): String =
    defaultCountry(seed).name
  def fullAddress(seed: Seed = Seed.random()): String =
    Arbitrary.arbitrary[address.FullAddress].one(seed).value
  def latitude(seed: Seed = Seed.random()): String =
    Arbitrary.arbitrary[address.Latitude].one(seed).value
  def longitude(seed: Seed = Seed.random()): String =
    Arbitrary.arbitrary[address.Longitude].one(seed).value
  def postalCode(seed: Seed = Seed.random()): String =
    Arbitrary.arbitrary[address.PostalCode].one(seed).value
  def secondaryAddress(seed: Seed = Seed.random()): String =
    Arbitrary.arbitrary[address.SecondaryAddress].one(seed).value
  def state(seed: Seed = Seed.random()): address.State =
    Arbitrary.arbitrary[address.State].one(seed)
  def stateAbbr(seed: Seed = Seed.random()): String = state(seed).abbr
  def stateZip(seed: Seed = Seed.random()): String =
    state(seed).zipGen.one(seed)
  def streetAddress(seed: Seed = Seed.random()): String =
    Arbitrary.arbitrary[address.StreetAddress].one(seed).value
  def streetName(seed: Seed = Seed.random()): String =
    Arbitrary.arbitrary[address.StreetName].one(seed).value
  def streetPrefix(seed: Seed = Seed.random()): String =
    Arbitrary.arbitrary[address.StreetPrefix].one(seed).value
  def streetSuffix(seed: Seed = Seed.random()): String =
    Arbitrary.arbitrary[address.StreetSuffix].one(seed).value

  // Company
  def bs(seed: Seed = Seed.random()): String =
    Arbitrary.arbitrary[company.BS].one(seed).value
  def buzzWord(seed: Seed = Seed.random()): String =
    Arbitrary.arbitrary[company.BuzzWord].one(seed).value
  def catchPhrase(seed: Seed = Seed.random()): String =
    Arbitrary.arbitrary[company.CatchPhrase].one(seed).value
  def companyDomainName(seed: Seed = Seed.random()): String =
    Arbitrary.arbitrary[company.CompanyDomainName].one(seed).value
  def companyName(seed: Seed = Seed.random()): String =
    Arbitrary.arbitrary[company.CompanyName].one(seed).value
  def companySuffix(seed: Seed = Seed.random()): String =
    Arbitrary.arbitrary[company.CompanySuffix].one(seed).value
  def companyUrl(seed: Seed = Seed.random()): String =
    Arbitrary.arbitrary[company.CompanyUrl].one(seed).value
  def industry(seed: Seed = Seed.random()): String =
    Arbitrary.arbitrary[company.Industry].one(seed).value
  def logo(seed: Seed = Seed.random()): String =
    Arbitrary.arbitrary[company.Logo].one(seed).value
  def profession(seed: Seed = Seed.random()): String =
    Arbitrary.arbitrary[company.Profession].one(seed).value

  // Phone Number
  def phoneNumber(seed: Seed = Seed.random()): String =
    Arbitrary.arbitrary[phone.PhoneNumber].one(seed).value
  def cellPhoneNumber(seed: Seed = Seed.random()): String =
    Arbitrary.arbitrary[phone.CellPhoneNumber].one(seed).value
}

object Faker {
  val default = new Faker(Locale.getDefault)
  val en = new Faker(Locale.ENGLISH)
  val en_US = new Faker(Locale.US)
  val en_CA = new Faker(Locale.CANADA)
  val en_GB = new Faker(Locale.UK)
}
