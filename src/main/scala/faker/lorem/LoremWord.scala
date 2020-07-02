package faker.lorem

import org.scalacheck.{Arbitrary, Gen}

import faker.ResourceLoader

final case class LoremWord private (value: String) extends AnyVal

object LoremWord {
  def loremWords(implicit loader: ResourceLoader): Seq[String] =
    loader.loadStringList("lorem.words")
  implicit def loremWordArbitrary(implicit
      loader: ResourceLoader
  ): Arbitrary[LoremWord] =
    Arbitrary(
      Gen.oneOf(loremWords).map(LoremWord.apply)
    )
}
