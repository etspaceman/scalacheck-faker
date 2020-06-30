package faker.internet

import faker.ResourceLoader
import org.scalacheck.{Arbitrary, Gen}

/**
  * Generates a random avatar url based on a collection of profile pictures of real people. All this avatar have been
  * authorized by its awesome users to be used on live websites (not just mockups). For more information, please
  * visit: http://uifaces.com/authorized
  *
  * @see <a href="http://uifaces.com/authorized">Authorized UI Faces</a>
  */
final case class Avatar private (value: String) extends AnyVal

object Avatar {
  private val avatars = ResourceLoader.loadStringList("internet.avatars")
  implicit val avatarArbitrary: Arbitrary[Avatar] = Arbitrary(
    Gen
      .oneOf(avatars)
      .map(x => Avatar(s"https://s3.amazonaws.com/uifaces/faces/twitter/$x"))
  )
}
