package faker.time

import java.time.{OffsetDateTime, ZoneId}

import org.scalacheck.Arbitrary

final case class CurrentEraOffsetDateTime private (value: OffsetDateTime) extends AnyVal

object CurrentEraOffsetDateTime {
  implicit val currentEraOffsetDateTimeArbitrary: Arbitrary[CurrentEraOffsetDateTime] = Arbitrary(
    Arbitrary
      .arbitrary[CurrentEraInstant]
      .map(x => CurrentEraOffsetDateTime(OffsetDateTime.ofInstant(x.value, ZoneId.systemDefault())))
  )
}
