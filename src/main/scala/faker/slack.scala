package faker

import io.estatico.newtype.macros.newtype
import io.estatico.newtype.ops.toCoercibleIdOps
import org.scalacheck.{Arbitrary, Gen}
import pureconfig.ConfigReader
import pureconfig.generic.semiauto._

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
        deriveReader
    }

    @newtype final case class SlackEmojiActivity private (value: String)

    object SlackEmojiActivity {
      def slackEmojiActivities(implicit
          loader: ResourceLoader
      ): Seq[SlackEmojiActivity] =
        SlackEmoji.slackEmojis
          .filter(_.category == "activity")
          .map(x => x.value.coerce)

      implicit def slackEmojiActivityArbitrary(implicit
          loader: ResourceLoader
      ): Arbitrary[SlackEmojiActivity] =
        Arbitrary(Gen.oneOf(slackEmojiActivities))

      implicit val slackEmojiActivityConfigReader
          : ConfigReader[SlackEmojiActivity] =
        ConfigReader[String].coerce
    }

    @newtype final case class SlackEmojiCelebration private (value: String)

    object SlackEmojiCelebration {
      def slackEmojiCelebrations(implicit
          loader: ResourceLoader
      ): Seq[SlackEmojiCelebration] =
        SlackEmoji.slackEmojis
          .filter(_.category == "celebration")
          .map(x => x.value.coerce)

      implicit def slackEmojiCelebrationArbitrary(implicit
          loader: ResourceLoader
      ): Arbitrary[SlackEmojiCelebration] =
        Arbitrary(Gen.oneOf(slackEmojiCelebrations))

      implicit val slackEmojiCelebrationConfigReader
          : ConfigReader[SlackEmojiCelebration] =
        ConfigReader[String].coerce
    }

    @newtype final case class SlackEmojiCustom private (value: String)

    object SlackEmojiCustom {
      def slackEmojiCustom(implicit
          loader: ResourceLoader
      ): Seq[SlackEmojiCustom] =
        SlackEmoji.slackEmojis
          .filter(_.category == "custom")
          .map(x => x.value.coerce)

      implicit def slackEmojiCustomArbitrary(implicit
          loader: ResourceLoader
      ): Arbitrary[SlackEmojiCustom] =
        Arbitrary(Gen.oneOf(slackEmojiCustom))

      implicit val slackEmojiCustomConfigReader
          : ConfigReader[SlackEmojiCustom] =
        ConfigReader[String].coerce
    }

    @newtype final case class SlackEmojiFood private (value: String)

    object SlackEmojiFood {
      def slackEmojiFoods(implicit
          loader: ResourceLoader
      ): Seq[SlackEmojiFood] =
        SlackEmoji.slackEmojis
          .filter(_.category == "food")
          .map(x => x.value.coerce)

      implicit def slackEmojiFoodArbitrary(implicit
          loader: ResourceLoader
      ): Arbitrary[SlackEmojiFood] =
        Arbitrary(Gen.oneOf(slackEmojiFoods))

      implicit val slackEmojiFoodConfigReader: ConfigReader[SlackEmojiFood] =
        ConfigReader[String].coerce
    }

    @newtype final case class SlackEmojiNature private (value: String)

    object SlackEmojiNature {
      def slackEmojiNature(implicit
          loader: ResourceLoader
      ): Seq[SlackEmojiNature] =
        SlackEmoji.slackEmojis
          .filter(_.category == "nature")
          .map(x => x.value.coerce)

      implicit def slackEmojiNatureArbitrary(implicit
          loader: ResourceLoader
      ): Arbitrary[SlackEmojiNature] =
        Arbitrary(Gen.oneOf(slackEmojiNature))

      implicit val slackEmojiNatureConfigReader
          : ConfigReader[SlackEmojiNature] =
        ConfigReader[String].coerce
    }

    @newtype final case class SlackEmojiObject private (value: String)

    object SlackEmojiObject {
      def slackEmojiObjects(implicit
          loader: ResourceLoader
      ): Seq[SlackEmojiObject] =
        SlackEmoji.slackEmojis
          .filter(_.category == "object")
          .map(x => x.value.coerce)

      implicit def slackEmojiObjectArbitrary(implicit
          loader: ResourceLoader
      ): Arbitrary[SlackEmojiObject] =
        Arbitrary(Gen.oneOf(slackEmojiObjects))

      implicit val slackEmojiObjectConfigReader
          : ConfigReader[SlackEmojiObject] =
        ConfigReader[String].coerce
    }

    @newtype final case class SlackEmojiPerson private (value: String)

    object SlackEmojiPerson {
      def slackEmojiPeople(implicit
          loader: ResourceLoader
      ): Seq[SlackEmojiPerson] =
        SlackEmoji.slackEmojis
          .filter(_.category == "person")
          .map(x => x.value.coerce)

      implicit def slackEmojiPersonArbitrary(implicit
          loader: ResourceLoader
      ): Arbitrary[SlackEmojiPerson] =
        Arbitrary(Gen.oneOf(slackEmojiPeople))

      implicit val slackEmojiPersonConfigReader
          : ConfigReader[SlackEmojiPerson] =
        ConfigReader[String].coerce
    }

    @newtype final case class SlackEmojiTravel private (value: String)

    object SlackEmojiTravel {
      def slackEmojiTravels(implicit
          loader: ResourceLoader
      ): Seq[SlackEmojiTravel] =
        SlackEmoji.slackEmojis
          .filter(_.category == "travel")
          .map(x => x.value.coerce)

      implicit def slackEmojiTravelArbitrary(implicit
          loader: ResourceLoader
      ): Arbitrary[SlackEmojiTravel] =
        Arbitrary(Gen.oneOf(slackEmojiTravels))

      implicit val slackEmojiTravelConfigReader
          : ConfigReader[SlackEmojiTravel] =
        ConfigReader[String].coerce
    }
  }
}
