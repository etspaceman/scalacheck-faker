package faker.time

import java.time.{ZoneId, ZonedDateTime}

import org.scalacheck.Arbitrary

final case class PastZonedDateTime private (value: ZonedDateTime) extends AnyVal

object PastZonedDateTime {
  implicit val pastZonedDateTimeArbitrary: Arbitrary[PastZonedDateTime] = Arbitrary(
    Arbitrary
      .arbitrary[PastInstant]
      .map(x => PastZonedDateTime(ZonedDateTime.ofInstant(x.value, ZoneId.systemDefault())))
  )
}
