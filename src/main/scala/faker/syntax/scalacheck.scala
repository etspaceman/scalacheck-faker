package faker.syntax

import org.scalacheck.Gen
import org.scalacheck.rng.Seed

import faker.compat.LazyListCompat

object scalacheck extends ScalacheckSyntax

trait ScalacheckSyntax {
  implicit def toScalacheckGenOps[A](
      gen: Gen[A]
  ): ScalacheckSyntax.ScalacheckGenOps[A] =
    new ScalacheckSyntax.ScalacheckGenOps(gen)
}

object ScalacheckSyntax extends LazyListCompat {
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
