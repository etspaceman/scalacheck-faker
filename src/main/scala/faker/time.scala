/*
 * Copyright (c) 2020 etspaceman
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of
 * this software and associated documentation files (the "Software"), to deal in
 * the Software without restriction, including without limitation the rights to
 * use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of
 * the Software, and to permit persons to whom the Software is furnished to do so,
 * subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
 * FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 * COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER
 * IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
 * CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package faker

import java.time.{Instant, LocalDateTime, OffsetDateTime, ZoneId, ZonedDateTime}

import org.scalacheck.{Arbitrary, Gen}

object time {
  type CurrentEraInstant = CurrentEraInstant.Type

  object CurrentEraInstant extends Newtype[Instant] { self =>
    implicit val currentEraInstantArbitrary: Arbitrary[CurrentEraInstant] =
      Arbitrary(
        Gen
          .choose(-62167219200000L, 253402300799999L)
          .map(Instant.ofEpochMilli)
          .map(self.apply)
      )
  }

  type CurrentEraLocalDateTime = CurrentEraLocalDateTime.Type

  object CurrentEraLocalDateTime extends Newtype[LocalDateTime] { self =>
    implicit val currentEraLocalDateTimeArbitrary
        : Arbitrary[CurrentEraLocalDateTime] = Arbitrary(
      Arbitrary
        .arbitrary[CurrentEraInstant]
        .map(x => LocalDateTime.ofInstant(x.value, ZoneId.systemDefault()))
        .map(self.apply)
    )
  }

  type CurrentEraOffsetDateTime = CurrentEraOffsetDateTime.Type

  object CurrentEraOffsetDateTime extends Newtype[OffsetDateTime] { self =>
    implicit val currentEraOffsetDateTimeArbitrary
        : Arbitrary[CurrentEraOffsetDateTime] = Arbitrary(
      Arbitrary
        .arbitrary[CurrentEraInstant]
        .map(x => OffsetDateTime.ofInstant(x.value, ZoneId.systemDefault()))
        .map(self.apply)
    )
  }

  type CurrentEraZonedDateTime = CurrentEraZonedDateTime.Type

  object CurrentEraZonedDateTime extends Newtype[ZonedDateTime] { self =>
    implicit val currentEraZonedDateTimeArbitrary
        : Arbitrary[CurrentEraZonedDateTime] = Arbitrary(
      Arbitrary
        .arbitrary[CurrentEraInstant]
        .map(x => ZonedDateTime.ofInstant(x.value, ZoneId.systemDefault()))
        .map(self.apply)
    )
  }

  type NowInstant = NowInstant.Type

  object NowInstant extends Newtype[Instant] { self =>
    implicit val nowInstantArbitrary: Arbitrary[NowInstant] = Arbitrary(
      Gen.delay(Instant.now()).map(self.apply)
    )
  }

  type NowLocalDateTime = NowLocalDateTime.Type

  object NowLocalDateTime extends Newtype[LocalDateTime] { self =>
    implicit val nowLocalDateTimeArbitrary: Arbitrary[NowLocalDateTime] =
      Arbitrary(Gen.delay(LocalDateTime.now()).map(self.apply))
  }

  type NowOffsetDateTime = NowOffsetDateTime.Type

  object NowOffsetDateTime extends Newtype[OffsetDateTime] { self =>
    implicit val nowOffsetTimeArbitrary: Arbitrary[NowOffsetDateTime] =
      Arbitrary(Gen.delay(OffsetDateTime.now()).map(self.apply))
  }

  type NowZonedDateTime = NowZonedDateTime.Type

  object NowZonedDateTime extends Newtype[ZonedDateTime] { self =>
    implicit val nowZonedDateTimeArbitrary: Arbitrary[NowZonedDateTime] =
      Arbitrary(Gen.delay(ZonedDateTime.now()).map(self.apply))
  }

  type FutureInstant = FutureInstant.Type

  object FutureInstant extends Newtype[Instant] { self =>
    implicit val futureInstantArbitrary: Arbitrary[FutureInstant] = Arbitrary {
      (for {
        now <- Arbitrary.arbitrary[NowInstant].map(_.value.plusMillis(1L))
        epochSecond <- Gen.choose(
          now.getEpochSecond,
          Instant.MAX.getEpochSecond
        )
        nanoAdjustment <- Gen.choose(now.getNano, Instant.MAX.getNano)
      } yield Instant.ofEpochSecond(epochSecond, nanoAdjustment.toLong))
        .map(self.apply)
    }
  }

  type FutureLocalDateTime = FutureLocalDateTime.Type

  object FutureLocalDateTime extends Newtype[LocalDateTime] { self =>
    implicit val futureLocalDateTimeArbitrary: Arbitrary[FutureLocalDateTime] =
      Arbitrary(
        Arbitrary
          .arbitrary[FutureInstant]
          .map(x => LocalDateTime.ofInstant(x.value, ZoneId.systemDefault()))
          .map(self.apply)
      )
  }

  type FutureOffsetDateTime = FutureOffsetDateTime.Type

  object FutureOffsetDateTime extends Newtype[OffsetDateTime] { self =>
    implicit val futureOffsetDateTimeArbitrary
        : Arbitrary[FutureOffsetDateTime] =
      Arbitrary(
        Arbitrary
          .arbitrary[FutureInstant]
          .map(x => OffsetDateTime.ofInstant(x.value, ZoneId.systemDefault()))
          .map(self.apply)
      )
  }

  type FutureZonedDateTime = FutureZonedDateTime.Type

  object FutureZonedDateTime extends Newtype[ZonedDateTime] { self =>
    implicit val futureZonedDateTimeArbitrary: Arbitrary[FutureZonedDateTime] =
      Arbitrary(
        Arbitrary
          .arbitrary[FutureInstant]
          .map(x => ZonedDateTime.ofInstant(x.value, ZoneId.systemDefault()))
          .map(self.apply)
      )
  }

  type PastInstant = PastInstant.Type

  object PastInstant extends Newtype[Instant] { self =>
    implicit val pastInstantArbitrary: Arbitrary[PastInstant] = Arbitrary {
      (for {
        now <- Arbitrary.arbitrary[NowInstant].map(_.value)
        epochSecond <- Gen.choose(
          Instant.MIN.getEpochSecond,
          now.getEpochSecond
        )
        nanoAdjustment <- Gen.choose(Instant.MIN.getNano, now.getNano)
      } yield Instant.ofEpochSecond(epochSecond, nanoAdjustment.toLong))
        .map(self.apply)
    }
  }

  type PastLocalDateTime = PastLocalDateTime.Type

  object PastLocalDateTime extends Newtype[LocalDateTime] { self =>
    implicit val pastLocalDateTimeArbitrary: Arbitrary[PastLocalDateTime] =
      Arbitrary(
        Arbitrary
          .arbitrary[PastInstant]
          .map(x => LocalDateTime.ofInstant(x.value, ZoneId.systemDefault()))
          .map(self.apply)
      )
  }

  type PastOffsetDateTime = PastOffsetDateTime.Type

  object PastOffsetDateTime extends Newtype[OffsetDateTime] { self =>
    implicit val pastOffsetDateTimeArbitrary: Arbitrary[PastOffsetDateTime] =
      Arbitrary(
        Arbitrary
          .arbitrary[PastInstant]
          .map(x => OffsetDateTime.ofInstant(x.value, ZoneId.systemDefault()))
          .map(self.apply)
      )
  }

  type PastZonedDateTime = PastZonedDateTime.Type

  object PastZonedDateTime extends Newtype[ZonedDateTime] { self =>
    implicit val pastZonedDateTimeArbitrary: Arbitrary[PastZonedDateTime] =
      Arbitrary(
        Arbitrary
          .arbitrary[PastInstant]
          .map(x => ZonedDateTime.ofInstant(x.value, ZoneId.systemDefault()))
          .map(self.apply)
      )
  }

  type RandomInstant = RandomInstant.Type

  object RandomInstant extends Newtype[Instant] { self =>
    implicit val randomInstantArbitrary: Arbitrary[RandomInstant] = Arbitrary {
      (for {
        epochSecond <-
          Gen.choose(Instant.MIN.getEpochSecond, Instant.MAX.getEpochSecond)
        nanoAdjustment <- Gen.choose(Instant.MIN.getNano, Instant.MAX.getNano)
      } yield Instant.ofEpochSecond(epochSecond, nanoAdjustment.toLong))
        .map(self.apply)
    }
  }

  type RandomLocalDateTime = RandomLocalDateTime.Type

  object RandomLocalDateTime extends Newtype[LocalDateTime] { self =>
    implicit val randomLocalDateTimeArbitrary: Arbitrary[RandomLocalDateTime] =
      Arbitrary(
        Arbitrary
          .arbitrary[RandomInstant]
          .map(x => LocalDateTime.ofInstant(x.value, ZoneId.systemDefault()))
          .map(self.apply)
      )
  }

  type RandomOffsetDateTime = RandomOffsetDateTime.Type

  object RandomOffsetDateTime extends Newtype[OffsetDateTime] { self =>
    implicit val randomOffsetDateTimeArbitrary
        : Arbitrary[RandomOffsetDateTime] =
      Arbitrary(
        Arbitrary
          .arbitrary[RandomInstant]
          .map(x => OffsetDateTime.ofInstant(x.value, ZoneId.systemDefault()))
          .map(self.apply)
      )
  }

  type RandomZonedDateTime = RandomZonedDateTime.Type

  object RandomZonedDateTime extends Newtype[ZonedDateTime] { self =>
    implicit val randomZonedDateTimeArbitrary: Arbitrary[RandomZonedDateTime] =
      Arbitrary(
        Arbitrary
          .arbitrary[RandomInstant]
          .map(x => ZonedDateTime.ofInstant(x.value, ZoneId.systemDefault()))
          .map(self.apply)
      )
  }
}
