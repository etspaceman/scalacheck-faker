package faker.time

import java.time.ZonedDateTime

import org.scalacheck.{Arbitrary, Gen}

final case class NowZonedDateTime private (value: ZonedDateTime) extends AnyVal

object NowZonedDateTime {
  implicit val nowZonedDateTimeArbitrary: Arbitrary[NowZonedDateTime] =
    Arbitrary(
      Gen.delay(ZonedDateTime.now()).map(NowZonedDateTime.apply)
    )
}
