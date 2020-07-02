package faker.internet

import scala.util.Random

import org.scalacheck.{Arbitrary, Gen}

import faker.ResourceLoader
import faker.lorem.LoremCharacters

final case class Password private (value: String) extends AnyVal

object Password {
  def passwordSpecialCharacters(implicit loader: ResourceLoader): Seq[String] =
    loader.loadStringList("internet.password.special-characters")
  implicit def passwordArbitrary(implicit
      loader: ResourceLoader
  ): Arbitrary[Password] =
    Arbitrary(
      for {
        chars <- Arbitrary.arbitrary[LoremCharacters]
        specialChars <- Gen.atLeastOne(passwordSpecialCharacters)
        pass =
          Random.shuffle(chars.value.map(_.toString) ++ specialChars).mkString
      } yield Password(pass)
    )
}
