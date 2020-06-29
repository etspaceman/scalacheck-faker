package faker.lorem

import org.scalacheck.{Arbitrary, Gen}

final case class LoremCharacters private (value: String) extends AnyVal

object LoremCharacters {
  implicit val loremCharactersArbitrary: Arbitrary[LoremCharacters] = Arbitrary {
    for {
      characterCount <- Gen.choose(2, 255)
      characters <- Gen.listOfN(characterCount, Gen.oneOf(Lorem.characters))
    } yield LoremCharacters(characters.mkString)
  }
}
