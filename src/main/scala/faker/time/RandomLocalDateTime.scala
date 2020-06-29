package faker.time

import java.time.{LocalDateTime, ZoneId}

import org.scalacheck.Arbitrary

final case class RandomLocalDateTime private (value: LocalDateTime) extends AnyVal

object RandomLocalDateTime {
  implicit val randomLocalDateTimeArbitrary: Arbitrary[RandomLocalDateTime] = Arbitrary(
    Arbitrary
      .arbitrary[RandomInstant]
      .map(x => RandomLocalDateTime(LocalDateTime.ofInstant(x.value, ZoneId.systemDefault())))
  )
}
