package faker.internet

import scala.util.Random

import org.scalacheck.{Arbitrary, Gen}

import faker.ResourceLoader
import faker.lorem.LoremCharacters

final case class Password private (value: String) extends AnyVal

object Password {
  def passwordSpecialCharacters(implicit loader: ResourceLoader): Seq[String] =
    loader.loadKey[Seq[String]]("internet.password.special-characters")
  implicit def passwordArbitrary(implicit
      loader: ResourceLoader
  ): Arbitrary[Password] =
    Arbitrary(
      for {
        regularCharNumber <- Gen.choose(8, 20)
        chars <-
          Gen
            .listOfN(
              regularCharNumber,
              Gen.oneOf(LoremCharacters.loremCharacters)
            )
            .map(_.mkString)
        specialChars <- Gen.atLeastOne(passwordSpecialCharacters)
        pass = Random.shuffle(chars.map(_.toString) ++ specialChars).mkString
      } yield Password(pass)
    )
}
