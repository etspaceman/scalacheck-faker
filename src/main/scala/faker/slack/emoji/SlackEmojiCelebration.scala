package faker.slack.emoji

import org.scalacheck.{Arbitrary, Gen}
import pureconfig.ConfigReader
import pureconfig.generic.semiauto._

import faker.ResourceLoader

final case class SlackEmojiCelebration private (value: String) extends AnyVal

object SlackEmojiCelebration {
  def slackEmojiCelebrations(implicit
      loader: ResourceLoader
  ): Seq[SlackEmojiCelebration] =
    SlackEmoji.slackEmojis
      .filter(_.category == "celebration")
      .map(x => SlackEmojiCelebration(x.value))

  implicit def slackEmojiCelebrationArbitrary(implicit
      loader: ResourceLoader
  ): Arbitrary[SlackEmojiCelebration] =
    Arbitrary(Gen.oneOf(slackEmojiCelebrations))

  implicit val slackEmojiCelebrationConfigReader
      : ConfigReader[SlackEmojiCelebration] =
    deriveReader
}
