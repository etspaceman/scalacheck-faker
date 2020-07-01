package faker.internet

import faker.ResourceLoader
import org.scalacheck.{Arbitrary, Gen}

final case class UserAgent private (value: String) extends AnyVal

object UserAgent {
  private val userAgentTypes: Seq[String] = Seq(
    "aol",
    "chrome",
    "firefox",
    "internet-explorer",
    "netscape",
    "opera",
    "safari"
  )
  implicit val userAgentArbitrary: Arbitrary[UserAgent] = Arbitrary(
    for {
      tpe <- Gen.oneOf(userAgentTypes)
      agent <-
        Gen.oneOf(ResourceLoader.loadStringList(s"internet.user-agents.$tpe"))
    } yield UserAgent(agent)
  )
}
