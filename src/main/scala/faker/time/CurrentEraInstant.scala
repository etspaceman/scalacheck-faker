package faker.time

import java.time.Instant
import java.time.temporal.ChronoUnit

import org.scalacheck.{Arbitrary, Gen}

final case class CurrentEraInstant private (value: Instant) extends AnyVal

object CurrentEraInstant {
  implicit val currentEraInstantArbitrary: Arbitrary[CurrentEraInstant] = Arbitrary(
    Gen
      .choose(-62167219200000L, 253402300799999L)
      .map(Instant.ofEpochMilli)
      .map(CurrentEraInstant.apply)
  )
}
