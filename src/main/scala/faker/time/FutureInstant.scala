package faker.time

import java.time.Instant

import org.scalacheck.{Arbitrary, Gen}

final case class FutureInstant private (value: Instant) extends AnyVal

object FutureInstant {
  implicit val futureInstantArbitrary: Arbitrary[FutureInstant] = Arbitrary {
    for {
      now <- Arbitrary.arbitrary[NowInstant].map(_.value.plusMillis(1L))
      epochSecond <- Gen.choose(now.getEpochSecond, Instant.MAX.getEpochSecond)
      nanoAdjustment <- Gen.choose(now.getNano, Instant.MAX.getNano)
    } yield FutureInstant(Instant.ofEpochSecond(epochSecond, nanoAdjustment))
  }
}
