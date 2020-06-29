package faker.time

import java.time.{ZoneId, ZonedDateTime}

import org.scalacheck.Arbitrary

final case class FutureZonedDateTime private (value: ZonedDateTime) extends AnyVal

object FutureZonedDateTime {
  implicit val futureZonedDateTimeArbitrary: Arbitrary[FutureZonedDateTime] = Arbitrary(
    Arbitrary
      .arbitrary[FutureInstant]
      .map(x => FutureZonedDateTime(ZonedDateTime.ofInstant(x.value, ZoneId.systemDefault())))
  )
}
