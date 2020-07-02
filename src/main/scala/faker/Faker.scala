package faker

import java.util.Locale

import org.scalacheck.Arbitrary

import faker.syntax.scalacheck._

final class Faker(private val locale: Locale) {

  implicit val loader: ResourceLoader = new ResourceLoader(locale)

  // Lorem
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

}
