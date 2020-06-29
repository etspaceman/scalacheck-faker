package faker.name

import faker.ResourceLoader
import org.scalacheck.{Arbitrary, Gen}

final case class LastName private (value: String) extends AnyVal

object LastName {
  private val lastNames: Seq[String] = ResourceLoader.loadLines("last-names.txt")
  implicit val lastNameArbitrary: Arbitrary[LastName] = Arbitrary(Gen.oneOf(lastNames).map(LastName.apply))
}
