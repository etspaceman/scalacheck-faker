package faker.internet

import org.scalacheck.{Arbitrary, Gen}

import faker.ResourceLoader

final case class UserAgent private (value: String) extends AnyVal

object UserAgent {
  def userAgents(loader: ResourceLoader): Map[String, Seq[String]] =
    Seq(
      "aol",
      "chrome",
      "firefox",
      "internet-explorer",
      "netscape",
      "opera",
      "safari"
    ).map(x => x -> loader.loadStringList(s"internet.user-agents.$x")).toMap
  implicit def userAgentArbitrary(implicit
      loader: ResourceLoader
  ): Arbitrary[UserAgent] =
    Arbitrary(
      for {
        tpe <- Gen.oneOf(userAgents(loader).keys)
        agent <- Gen.oneOf(userAgents(loader)(tpe))
      } yield UserAgent(agent)
    )
}
