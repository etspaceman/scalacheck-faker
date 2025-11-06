/*
 * Copyright (c) 2020 etspaceman
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of
 * this software and associated documentation files (the "Software"), to deal in
 * the Software without restriction, including without limitation the rights to
 * use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of
 * the Software, and to permit persons to whom the Software is furnished to do so,
 * subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
 * FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 * COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER
 * IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
 * CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package faker.states

import org.scalacheck.{Arbitrary, Gen}
import pureconfig.ConfigReader
import pureconfig.error.CannotParse

import faker.syntax.string._
import faker.{ResourceLoader, StringGenBuilder}

sealed trait StateLike {
  def abbr: String
  def name: String
  def postalCodeGen: Gen[String]
}

object StateLike {
  implicit val stateLikeConfigReader: ConfigReader[StateLike] =
    ConfigReader.fromCursor(c =>
      for {
        objC <- c.asObjectCursor
        tpeC <- objC.atKey("type")
        tpe <- tpeC.asString
        res <- tpe match {
          case "us-state"   => ConfigReader[UsState].from(c)
          case "state"      => ConfigReader[State].from(c)
          case "province"   => ConfigReader[Province].from(c)
          case "district"   => ConfigReader[District].from(c)
          case "region"     => ConfigReader[Region].from(c)
          case "prefecture" => ConfigReader[Prefecture].from(c)
          case "republic"   => ConfigReader[Republic].from(c)
          case "county"     => ConfigReader[County].from(c)
          case x            =>
            ConfigReader.Result.fail(
              CannotParse(
                s"StateLike ConfigReader could not parse type field value '$x'",
                c.origin
              )
            )
        }
      } yield res
    )
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
  implicit val usStateConfigReader: ConfigReader[UsState] =
    ConfigReader.forProduct4("abbr", "name", "min-zip", "max-zip")(
      UsState(_, _, _, _)
    )
}

final case class State(
    abbr: String,
    name: String,
    postalCodeBuilder: StringGenBuilder
) extends StateLike {
  override val postalCodeGen: Gen[String] = postalCodeBuilder.gen
}

object State {
  implicit val stateConfigReader: ConfigReader[State] =
    ConfigReader.forProduct3("abbr", "name", "postal-code-builder")(
      State(_, _, _)
    )
}

final case class Province(
    abbr: String,
    name: String,
    postalCodeBuilder: StringGenBuilder
) extends StateLike {
  override val postalCodeGen: Gen[String] = postalCodeBuilder.gen
}

object Province {
  implicit val provinceConfigReader: ConfigReader[Province] =
    ConfigReader.forProduct3("abbr", "name", "postal-code-builder")(
      Province(_, _, _)
    )
}

final case class District(name: String, postalCodeBuilder: StringGenBuilder)
    extends StateLike {
  override val abbr: String = name
  override val postalCodeGen: Gen[String] = postalCodeBuilder.gen
}

object District {
  implicit val districtConfigReader: ConfigReader[District] =
    ConfigReader.forProduct2("name", "postal-code-builder")(
      District(_, _)
    )
}

final case class Region(name: String, postalCodeBuilder: StringGenBuilder)
    extends StateLike {
  override val abbr: String = name
  override val postalCodeGen: Gen[String] = postalCodeBuilder.gen
}

object Region {
  implicit val regionConfigReader: ConfigReader[Region] =
    ConfigReader.forProduct2("name", "postal-code-builder")(Region(_, _))
}

final case class Prefecture(name: String, postalCodeBuilder: StringGenBuilder)
    extends StateLike {
  override val abbr: String = name
  override val postalCodeGen: Gen[String] = postalCodeBuilder.gen
}

object Prefecture {
  implicit val prefectureConfigReader: ConfigReader[Prefecture] =
    ConfigReader.forProduct2("name", "postal-code-builder")(Prefecture(_, _))
}

final case class Republic(name: String, postalCodeBuilder: StringGenBuilder)
    extends StateLike {
  override val abbr: String = name
  override val postalCodeGen: Gen[String] = postalCodeBuilder.gen
}

object Republic {
  implicit val republicConfigReader: ConfigReader[Republic] =
    ConfigReader.forProduct2("name", "postal-code-builder")(Republic(_, _))
}

final case class County(name: String, postalCodeBuilder: StringGenBuilder)
    extends StateLike {
  override val abbr: String = name
  override val postalCodeGen: Gen[String] = postalCodeBuilder.gen
}

object County {
  implicit val countyConfigReader: ConfigReader[County] =
    ConfigReader.forProduct2("name", "postal-code-builder")(County(_, _))
}
