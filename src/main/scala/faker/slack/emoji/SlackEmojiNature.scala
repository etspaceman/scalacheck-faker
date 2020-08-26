package faker.slack.emoji

import org.scalacheck.{Arbitrary, Gen}
import pureconfig.ConfigReader
import pureconfig.generic.semiauto._

import faker.ResourceLoader

final case class SlackEmojiNature private (value: String) extends AnyVal

object SlackEmojiNature {
  def slackEmojiNature(implicit loader: ResourceLoader): Seq[SlackEmojiNature] =
    SlackEmoji.slackEmojis
      .filter(_.category == "nature")
      .map(x => SlackEmojiNature(x.value))

  implicit def slackEmojiNatureArbitrary(implicit
      loader: ResourceLoader
  ): Arbitrary[SlackEmojiNature] =
    Arbitrary(Gen.oneOf(slackEmojiNature))

  implicit val slackEmojiNatureConfigReader: ConfigReader[SlackEmojiNature] =
    deriveReader
}
