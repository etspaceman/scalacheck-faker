package faker.internet

import scala.util.Try

import org.scalatest.freespec.AnyFreeSpecLike
import org.scalatestplus.scalacheck.Checkers

class FakerIDNSpec extends AnyFreeSpecLike with Checkers {
  "FakerIDN" - {
    "should not fail" in {
      check((str: String) => {
        Try(FakerIDN.toASCII(str)).isSuccess
      })
    }
  }
}
