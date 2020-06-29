package faker.time

import java.time.Instant

import org.scalacheck.{Arbitrary, Gen}

final case class NowInstant private (value: Instant) extends AnyVal

object NowInstant {
  implicit val nowInstantArbitrary: Arbitrary[NowInstant] = Arbitrary(
    Gen.delay(Instant.now()).map(NowInstant.apply)
  )
}
