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

package faker.syntax

import org.scalacheck.Gen
import org.scalacheck.rng.Seed

import faker.compat.LazyListCompat

private[faker] object scalacheck extends ScalacheckSyntax

private[faker] trait ScalacheckSyntax {
  implicit def toScalacheckGenOps[A](
      gen: Gen[A]
  ): ScalacheckSyntax.ScalacheckGenOps[A] =
    new ScalacheckSyntax.ScalacheckGenOps(gen)
}

private[faker] object ScalacheckSyntax extends LazyListCompat {
  final class ScalacheckGenOps[A](private val gen: Gen[A]) extends AnyVal {
    def lazyList(seed: Seed): LazyList[A] =
      LazyList
        .continually(gen.apply(Gen.Parameters.default, seed).toList)
        .flatten
    def lazyList: LazyList[A] = lazyList(Seed.random())
    def one(seed: Seed): A = lazyList(seed).head
    def one: A = one(Seed.random())
    def take(n: Int, seed: Seed): Seq[A] =
      lazyList(seed).take(n)
    def take(n: Int): Seq[A] = take(n, Seed.random())
  }
}
