package faker.internet

import org.scalacheck.{Arbitrary, Gen}

import faker.ResourceLoader

final case class UserAgent private (value: String) extends AnyVal

object UserAgent {
  def userAgents(loader: ResourceLoader): Map[String, Seq[String]] =
    loader.loadKey[Map[String, Seq[String]]](s"internet.user-agents")
  implicit def userAgentArbitrary(implicit
      loader: ResourceLoader
  ): Arbitrary[UserAgent] =
    Arbitrary(Gen.oneOf(userAgents(loader).values.flatten).map(UserAgent.apply))
}
