package faker

import scala.reflect.ClassTag

import org.scalacheck.Arbitrary
import org.scalatest.freespec.AnyFreeSpecLike
import org.scalatestplus.scalacheck.Checkers

trait FakerArbitrarySpec extends AnyFreeSpecLike with Checkers {
  val resourceLoaders: Seq[ResourceLoader] =
    Seq(ResourceLoader.en_US, ResourceLoader.en_CA, ResourceLoader.en_GB)
  def testCanGen[A: Arbitrary](implicit
      CT: ClassTag[A]
  ): Unit =
    resourceLoaders.foreach(implicit loader =>
      s"${CT.runtimeClass.getName} should generate faker data successfully for ${loader.locale}" in {
        check((_: A) => true)
      }
    )
}
