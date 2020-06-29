package faker.time

import java.time.Instant

import org.scalacheck.{Arbitrary, Gen}

final case class RandomInstant private (value: Instant) extends AnyVal

object RandomInstant {
  implicit val randomInstantArbitrary: Arbitrary[RandomInstant] = Arbitrary {
    for {
      epochSecond <- Gen.choose(Instant.MIN.getEpochSecond, Instant.MAX.getEpochSecond)
      nanoAdjustment <- Gen.choose(Instant.MIN.getNano, Instant.MAX.getNano)
    } yield RandomInstant(Instant.ofEpochSecond(epochSecond, nanoAdjustment))
  }
}
