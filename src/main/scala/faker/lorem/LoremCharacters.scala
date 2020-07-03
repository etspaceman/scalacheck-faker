package faker.lorem

import org.scalacheck.{Arbitrary, Gen}

import faker.ResourceLoader

final case class LoremCharacters private (value: String) extends AnyVal

object LoremCharacters {
  def loremCharacters(implicit loader: ResourceLoader): Set[Char] =
    LoremWord.loremWords.mkString.toSet
  implicit def loremCharactersArbitrary(implicit
      loader: ResourceLoader
  ): Arbitrary[LoremCharacters] =
    Arbitrary {
      for {
        characterCount <- Gen.choose(2, 255)
        characters <- Gen.listOfN(characterCount, Gen.oneOf(loremCharacters))
      } yield LoremCharacters(characters.mkString)
    }
}
