package faker.time

import java.time.{OffsetDateTime, ZoneId}

import org.scalacheck.Arbitrary

final case class PastOffsetDateTime private (value: OffsetDateTime) extends AnyVal

object PastOffsetDateTime {
  implicit val pastOffsetDateTimeArbitrary: Arbitrary[PastOffsetDateTime] = Arbitrary(
    Arbitrary
      .arbitrary[PastInstant]
      .map(x => PastOffsetDateTime(OffsetDateTime.ofInstant(x.value, ZoneId.systemDefault())))
  )
}



