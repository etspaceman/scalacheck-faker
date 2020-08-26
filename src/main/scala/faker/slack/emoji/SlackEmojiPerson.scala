package faker.slack.emoji

import org.scalacheck.{Arbitrary, Gen}
import pureconfig.ConfigReader
import pureconfig.generic.semiauto._

import faker.ResourceLoader

final case class SlackEmojiPerson private (value: String) extends AnyVal

object SlackEmojiPerson {
  def slackEmojiPeople(implicit loader: ResourceLoader): Seq[SlackEmojiPerson] =
    SlackEmoji.slackEmojis
      .filter(_.category == "person")
      .map(x => SlackEmojiPerson(x.value))

  implicit def slackEmojiPersonArbitrary(implicit
      loader: ResourceLoader
  ): Arbitrary[SlackEmojiPerson] =
    Arbitrary(Gen.oneOf(slackEmojiPeople))

  implicit val slackEmojiPersonConfigReader: ConfigReader[SlackEmojiPerson] =
    deriveReader
}
