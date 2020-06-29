package faker.time

import java.time.Instant

import org.scalacheck.{Arbitrary, Gen}

final case class FutureInstant private (value: Instant) extends AnyVal

object FutureInstant {
  implicit val futureInstantArbitrary: Arbitrary[FutureInstant] = Arbitrary(
    Arbitrary
      .arbitrary[NowInstant]
      .flatMap(now => Gen.choose(now.value.plusMillis(1).toEpochMilli, Instant.MAX.toEpochMilli))
      .map(Instant.ofEpochMilli)
      .map(FutureInstant.apply)
  )
}
