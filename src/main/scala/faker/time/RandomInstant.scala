package faker.time

import java.time.Instant

import org.scalacheck.{Arbitrary, Gen}

final case class RandomInstant private (value: Instant) extends AnyVal

object RandomInstant {
  implicit val randomInstantArbitrary: Arbitrary[RandomInstant] = Arbitrary(
    Gen
      .choose(Instant.MIN.toEpochMilli, Instant.MAX.toEpochMilli)
      .map(Instant.ofEpochMilli)
      .map(RandomInstant.apply)
  )
}
