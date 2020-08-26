package faker.slack.emoji

import org.scalacheck.{Arbitrary, Gen}
import pureconfig.ConfigReader
import pureconfig.generic.semiauto._

import faker.ResourceLoader

final case class SlackEmojiObject private (value: String) extends AnyVal

object SlackEmojiObject {
  def slackEmojiObjects(implicit
      loader: ResourceLoader
  ): Seq[SlackEmojiObject] =
    SlackEmoji.slackEmojis
      .filter(_.category == "object")
      .map(x => SlackEmojiObject(x.value))

  implicit def slackEmojiObjectArbitrary(implicit
      loader: ResourceLoader
  ): Arbitrary[SlackEmojiObject] =
    Arbitrary(Gen.oneOf(slackEmojiObjects))

  implicit val slackEmojiObjectConfigReader: ConfigReader[SlackEmojiObject] =
    deriveReader
}
