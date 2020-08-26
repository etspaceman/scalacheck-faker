package faker.slack.emoji

import org.scalacheck.{Arbitrary, Gen}
import pureconfig.ConfigReader
import pureconfig.generic.semiauto._

import faker.ResourceLoader

final case class SlackEmojiActivity private (value: String) extends AnyVal

object SlackEmojiActivity {
  def slackEmojiActivities(implicit
      loader: ResourceLoader
  ): Seq[SlackEmojiActivity] =
    SlackEmoji.slackEmojis
      .filter(_.category == "activity")
      .map(x => SlackEmojiActivity(x.value))

  implicit def slackEmojiActivityArbitrary(implicit
      loader: ResourceLoader
  ): Arbitrary[SlackEmojiActivity] =
    Arbitrary(Gen.oneOf(slackEmojiActivities))

  implicit val slackEmojiActivityConfigReader
      : ConfigReader[SlackEmojiActivity] =
    deriveReader
}
