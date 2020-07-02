package faker.name

import org.scalacheck.{Arbitrary, Gen}

import faker.ResourceLoader

final case class FirstName private (value: String) extends AnyVal

object FirstName {
  def firstNames(implicit loader: ResourceLoader): Seq[String] =
    loader.loadStringList("name.first.names")

  implicit def firstNameArbitrary(implicit
      loader: ResourceLoader
  ): Arbitrary[FirstName] =
    Arbitrary(
      Gen.oneOf(firstNames).map(FirstName.apply)
    )
}
