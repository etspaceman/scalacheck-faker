package faker.time

import java.time.{LocalDateTime, ZoneId}

import org.scalacheck.Arbitrary

final case class FutureLocalDateTime private (value: LocalDateTime) extends AnyVal

object FutureLocalDateTime {
  implicit val futureLocalDateTimeArbitrary: Arbitrary[FutureLocalDateTime] = Arbitrary(
    Arbitrary
      .arbitrary[FutureInstant]
      .map(x => FutureLocalDateTime(LocalDateTime.ofInstant(x.value, ZoneId.systemDefault())))
  )
}
