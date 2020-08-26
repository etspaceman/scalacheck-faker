package faker.slack.emoji

import org.scalacheck.{Arbitrary, Gen}
import pureconfig.ConfigReader
import pureconfig.generic.semiauto._

import faker.ResourceLoader

final case class SlackEmojiFood private (value: String) extends AnyVal

object SlackEmojiFood {
  def slackEmojiFoods(implicit loader: ResourceLoader): Seq[SlackEmojiFood] =
    SlackEmoji.slackEmojis
      .filter(_.category == "food")
      .map(x => SlackEmojiFood(x.value))

  implicit def slackEmojiFoodArbitrary(implicit
      loader: ResourceLoader
  ): Arbitrary[SlackEmojiFood] =
    Arbitrary(Gen.oneOf(slackEmojiFoods))

  implicit val slackEmojiFoodConfigReader: ConfigReader[SlackEmojiFood] =
    deriveReader
}
