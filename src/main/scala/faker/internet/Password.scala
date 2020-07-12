package faker.internet

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
        charNum <- Gen.choose(8, 20)
        specialChars <- Gen.atLeastOne(passwordSpecialCharacters)
        pass <-
          Gen
            .listOfN(
              charNum,
              Gen.oneOf(LoremCharacters.loremCharacters ++ specialChars)
            )
            .map(_.mkString)
      } yield Password(pass)
    )
}
