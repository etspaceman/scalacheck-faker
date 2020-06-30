package faker.time

import java.time.{OffsetDateTime, ZoneId}

import org.scalacheck.Arbitrary

final case class RandomOffsetDateTime private (value: OffsetDateTime)
    extends AnyVal

object RandomOffsetDateTime {
  implicit val randomOffsetDateTimeArbitrary: Arbitrary[RandomOffsetDateTime] =
    Arbitrary(
      Arbitrary
        .arbitrary[RandomInstant]
        .map(x =>
          RandomOffsetDateTime(
            OffsetDateTime.ofInstant(x.value, ZoneId.systemDefault())
          )
        )
    )
}
