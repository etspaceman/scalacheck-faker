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

import java.time._

object TimeSpec extends FakerSpec {

  doTest[time.CurrentEraInstant, Instant](
    "time.CurrentEraInstant",
    _.currentEraInstant(),
    _ => implicitly,
    res => Option(res).isDefined,
    _.toString
  )

  doTest[time.CurrentEraLocalDateTime, LocalDateTime](
    "time.CurrentEraLocalDateTime",
    _.currentEraLocalDateTime(),
    _ => implicitly,
    res => Option(res).isDefined,
    _.toString
  )

  doTest[time.CurrentEraOffsetDateTime, OffsetDateTime](
    "time.CurrentEraOffsetDateTime",
    _.currentEraOffsetDateTime(),
    _ => implicitly,
    res => Option(res).isDefined,
    _.toString
  )

  doTest[time.CurrentEraZonedDateTime, ZonedDateTime](
    "time.CurrentEraZonedDateTime",
    _.currentEraZonedDateTime(),
    _ => implicitly,
    res => Option(res).isDefined,
    _.toString
  )

  doTest[time.FutureInstant, Instant](
    "time.FutureInstant",
    _.futureInstant(),
    _ => implicitly,
    res => Option(res).isDefined,
    _.toString
  )

  doTest[time.FutureLocalDateTime, LocalDateTime](
    "time.FutureLocalDateTime",
    _.futureLocalDateTime(),
    _ => implicitly,
    res => Option(res).isDefined,
    _.toString
  )

  doTest[time.FutureOffsetDateTime, OffsetDateTime](
    "time.FutureOffsetDateTime",
    _.futureOffsetDateTime(),
    _ => implicitly,
    res => Option(res).isDefined,
    _.toString
  )

  doTest[time.FutureZonedDateTime, ZonedDateTime](
    "time.FutureZonedDateTime",
    _.futureZonedDateTime(),
    _ => implicitly,
    res => Option(res).isDefined,
    _.toString
  )

  doTest[time.NowInstant, Instant](
    "time.NowInstant",
    _.nowInstant(),
    _ => implicitly,
    res => Option(res).isDefined,
    _.toString
  )

  doTest[time.NowLocalDateTime, LocalDateTime](
    "time.NowLocalDateTime",
    _.nowLocalDateTime(),
    _ => implicitly,
    res => Option(res).isDefined,
    _.toString
  )

  doTest[time.NowOffsetDateTime, OffsetDateTime](
    "time.NowOffsetDateTime",
    _.nowOffsetDateTime(),
    _ => implicitly,
    res => Option(res).isDefined,
    _.toString
  )

  doTest[time.NowZonedDateTime, ZonedDateTime](
    "time.NowZonedDateTime",
    _.nowZonedDateTime(),
    _ => implicitly,
    res => Option(res).isDefined,
    _.toString
  )

  doTest[time.PastInstant, Instant](
    "time.PastInstant",
    _.pastInstant(),
    _ => implicitly,
    res => Option(res).isDefined,
    _.toString
  )

  doTest[time.PastLocalDateTime, LocalDateTime](
    "time.PastLocalDateTime",
    _.pastLocalDateTime(),
    _ => implicitly,
    res => Option(res).isDefined,
    _.toString
  )

  doTest[time.PastOffsetDateTime, OffsetDateTime](
    "time.PastOffsetDateTime",
    _.pastOffsetDateTime(),
    _ => implicitly,
    res => Option(res).isDefined,
    _.toString
  )

  doTest[time.PastZonedDateTime, ZonedDateTime](
    "time.PastZonedDateTime",
    _.pastZonedDateTime(),
    _ => implicitly,
    res => Option(res).isDefined,
    _.toString
  )

  doTest[time.RandomInstant, Instant](
    "time.RandomInstant",
    _.randomInstant(),
    _ => implicitly,
    res => Option(res).isDefined,
    _.toString
  )

  doTest[time.RandomLocalDateTime, LocalDateTime](
    "time.RandomLocalDateTime",
    _.randomLocalDateTime(),
    _ => implicitly,
    res => Option(res).isDefined,
    _.toString
  )

  doTest[time.RandomOffsetDateTime, OffsetDateTime](
    "time.RandomOffsetDateTime",
    _.randomOffsetDateTime(),
    _ => implicitly,
    res => Option(res).isDefined,
    _.toString
  )

  doTest[time.RandomZonedDateTime, ZonedDateTime](
    "time.RandomZonedDateTime",
    _.randomZonedDateTime(),
    _ => implicitly,
    res => Option(res).isDefined,
    _.toString
  )

}
