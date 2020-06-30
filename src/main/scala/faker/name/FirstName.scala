package faker.name

import faker.ResourceLoader
import org.scalacheck.{Arbitrary, Gen}

final case class FirstName private (value: String) extends AnyVal

object FirstName {
  private val firstNames: Seq[String] =
    ResourceLoader.loadStringList("name.first.names")

  implicit val firstNameArbitrary: Arbitrary[FirstName] = Arbitrary(
    Gen.oneOf(firstNames).map(FirstName.apply)
  )
}
