package faker.time

import java.time.{ZoneId, ZonedDateTime}

import org.scalacheck.Arbitrary

final case class RandomZonedDateTime private (value: ZonedDateTime) extends AnyVal

object RandomZonedDateTime {
  implicit val randomZonedDateTimeArbitrary: Arbitrary[RandomZonedDateTime] = Arbitrary(
    Arbitrary
      .arbitrary[RandomInstant]
      .map(x => RandomZonedDateTime(ZonedDateTime.ofInstant(x.value, ZoneId.systemDefault())))
  )
}
