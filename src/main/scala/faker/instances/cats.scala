package faker.instances

import _root_.cats.Monad
import _root_.cats.StackSafeMonad
import org.scalacheck.Gen

object cats {
  implicit val genMonad: Monad[Gen] = new StackSafeMonad[Gen] {
    override def flatMap[A, B](fa: Gen[A])(f: A => Gen[B]): Gen[B] = fa.flatMap(f)
    override def pure[A](x: A): Gen[A] = Gen.const(x)
  }
}
