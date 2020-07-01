package faker.time

import java.time.Instant

import org.scalacheck.{Arbitrary, Gen}

final case class PastInstant private (value: Instant) extends AnyVal

object PastInstant {
  implicit val pastInstantArbitrary: Arbitrary[PastInstant] = Arbitrary {
    for {
      now <- Arbitrary.arbitrary[NowInstant].map(_.value)
      epochSecond <- Gen.choose(Instant.MIN.getEpochSecond, now.getEpochSecond)
      nanoAdjustment <- Gen.choose(Instant.MIN.getNano, now.getNano)
    } yield PastInstant(
      Instant.ofEpochSecond(epochSecond, nanoAdjustment.toLong)
    )
  }
}
