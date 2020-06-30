package faker.lorem

import faker.ResourceLoader
import org.scalacheck.{Arbitrary, Gen}

final case class LoremWord private (value: String) extends AnyVal

object LoremWord {
  private[faker] val loremWords: Seq[String] =
    ResourceLoader.loadStringList("lorem.words")
  implicit val loremWordArbitrary: Arbitrary[LoremWord] = Arbitrary(
    Gen.oneOf(loremWords).map(LoremWord.apply)
  )
}
