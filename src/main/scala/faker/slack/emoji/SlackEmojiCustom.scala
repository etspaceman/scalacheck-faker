package faker.slack.emoji

import org.scalacheck.{Arbitrary, Gen}
import pureconfig.ConfigReader
import pureconfig.generic.semiauto._

import faker.ResourceLoader

final case class SlackEmojiCustom private (value: String) extends AnyVal

object SlackEmojiCustom {
  def slackEmojiCustom(implicit loader: ResourceLoader): Seq[SlackEmojiCustom] =
    SlackEmoji.slackEmojis
      .filter(_.category == "custom")
      .map(x => SlackEmojiCustom(x.value))

  implicit def slackEmojiCustomArbitrary(implicit
      loader: ResourceLoader
  ): Arbitrary[SlackEmojiCustom] =
    Arbitrary(Gen.oneOf(slackEmojiCustom))

  implicit val slackEmojiCustomConfigReader: ConfigReader[SlackEmojiCustom] =
    deriveReader
}
