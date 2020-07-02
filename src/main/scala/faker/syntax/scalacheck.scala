package faker.syntax

import org.scalacheck.Gen

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
    def lazyList: LazyList[A] = LazyList.continually(gen.sample.toList).flatten
    def one: A = lazyList.head
    def take(n: Int): Seq[A] = lazyList.take(n)
  }
}
