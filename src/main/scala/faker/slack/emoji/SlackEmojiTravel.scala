package faker.slack.emoji

import org.scalacheck.{Arbitrary, Gen}
import pureconfig.ConfigReader
import pureconfig.generic.semiauto._

import faker.ResourceLoader

final case class SlackEmojiTravel private (value: String) extends AnyVal

object SlackEmojiTravel {
  def slackEmojiTravels(implicit
      loader: ResourceLoader
  ): Seq[SlackEmojiTravel] =
    SlackEmoji.slackEmojis
      .filter(_.category == "travel")
      .map(x => SlackEmojiTravel(x.value))

  implicit def slackEmojiTravelArbitrary(implicit
      loader: ResourceLoader
  ): Arbitrary[SlackEmojiTravel] =
    Arbitrary(Gen.oneOf(slackEmojiTravels))

  implicit val slackEmojiTravelConfigReader: ConfigReader[SlackEmojiTravel] =
    deriveReader
}
