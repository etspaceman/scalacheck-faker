package faker.time

import java.time.{OffsetDateTime, ZoneId}

import org.scalacheck.Arbitrary

final case class FutureOffsetDateTime private (value: OffsetDateTime) extends AnyVal

object FutureOffsetDateTime {
  implicit val futureOffsetDateTimeArbitrary: Arbitrary[FutureOffsetDateTime] = Arbitrary(
    Arbitrary
      .arbitrary[FutureInstant]
      .map(x => FutureOffsetDateTime(OffsetDateTime.ofInstant(x.value, ZoneId.systemDefault())))
  )
}



