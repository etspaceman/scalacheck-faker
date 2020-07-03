package faker.name

import org.scalacheck.{Arbitrary, Gen}

import faker.ResourceLoader

final case class LastName private (value: String) extends AnyVal

object LastName {
  def lastNames(implicit loader: ResourceLoader): Seq[String] =
    loader.loadStringList("name.last.names")
  implicit def lastNameArbitrary(implicit
      loader: ResourceLoader
  ): Arbitrary[LastName] =
    Arbitrary(
      Gen.oneOf(lastNames).map(LastName.apply)
    )
}
