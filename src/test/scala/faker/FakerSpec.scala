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

import cats.Show
import cats.data.Validated.Valid
import cats.effect._
import cats.syntax.all._
import org.scalacheck.Arbitrary
import weaver._
import weaver.scalacheck._

trait FakerSpec extends SimpleIOSuite with Checkers {
  override lazy val checkConfig =
    super.checkConfig.copy(
      minimumSuccessful = 3,
      perPropertyParallelism = 5,
      maximumDiscardRatio = 40
    )

  def doTest[A, B](
      desc: String,
      fakerF: Faker => B,
      arbF: Faker => Arbitrary[A],
      testF: B => Boolean,
      stringF: A => String
  ): Unit = loggedTest(s"Testing $desc") { log =>
    Faker.all
      .parTraverse { faker =>
        for {
          _ <- log.info(s"Testing $desc for locale ${faker.locale}")
          b <- IO(fakerF(faker))
          arb <- IO(arbF(faker))
          res1 <- IO(expect(testF(b)))
          hasAlertedRef <- Ref.of(false)
          res2 <- forall { (x: A) =>
            @SuppressWarnings(Array("DisableSyntax.var"))
            def maybeAlert(str: String): IO[Unit] =
              hasAlertedRef.get.flatMap(hasAlerted =>
                if (
                  str
                    .contains(s"Not implemented for this locale") && !hasAlerted
                ) {
                  log.info(
                    s"'$desc' contains an unimplemented element for locale '${faker.locale}'"
                  ) >> hasAlertedRef.set(true)
                } else IO.unit
              )
            maybeAlert(stringF(x)).as(expect(true))
          }(arb, Show.show(stringF), implicitly, implicitly)
        } yield res1 && res2
      }
      .map(_.foldLeft(Expectations(Valid(()))) { case (a, x) => a && x })
  }
}
