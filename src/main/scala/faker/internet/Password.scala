package faker.internet

import faker.ResourceLoader
import faker.lorem.LoremCharacters
import org.scalacheck.{Arbitrary, Gen}

import scala.util.Random

final case class Password private (value: String) extends AnyVal

object Password {
  private val passwordSpecialCharacters: Seq[String] =
    ResourceLoader.loadStringList("internet.password.special-characters")
  implicit val passwordArbitrary: Arbitrary[Password] = Arbitrary(
    for {
      chars <- Arbitrary.arbitrary[LoremCharacters]
      specialChars <- Gen.atLeastOne(passwordSpecialCharacters)
      pass =
        Random.shuffle(chars.value.map(_.toString) ++ specialChars).mkString
    } yield Password(pass)
  )
}
