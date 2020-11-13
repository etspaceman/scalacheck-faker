package faker

import java.time.{Instant, LocalDateTime, OffsetDateTime, ZoneId, ZonedDateTime}

import io.estatico.newtype.macros.newtype
import io.estatico.newtype.ops._
import org.scalacheck.{Arbitrary, Gen}

object time {
  @newtype final case class CurrentEraInstant private (value: Instant)

  object CurrentEraInstant {
    implicit val currentEraInstantArbitrary: Arbitrary[CurrentEraInstant] =
      Arbitrary(
        Gen
          .choose(-62167219200000L, 253402300799999L)
          .map(Instant.ofEpochMilli)
      ).coerce
  }

  @newtype final case class CurrentEraLocalDateTime private (
      value: LocalDateTime
  )

  object CurrentEraLocalDateTime {
    implicit val currentEraLocalDateTimeArbitrary
        : Arbitrary[CurrentEraLocalDateTime] = Arbitrary(
      Arbitrary
        .arbitrary[CurrentEraInstant]
        .map(x => LocalDateTime.ofInstant(x.value, ZoneId.systemDefault()))
    ).coerce
  }

  @newtype final case class CurrentEraOffsetDateTime private (
      value: OffsetDateTime
  )

  object CurrentEraOffsetDateTime {
    implicit val currentEraOffsetDateTimeArbitrary
        : Arbitrary[CurrentEraOffsetDateTime] = Arbitrary(
      Arbitrary
        .arbitrary[CurrentEraInstant]
        .map(x => OffsetDateTime.ofInstant(x.value, ZoneId.systemDefault()))
    ).coerce
  }

  @newtype final case class CurrentEraZonedDateTime private (
      value: ZonedDateTime
  )

  object CurrentEraZonedDateTime {
    implicit val currentEraZonedDateTimeArbitrary
        : Arbitrary[CurrentEraZonedDateTime] = Arbitrary(
      Arbitrary
        .arbitrary[CurrentEraInstant]
        .map(x => ZonedDateTime.ofInstant(x.value, ZoneId.systemDefault()))
    ).coerce
  }

  @newtype final case class NowInstant private (value: Instant)

  object NowInstant {
    implicit val nowInstantArbitrary: Arbitrary[NowInstant] = Arbitrary(
      Gen.delay(Instant.now())
    ).coerce
  }

  @newtype final case class NowLocalDateTime private (value: LocalDateTime)

  object NowLocalDateTime {
    implicit val nowLocalDateTimeArbitrary: Arbitrary[NowLocalDateTime] =
      Arbitrary(Gen.delay(LocalDateTime.now())).coerce
  }

  @newtype final case class NowOffsetDateTime private (value: OffsetDateTime)

  object NowOffsetDateTime {
    implicit val nowOffsetTimeArbitrary: Arbitrary[NowOffsetDateTime] =
      Arbitrary(Gen.delay(OffsetDateTime.now())).coerce
  }

  @newtype final case class NowZonedDateTime private (value: ZonedDateTime)

  object NowZonedDateTime {
    implicit val nowZonedDateTimeArbitrary: Arbitrary[NowZonedDateTime] =
      Arbitrary(Gen.delay(ZonedDateTime.now())).coerce
  }

  @newtype final case class FutureInstant private (value: Instant)

  object FutureInstant {
    implicit val futureInstantArbitrary: Arbitrary[FutureInstant] = Arbitrary {
      for {
        now <- Arbitrary.arbitrary[NowInstant].map(_.value.plusMillis(1L))
        epochSecond <- Gen.choose(
          now.getEpochSecond,
          Instant.MAX.getEpochSecond
        )
        nanoAdjustment <- Gen.choose(now.getNano, Instant.MAX.getNano)
      } yield Instant.ofEpochSecond(epochSecond, nanoAdjustment.toLong)
    }.coerce
  }

  @newtype final case class FutureLocalDateTime private (value: LocalDateTime)

  object FutureLocalDateTime {
    implicit val futureLocalDateTimeArbitrary: Arbitrary[FutureLocalDateTime] =
      Arbitrary(
        Arbitrary
          .arbitrary[FutureInstant]
          .map(x => LocalDateTime.ofInstant(x.value, ZoneId.systemDefault()))
      ).coerce
  }

  @newtype final case class FutureOffsetDateTime private (value: OffsetDateTime)

