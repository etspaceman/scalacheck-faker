package faker.address

import org.scalacheck.{Arbitrary, Gen}
import pureconfig.ConfigReader
import pureconfig.generic.semiauto._

import faker.ResourceLoader
import faker.syntax.string._

/** Faker class for US States. Since this is specific for the US locale, that locale is
  * always used to load the data for this class.
  */
final case class State private (
    abbr: String,
    name: String,
    minZip: Int,
    maxZip: Int
) {
  val zipCodes: Seq[String] =
    minZip.until(maxZip + 1).map(x => "%05d".format(x))
  private val zip5Gen: Gen[String] = Gen.oneOf(zipCodes)
  private val zip9Gen: Gen[String] =
    zip5Gen.flatMap(zip5 => "####".interpolatedGen.map(zip4 => s"$zip5-$zip4"))
  val zipGen: Gen[String] = Gen.frequency(1 -> zip5Gen, 1 -> zip9Gen)
}

object State {
  implicit val stateConfigReader: ConfigReader[State] = deriveReader
  val states: Seq[State] =
    ResourceLoader.en_US.loadKey[Seq[State]]("address.states")
  implicit val stateArbitrary: Arbitrary[State] = Arbitrary(Gen.oneOf(states))
}
