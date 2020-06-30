package faker.time

import java.time.{ZoneId, ZonedDateTime}

import org.scalacheck.Arbitrary

final case class CurrentEraZonedDateTime private (value: ZonedDateTime)
    extends AnyVal

object CurrentEraZonedDateTime {
  implicit val currentEraZonedDateTimeArbitrary
      : Arbitrary[CurrentEraZonedDateTime] = Arbitrary(
    Arbitrary
      .arbitrary[CurrentEraInstant]
      .map(x =>
        CurrentEraZonedDateTime(
          ZonedDateTime.ofInstant(x.value, ZoneId.systemDefault())
        )
      )
  )
}
