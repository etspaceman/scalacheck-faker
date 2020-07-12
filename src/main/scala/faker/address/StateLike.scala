package faker.address

import org.scalacheck.{Arbitrary, Gen}
import pureconfig.ConfigReader
import pureconfig.generic.semiauto._

import faker.syntax.string._
import faker.{ResourceLoader, StringGenBuilder}

sealed trait StateLike {
  def abbr: String
  def name: String
  def postalCodeGen: Gen[String]
}

object StateLike {
  implicit val stateLikeConfigReader: ConfigReader[StateLike] = deriveReader
  def states(implicit loader: ResourceLoader): Seq[StateLike] =
    loader.loadKey[Seq[StateLike]]("address.states")
  implicit def stateArbitrary(implicit
      loader: ResourceLoader
  ): Arbitrary[StateLike] = Arbitrary(Gen.oneOf(states))
}

final case class UsState private (
    abbr: String,
    name: String,
    minZip: Int,
    maxZip: Int
) extends StateLike {
  val zipCodes: Seq[String] =
    minZip.until(maxZip + 1).map(x => "%05d".format(x))
  private val zip5Gen: Gen[String] = Gen.oneOf(zipCodes)
  private val zip9Gen: Gen[String] = for {
    zip5 <- zip5Gen
    zip4 <- "####".interpolatedGen
  } yield s"$zip5-$zip4"
  override val postalCodeGen: Gen[String] =
    Gen.frequency(1 -> zip5Gen, 1 -> zip9Gen)
}

object UsState {
  implicit val usStateConfigReader: ConfigReader[UsState] = deriveReader
}

final case class State(
    abbr: String,
    name: String,
    postalCodeBuilder: StringGenBuilder
) extends StateLike {
  override val postalCodeGen: Gen[String] = postalCodeBuilder.gen
}

object State {
  implicit val stateConfigReader: ConfigReader[State] = deriveReader
}

final case class Province(
    abbr: String,
    name: String,
    postalCodeBuilder: StringGenBuilder
) extends StateLike {
  override val postalCodeGen: Gen[String] = postalCodeBuilder.gen
}

object Province {
  implicit val provinceConfigReader: ConfigReader[Province] = deriveReader
}

final case class District(name: String, postalCodeBuilder: StringGenBuilder)
    extends StateLike {
  override val abbr: String = name
  override val postalCodeGen: Gen[String] = postalCodeBuilder.gen
}

object District {
  implicit val districtConfigReader: ConfigReader[District] = deriveReader
}

final case class Region(name: String, postalCodeBuilder: StringGenBuilder)
    extends StateLike {
  override val abbr: String = name
  override val postalCodeGen: Gen[String] = postalCodeBuilder.gen
}

object Region {
  implicit val regionConfigReader: ConfigReader[Region] = deriveReader
}
