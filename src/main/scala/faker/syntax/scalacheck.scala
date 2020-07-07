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
  @SuppressWarnings(Array("DisableSyntax.defaultArgs"))
  final class ScalacheckGenOps[A](private val gen: Gen[A]) extends AnyVal {
    def lazyList(seed: Seed = Seed.random()): LazyList[A] =
      LazyList
        .continually(gen.apply(Gen.Parameters.default, seed).toList)
        .flatten
    def one(seed: Seed = Seed.random()): A = lazyList(seed).head
    def take(n: Int, seed: Seed = Seed.random()): Seq[A] =
      lazyList(seed).take(n)
  }
}
