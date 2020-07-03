package faker

import java.time._
import java.util.Locale

import org.scalacheck.Arbitrary

import faker.syntax.scalacheck._

final class Faker(private val locale: Locale) {

  implicit val loader: ResourceLoader = new ResourceLoader(locale)

  // Lorem
  def loremCharacters(): String =
    Arbitrary.arbitrary[lorem.LoremCharacters].one.value
  def loremWord(): String = Arbitrary.arbitrary[lorem.LoremWord].one.value
  def loremWords(words: Int): String =
    Arbitrary.arbitrary[lorem.LoremWord].take(words).map(_.value).mkString(" ")
  def loremWords(): String = Arbitrary.arbitrary[lorem.LoremWords].one.value
  def loremSentence(words: Int): String = loremWords(words) + "."
  def loremSentence(): String =
    Arbitrary.arbitrary[lorem.LoremSentence].one.value
  def loremParagraph(sentences: Int): String =
    Arbitrary
      .arbitrary[lorem.LoremSentence]
      .take(sentences)
      .map(_.value)
      .mkString(" ")
  def loremParagraph(): String =
    Arbitrary.arbitrary[lorem.LoremParagraph].one.value
  def loremParagraphs(paragraphs: Int): String =
    Arbitrary
      .arbitrary[lorem.LoremParagraph]
      .take(paragraphs)
      .map(_.value)
      .mkString("\n")
  def loremParagraphs(): String =
    Arbitrary.arbitrary[lorem.LoremParagraphs].one.value

  // Name
  def firstName(): String = Arbitrary.arbitrary[name.FirstName].one.value
  def fullName(): String = Arbitrary.arbitrary[name.FullName].one.value
  def fullNameWithMiddle(): String =
    Arbitrary.arbitrary[name.FullNameWithMiddle].one.value
  def lastName(): String = Arbitrary.arbitrary[name.LastName].one.value
  def prefix(): String = Arbitrary.arbitrary[name.Prefix].one.value
  def suffix(): String = Arbitrary.arbitrary[name.Suffix].one.value
  def title(): String = Arbitrary.arbitrary[name.Title].one.value
  def userName(): String = Arbitrary.arbitrary[name.UserName].one.value

  // Internet
  def avatar(): String = Arbitrary.arbitrary[internet.Avatar].one.value
  def domainName(): String = Arbitrary.arbitrary[internet.DomainName].one.value
  def domainSuffix(): String =
    Arbitrary.arbitrary[internet.DomainSuffix].one.value
  def domainWord(): String = Arbitrary.arbitrary[internet.DomainWord].one.value
  def emailAddress(): String =
    Arbitrary.arbitrary[internet.EmailAddress].one.value
  def image(): String = Arbitrary.arbitrary[internet.Image].one.value
  def ipV4Address(): String =
    Arbitrary.arbitrary[internet.IpV4Address].one.value
  def ipV4Cidr(): String = Arbitrary.arbitrary[internet.IpV4Cidr].one.value
  def ipV6Address(): String =
    Arbitrary.arbitrary[internet.IpV6Address].one.value
  def ipV6Cidr(): String = Arbitrary.arbitrary[internet.IpV6Cidr].one.value
  def macAddress(): String = Arbitrary.arbitrary[internet.MacAddress].one.value
  def password(): String = Arbitrary.arbitrary[internet.Password].one.value
  def privateIpV4Address(): String =
    Arbitrary.arbitrary[internet.PrivateIpV4Address].one.value
  def publicIpV4Address(): String =
    Arbitrary.arbitrary[internet.PublicIpV4Address].one.value
  def safeEmailAddress(): String =
    Arbitrary.arbitrary[internet.SafeEmailAddress].one.value
  def slug(): String = Arbitrary.arbitrary[internet.Slug].one.value
  def url(): String = Arbitrary.arbitrary[internet.Url].one.value
  def userAgent(): String = Arbitrary.arbitrary[internet.UserAgent].one.value

  // Time
  def currentEraInstant(): Instant =
    Arbitrary.arbitrary[time.CurrentEraInstant].one.value
  def currentEraLocalDateTime(): LocalDateTime =
    Arbitrary.arbitrary[time.CurrentEraLocalDateTime].one.value
  def currentEraOffsetDateTime(): OffsetDateTime =
    Arbitrary.arbitrary[time.CurrentEraOffsetDateTime].one.value
  def currentEraZonedDateTime(): ZonedDateTime =
    Arbitrary.arbitrary[time.CurrentEraZonedDateTime].one.value
  def futureInstant(): Instant =
    Arbitrary.arbitrary[time.FutureInstant].one.value
  def futureLocalDateTime(): LocalDateTime =
    Arbitrary.arbitrary[time.FutureLocalDateTime].one.value
  def futureOffsetDateTime(): OffsetDateTime =
    Arbitrary.arbitrary[time.FutureOffsetDateTime].one.value
  def futureZonedDateTime(): ZonedDateTime =
    Arbitrary.arbitrary[time.FutureZonedDateTime].one.value
  def nowInstant(): Instant =
    Arbitrary.arbitrary[time.NowInstant].one.value
  def nowLocalDateTime(): LocalDateTime =
    Arbitrary.arbitrary[time.NowLocalDateTime].one.value
  def nowOffsetDateTime(): OffsetDateTime =
    Arbitrary.arbitrary[time.NowOffsetDateTime].one.value
  def nowZonedDateTime(): ZonedDateTime =
    Arbitrary.arbitrary[time.NowZonedDateTime].one.value
  def pastInstant(): Instant =
    Arbitrary.arbitrary[time.PastInstant].one.value
  def pastLocalDateTime(): LocalDateTime =
    Arbitrary.arbitrary[time.PastLocalDateTime].one.value
  def pastOffsetDateTime(): OffsetDateTime =
    Arbitrary.arbitrary[time.PastOffsetDateTime].one.value
  def pastZonedDateTime(): ZonedDateTime =
    Arbitrary.arbitrary[time.PastZonedDateTime].one.value
  def randomInstant(): Instant =
    Arbitrary.arbitrary[time.RandomInstant].one.value
  def randomLocalDateTime(): LocalDateTime =
    Arbitrary.arbitrary[time.RandomLocalDateTime].one.value
  def randomOffsetDateTime(): OffsetDateTime =
    Arbitrary.arbitrary[time.RandomOffsetDateTime].one.value
  def randomZonedDateTime(): ZonedDateTime =
    Arbitrary.arbitrary[time.RandomZonedDateTime].one.value

}

object Faker {
  val default = new Faker(Locale.getDefault)
}
