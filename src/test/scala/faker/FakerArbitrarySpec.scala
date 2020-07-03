package faker

import scala.reflect.ClassTag

import org.scalacheck.Arbitrary
import org.scalatest.freespec.AnyFreeSpecLike
import org.scalatestplus.scalacheck.Checkers

trait FakerArbitrarySpec extends AnyFreeSpecLike with Checkers {
  def testCanGen[A: Arbitrary](implicit CT: ClassTag[A]): Unit =
    s"${CT.runtimeClass.getName} should generate faker data successfully" in {
      check((_: A) => true)
    }
}
