package faker.internet

import org.scalacheck.{Arbitrary, Gen}

import faker.ResourceLoader

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
  implicit def userAgentArbitrary(implicit
      loader: ResourceLoader
  ): Arbitrary[UserAgent] =
    Arbitrary(
      for {
        tpe <- Gen.oneOf(userAgentTypes)
        agent <- Gen.oneOf(loader.loadStringList(s"internet.user-agents.$tpe"))
      } yield UserAgent(agent)
    )
}
