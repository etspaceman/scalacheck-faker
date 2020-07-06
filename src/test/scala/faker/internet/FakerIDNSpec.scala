package faker.internet

import org.scalatest.freespec.AnyFreeSpecLike
import org.scalatestplus.scalacheck.Checkers

import scala.util.Try

class FakerIDNSpec extends AnyFreeSpecLike with Checkers {
  "FakerIDN" - {
    "should not fail" in {
      check((str: String) => {
        Try(FakerIDN.toASCII(str)).isSuccess
      })
    }
  }
}
