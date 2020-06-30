package faker.time

import java.time.{LocalDateTime, ZoneId}

import org.scalacheck.Arbitrary

final case class CurrentEraLocalDateTime private (value: LocalDateTime)
    extends AnyVal

object CurrentEraLocalDateTime {
  implicit val currentEraLocalDateTimeArbitrary
      : Arbitrary[CurrentEraLocalDateTime] = Arbitrary(
    Arbitrary
      .arbitrary[CurrentEraInstant]
      .map(x =>
        CurrentEraLocalDateTime(
          LocalDateTime.ofInstant(x.value, ZoneId.systemDefault())
        )
      )
  )
}
