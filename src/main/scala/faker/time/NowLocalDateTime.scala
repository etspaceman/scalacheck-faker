package faker.time

import java.time.LocalDateTime

import org.scalacheck.{Arbitrary, Gen}

final case class NowLocalDateTime private (value: LocalDateTime) extends AnyVal

object NowLocalDateTime {
  implicit val nowLocalDateTimeArbitrary: Arbitrary[NowLocalDateTime] =
    Arbitrary(
      Gen.delay(LocalDateTime.now()).map(NowLocalDateTime.apply)
    )
}