  object FutureOffsetDateTime {
    implicit val futureOffsetDateTimeArbitrary
        : Arbitrary[FutureOffsetDateTime] =
      Arbitrary(
        Arbitrary
          .arbitrary[FutureInstant]
          .map(x => OffsetDateTime.ofInstant(x.value, ZoneId.systemDefault()))
      ).coerce
  }

  @newtype final case class FutureZonedDateTime private (value: ZonedDateTime)

  object FutureZonedDateTime {
    implicit val futureZonedDateTimeArbitrary: Arbitrary[FutureZonedDateTime] =
      Arbitrary(
        Arbitrary
          .arbitrary[FutureInstant]
          .map(x => ZonedDateTime.ofInstant(x.value, ZoneId.systemDefault()))
      ).coerce
  }

  @newtype final case class PastInstant private (value: Instant)

  object PastInstant {
    implicit val pastInstantArbitrary: Arbitrary[PastInstant] = Arbitrary {
      for {
        now <- Arbitrary.arbitrary[NowInstant].map(_.value)
        epochSecond <- Gen.choose(
          Instant.MIN.getEpochSecond,
          now.getEpochSecond
        )
        nanoAdjustment <- Gen.choose(Instant.MIN.getNano, now.getNano)
      } yield Instant.ofEpochSecond(epochSecond, nanoAdjustment.toLong)
    }.coerce
  }

  @newtype final case class PastLocalDateTime private (value: LocalDateTime)

  object PastLocalDateTime {
    implicit val pastLocalDateTimeArbitrary: Arbitrary[PastLocalDateTime] =
      Arbitrary(
        Arbitrary
          .arbitrary[PastInstant]
          .map(x => LocalDateTime.ofInstant(x.value, ZoneId.systemDefault()))
      ).coerce
  }

  @newtype final case class PastOffsetDateTime private (value: OffsetDateTime)

  object PastOffsetDateTime {
    implicit val pastOffsetDateTimeArbitrary: Arbitrary[PastOffsetDateTime] =
      Arbitrary(
        Arbitrary
          .arbitrary[PastInstant]
          .map(x => OffsetDateTime.ofInstant(x.value, ZoneId.systemDefault()))
      ).coerce
  }

  @newtype final case class PastZonedDateTime private (value: ZonedDateTime)

  object PastZonedDateTime {
    implicit val pastZonedDateTimeArbitrary: Arbitrary[PastZonedDateTime] =
      Arbitrary(
        Arbitrary
          .arbitrary[PastInstant]
          .map(x => ZonedDateTime.ofInstant(x.value, ZoneId.systemDefault()))
      ).coerce
  }

  @newtype final case class RandomInstant private (value: Instant)

  object RandomInstant {
    implicit val randomInstantArbitrary: Arbitrary[RandomInstant] = Arbitrary {
      for {
        epochSecond <-
          Gen.choose(Instant.MIN.getEpochSecond, Instant.MAX.getEpochSecond)
        nanoAdjustment <- Gen.choose(Instant.MIN.getNano, Instant.MAX.getNano)
      } yield Instant.ofEpochSecond(epochSecond, nanoAdjustment.toLong)
    }.coerce
  }

  @newtype final case class RandomLocalDateTime private (value: LocalDateTime)

  object RandomLocalDateTime {
    implicit val randomLocalDateTimeArbitrary: Arbitrary[RandomLocalDateTime] =
      Arbitrary(
        Arbitrary
          .arbitrary[RandomInstant]
          .map(x => LocalDateTime.ofInstant(x.value, ZoneId.systemDefault()))
      ).coerce
  }

  @newtype final case class RandomOffsetDateTime private (value: OffsetDateTime)

  object RandomOffsetDateTime {
    implicit val randomOffsetDateTimeArbitrary
        : Arbitrary[RandomOffsetDateTime] =
      Arbitrary(
        Arbitrary
          .arbitrary[RandomInstant]
          .map(x => OffsetDateTime.ofInstant(x.value, ZoneId.systemDefault()))
      ).coerce
  }

  @newtype final case class RandomZonedDateTime private (value: ZonedDateTime)

  object RandomZonedDateTime {
    implicit val randomZonedDateTimeArbitrary: Arbitrary[RandomZonedDateTime] =
      Arbitrary(
        Arbitrary
          .arbitrary[RandomInstant]
          .map(x => ZonedDateTime.ofInstant(x.value, ZoneId.systemDefault()))
      ).coerce
  }
}
