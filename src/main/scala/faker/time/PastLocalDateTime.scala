package faker.time

import java.time.{LocalDateTime, ZoneId}

import org.scalacheck.Arbitrary

final case class PastLocalDateTime private (value: LocalDateTime) extends AnyVal

object PastLocalDateTime {
  implicit val pastLocalDateTimeArbitrary: Arbitrary[PastLocalDateTime] =
    Arbitrary(
      Arbitrary
        .arbitrary[PastInstant]
        .map(x =>
          PastLocalDateTime(
            LocalDateTime.ofInstant(x.value, ZoneId.systemDefault())
          )
        )
    )
}
