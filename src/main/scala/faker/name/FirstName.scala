package faker.name

import faker.ResourceLoader
import org.scalacheck.{Arbitrary, Gen}

final case class FirstName private (value: String) extends AnyVal

object FirstName {
  private val femaleFirstNames: Seq[String] = ResourceLoader.loadLines("female-first-names.txt")
  private val maleFirstNames: Seq[String] = ResourceLoader.loadLines("male-first-names.txt")
  private val firstNames: Seq[String] = femaleFirstNames ++ maleFirstNames

  implicit val firstNameArbitrary: Arbitrary[FirstName] = Arbitrary(Gen.oneOf(firstNames).map(FirstName.apply))
}
