package faker

import cats.syntax.all._

object SlackSpec extends FakerSpec {

  doTest[slack.emoji.SlackEmoji, String](
    "slack.emoji.SlackEmoji",
    _.slackEmoji(),
    faker => {
      implicit val resourceLoader: ResourceLoader = faker.loader
      implicitly
    },
    _.nonEmpty,
    _.value
  )

  doTest[slack.emoji.SlackEmojiActivity, String](
    "slack.emoji.SlackEmojiActivity",
    _.slackEmojiActivity(),
    faker => {
      implicit val resourceLoader: ResourceLoader = faker.loader
      implicitly
    },
    _.nonEmpty,
    _.value
  )

  doTest[slack.emoji.SlackEmojiCelebration, String](
    "slack.emoji.SlackEmojiCelebration",
    _.slackEmojiCelebration(),
    faker => {
      implicit val resourceLoader: ResourceLoader = faker.loader
      implicitly
    },
    _.nonEmpty,
    _.value
  )

  doTest[slack.emoji.SlackEmojiCustom, String](
    "slack.emoji.SlackEmojiCustom",
    _.slackEmojiCustom(),
    faker => {
      implicit val resourceLoader: ResourceLoader = faker.loader
      implicitly
    },
    _.nonEmpty,
    _.value
  )

  doTest[slack.emoji.SlackEmojiFood, String](
    "slack.emoji.SlackEmojiFood",
    _.slackEmojiFood(),
    faker => {
      implicit val resourceLoader: ResourceLoader = faker.loader
      implicitly
    },
    _.nonEmpty,
    _.value
  )

  doTest[slack.emoji.SlackEmojiNature, String](
    "slack.emoji.SlackEmojiNature",
    _.slackEmojiNature(),
    faker => {
      implicit val resourceLoader: ResourceLoader = faker.loader
      implicitly
    },
    _.nonEmpty,
    _.value
  )

  doTest[slack.emoji.SlackEmojiObject, String](
    "slack.emoji.SlackEmojiObject",
    _.slackEmojiObject(),
    faker => {
      implicit val resourceLoader: ResourceLoader = faker.loader
      implicitly
    },
    _.nonEmpty,
    _.value
  )

  doTest[slack.emoji.SlackEmojiPerson, String](
    "slack.emoji.SlackEmojiPerson",
    _.slackEmojiPerson(),
    faker => {
      implicit val resourceLoader: ResourceLoader = faker.loader
      implicitly
    },
    _.nonEmpty,
    _.value
  )

  doTest[slack.emoji.SlackEmojiTravel, String](
    "slack.emoji.SlackEmojiTravel",
    _.slackEmojiTravel(),
    faker => {
      implicit val resourceLoader: ResourceLoader = faker.loader
      implicitly
    },
    _.nonEmpty,
    _.value
  )

}
