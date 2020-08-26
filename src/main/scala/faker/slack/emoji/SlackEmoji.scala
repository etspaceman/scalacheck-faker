package faker.slack.emoji

import org.scalacheck.{Arbitrary, Gen}
import pureconfig.ConfigReader
import pureconfig.generic.semiauto._

import faker.ResourceLoader

final case class SlackEmoji private (value: String, category: String)

object SlackEmoji {
  def slackEmojis(implicit loader: ResourceLoader): Seq[SlackEmoji] =
    loader.loadKey[Seq[SlackEmoji]]("slack.emojis")

  implicit def slackEmojiArbitrary(implicit
      loader: ResourceLoader
  ): Arbitrary[SlackEmoji] =
    Arbitrary(Gen.oneOf(slackEmojis))

  implicit val slackEmojiConfigReader: ConfigReader[SlackEmoji] =
    deriveReader
}
