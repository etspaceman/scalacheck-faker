package faker.time

import java.time.Instant

import org.scalacheck.{Arbitrary, Gen}

final case class PastInstant private (value: Instant) extends AnyVal

object PastInstant {
  implicit val pastInstantArbitrary: Arbitrary[PastInstant] = Arbitrary(
    Arbitrary
      .arbitrary[NowInstant]
      .flatMap(now => Gen.choose(Instant.MIN.toEpochMilli, now.value.minusMillis(1L).toEpochMilli))
      .map(Instant.ofEpochMilli)
      .map(PastInstant.apply)
  )
}
