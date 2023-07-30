/*
 * Copyright (c) 2020 etspaceman
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of
 * this software and associated documentation files (the "Software"), to deal in
 * the Software without restriction, including without limitation the rights to
 * use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of
 * the Software, and to permit persons to whom the Software is furnished to do so,
 * subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
 * FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 * COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER
 * IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
 * CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

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
