package faker.internet

import org.scalacheck.Arbitrary

final case class UserAgent private (value: String) extends AnyVal

object UserAgent {
  implicit val userAgentArbitrary: Arbitrary[UserAgent] = Arbitrary(
    ???
  )
}
