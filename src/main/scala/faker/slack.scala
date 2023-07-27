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

import org.scalacheck.{Arbitrary, Gen}
import pureconfig.ConfigReader

object slack {
  object emoji {
    final case class SlackEmoji private (value: String, category: String)

    object SlackEmoji {
      def slackEmojis(implicit loader: ResourceLoader): Seq[SlackEmoji] =
        loader.loadKey[Seq[SlackEmoji]]("slack.emojis")

      implicit def slackEmojiArbitrary(implicit
          loader: ResourceLoader
      ): Arbitrary[SlackEmoji] =
        Arbitrary(Gen.oneOf(slackEmojis))

      implicit val slackEmojiConfigReader: ConfigReader[SlackEmoji] =
        ConfigReader.forProduct2("value", "category")(SlackEmoji(_, _))
    }

    type SlackEmojiActivity = SlackEmojiActivity.Type

    object SlackEmojiActivity extends Newtype[String] { self =>
      def slackEmojiActivities(implicit
          loader: ResourceLoader
      ): Seq[SlackEmojiActivity] =
        SlackEmoji.slackEmojis
          .filter(_.category == "activity")
          .map(x => x.value)
          .map(self.apply)

      implicit def slackEmojiActivityArbitrary(implicit
          loader: ResourceLoader
      ): Arbitrary[SlackEmojiActivity] =
        Arbitrary(Gen.oneOf(slackEmojiActivities))

      implicit val slackEmojiActivityConfigReader
          : ConfigReader[SlackEmojiActivity] =
        ConfigReader[String].map(self.apply)
    }

    type SlackEmojiCelebration = SlackEmojiCelebration.Type

    object SlackEmojiCelebration extends Newtype[String] { self =>
      def slackEmojiCelebrations(implicit
          loader: ResourceLoader
      ): Seq[SlackEmojiCelebration] =
        SlackEmoji.slackEmojis
          .filter(_.category == "celebration")
          .map(x => x.value)
          .map(self.apply)

      implicit def slackEmojiCelebrationArbitrary(implicit
          loader: ResourceLoader
      ): Arbitrary[SlackEmojiCelebration] =
        Arbitrary(Gen.oneOf(slackEmojiCelebrations))

      implicit val slackEmojiCelebrationConfigReader
          : ConfigReader[SlackEmojiCelebration] =
        ConfigReader[String].map(self.apply)
    }

    type SlackEmojiCustom = SlackEmojiCustom.Type

    object SlackEmojiCustom extends Newtype[String] { self =>
      def slackEmojiCustom(implicit
          loader: ResourceLoader
      ): Seq[SlackEmojiCustom] =
        SlackEmoji.slackEmojis
          .filter(_.category == "custom")
          .map(x => x.value)
          .map(self.apply)

      implicit def slackEmojiCustomArbitrary(implicit
          loader: ResourceLoader
      ): Arbitrary[SlackEmojiCustom] =
        Arbitrary(Gen.oneOf(slackEmojiCustom))

      implicit val slackEmojiCustomConfigReader
          : ConfigReader[SlackEmojiCustom] =
        ConfigReader[String].map(self.apply)
    }

    type SlackEmojiFood = SlackEmojiFood.Type

    object SlackEmojiFood extends Newtype[String] { self =>
      def slackEmojiFoods(implicit
          loader: ResourceLoader
      ): Seq[SlackEmojiFood] =
        SlackEmoji.slackEmojis
          .filter(_.category == "food")
          .map(x => x.value)
          .map(self.apply)

      implicit def slackEmojiFoodArbitrary(implicit
          loader: ResourceLoader
      ): Arbitrary[SlackEmojiFood] =
        Arbitrary(Gen.oneOf(slackEmojiFoods))

      implicit val slackEmojiFoodConfigReader: ConfigReader[SlackEmojiFood] =
        ConfigReader[String].map(self.apply)
    }

    type SlackEmojiNature = SlackEmojiNature.Type

    object SlackEmojiNature extends Newtype[String] { self =>
      def slackEmojiNature(implicit
          loader: ResourceLoader
      ): Seq[SlackEmojiNature] =
        SlackEmoji.slackEmojis
          .filter(_.category == "nature")
          .map(x => x.value)
          .map(self.apply)

      implicit def slackEmojiNatureArbitrary(implicit
          loader: ResourceLoader
      ): Arbitrary[SlackEmojiNature] =
        Arbitrary(Gen.oneOf(slackEmojiNature))

      implicit val slackEmojiNatureConfigReader
          : ConfigReader[SlackEmojiNature] =
        ConfigReader[String].map(self.apply)
    }

    type SlackEmojiObject = SlackEmojiObject.Type

    object SlackEmojiObject extends Newtype[String] { self =>
      def slackEmojiObjects(implicit
          loader: ResourceLoader
      ): Seq[SlackEmojiObject] =
        SlackEmoji.slackEmojis
          .filter(_.category == "object")
          .map(x => x.value)
          .map(self.apply)

      implicit def slackEmojiObjectArbitrary(implicit
          loader: ResourceLoader
      ): Arbitrary[SlackEmojiObject] =
        Arbitrary(Gen.oneOf(slackEmojiObjects))

      implicit val slackEmojiObjectConfigReader
          : ConfigReader[SlackEmojiObject] =
        ConfigReader[String].map(self.apply)
    }

    type SlackEmojiPerson = SlackEmojiPerson.Type

    object SlackEmojiPerson extends Newtype[String] { self =>
      def slackEmojiPeople(implicit
          loader: ResourceLoader
      ): Seq[SlackEmojiPerson] =
        SlackEmoji.slackEmojis
          .filter(_.category == "person")
          .map(x => x.value)
          .map(self.apply)

      implicit def slackEmojiPersonArbitrary(implicit
          loader: ResourceLoader
      ): Arbitrary[SlackEmojiPerson] =
        Arbitrary(Gen.oneOf(slackEmojiPeople))

      implicit val slackEmojiPersonConfigReader
          : ConfigReader[SlackEmojiPerson] =
        ConfigReader[String].map(self.apply)
    }

    type SlackEmojiTravel = SlackEmojiTravel.Type

    object SlackEmojiTravel extends Newtype[String] { self =>
      def slackEmojiTravels(implicit
          loader: ResourceLoader
      ): Seq[SlackEmojiTravel] =
        SlackEmoji.slackEmojis
          .filter(_.category == "travel")
          .map(x => x.value)
          .map(self.apply)

      implicit def slackEmojiTravelArbitrary(implicit
          loader: ResourceLoader
      ): Arbitrary[SlackEmojiTravel] =
        Arbitrary(Gen.oneOf(slackEmojiTravels))

      implicit val slackEmojiTravelConfigReader
          : ConfigReader[SlackEmojiTravel] =
        ConfigReader[String].map(self.apply)
    }
  }
}
