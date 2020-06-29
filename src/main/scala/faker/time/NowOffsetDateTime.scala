package faker.time

import java.time.OffsetDateTime

import org.scalacheck.{Arbitrary, Gen}

final case class NowOffsetDateTime private (value: OffsetDateTime) extends AnyVal

object NowOffsetDateTime {
  implicit val nowOffsetTimeArbitrary: Arbitrary[NowOffsetDateTime] =
    Arbitrary(
      Gen.delay(OffsetDateTime.now()).map(NowOffsetDateTime.apply)
    )
}
