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

package faker

import org.scalacheck.Gen
import pureconfig.ConfigReader
import pureconfig.error.CannotParse

import faker.syntax.string._

private[faker] final case class StringGenBuilder(
    options: Seq[StringGenBuilderWeightedOption]
) {
  val gen: Gen[String] =
    Gen.frequency(options.map(opt => opt.weight -> opt.gen): _*)
}

object StringGenBuilder {
  implicit val stringGenBuilderConfigReader: ConfigReader[StringGenBuilder] =
    ConfigReader.forProduct1("options")(StringGenBuilder(_))
}

private[faker] sealed trait StringGenBuilderPart {
  def prefix: Option[String]
  def suffix: Option[String]
  def valueGen: Gen[String]
  def gen: Gen[String] =
    valueGen.map(x => s"${prefix.getOrElse("")}$x${suffix.getOrElse("")}")
}

object StringGenBuilderPart {
  implicit val stringGenBuilderPartConfigReader
      : ConfigReader[StringGenBuilderPart] = ConfigReader.fromCursor(c =>
    for {
      objC <- c.asObjectCursor
      tpeC <- objC.atKey("type")
      tpe <- tpeC.asString
      res <- tpe match {
        case "seq-string-part"     => ConfigReader[SeqStringPart].from(c)
        case "seq-state-zip-part"  => ConfigReader[SeqStateZipPart].from(c)
        case "seq-state-name-part" => ConfigReader[SeqStateNamePart].from(c)
        case "seq-state-abbr-and-zip-part" =>
          ConfigReader[SeqStateAbbrAndZipPart].from(c)
        case "string-part"          => ConfigReader[StringPart].from(c)
        case "string-regex-part"    => ConfigReader[StringRegexPart].from(c)
        case "string-builder-part"  => ConfigReader[StringBuilderPart].from(c)
        case "default-country-part" => ConfigReader[DefaultCountryPart].from(c)
        case "country-part"         => ConfigReader[CountryPart].from(c)
        case x =>
          ConfigReader.Result.fail(
            CannotParse(
              s"StringGenBuilderPart ConfigReader could not parse type field value '$x'",
              c.origin
            )
          )
      }
    } yield res
  )
}

private[faker] final case class SeqStringPart(
    prefix: Option[String],
    suffix: Option[String],
    value: Seq[String]
) extends StringGenBuilderPart {
  val valueGen: Gen[String] =
    Gen
      .sequence[Seq[String], String](value.map(_.interpolatedGen))
      .flatMap(x => Gen.oneOf(x))
}

object SeqStringPart {
  implicit val seqStringPartConfigReader: ConfigReader[SeqStringPart] =
    ConfigReader.forProduct3("prefix", "suffix", "value")(
      SeqStringPart(_, _, _)
    )
}

private[faker] final case class SeqStateZipPart(
    prefix: Option[String],
    suffix: Option[String],
    value: Seq[states.StateLike]
) extends StringGenBuilderPart {
  val valueGen: Gen[String] =
    Gen
      .sequence[Seq[String], String](value.map(_.postalCodeGen))
      .flatMap(x => Gen.oneOf(x))
}

object SeqStateZipPart {
  implicit val seqStateZipPartConfigReader: ConfigReader[SeqStateZipPart] =
    ConfigReader.forProduct3("prefix", "suffix", "value")(
      SeqStateZipPart(_, _, _)
    )
}

private[faker] final case class SeqStateNamePart(
    prefix: Option[String],
    suffix: Option[String],
    value: Seq[states.StateLike]
) extends StringGenBuilderPart {
  val valueGen: Gen[String] = Gen.oneOf(value.map(_.name))
}

object SeqStateNamePart {
  implicit val seqStateNamePartConfigReader: ConfigReader[SeqStateNamePart] =
    ConfigReader.forProduct3("prefix", "suffix", "value")(
      SeqStateNamePart(_, _, _)
    )
}

private[faker] final case class SeqStateAbbrAndZipPart(
    prefix: Option[String],
    suffix: Option[String],
    value: Seq[states.StateLike]
) extends StringGenBuilderPart {
  val valueGen: Gen[String] = for {
    state <- Gen.oneOf(value)
    zip <- state.postalCodeGen
  } yield s"${state.abbr} $zip"
}

object SeqStateAbbrAndZipPart {
  implicit val seqStateAbbrPartConfigReader
      : ConfigReader[SeqStateAbbrAndZipPart] =
    ConfigReader.forProduct3("prefix", "suffix", "value")(
      SeqStateAbbrAndZipPart(_, _, _)
    )
}

private[faker] final case class StringPart(
    prefix: Option[String],
    suffix: Option[String],
    value: String
) extends StringGenBuilderPart {
  val valueGen: Gen[String] = value.interpolatedGen
}

object StringPart {
  implicit val stringPartConfigReader: ConfigReader[StringPart] =
    ConfigReader.forProduct3("prefix", "suffix", "value")(
      StringPart(_, _, _)
    )
}

private[faker] final case class StringRegexPart(
    prefix: Option[String],
    suffix: Option[String],
    value: String
) extends StringGenBuilderPart {
  val valueGen: Gen[String] = value.regexGen
}

object StringRegexPart {
  implicit val stringRegexPartConfigReader: ConfigReader[StringRegexPart] =
    ConfigReader.forProduct3("prefix", "suffix", "value")(
      StringRegexPart(_, _, _)
    )
}

private[faker] final case class StringBuilderPart(
    prefix: Option[String],
    suffix: Option[String],
    value: StringGenBuilder
) extends StringGenBuilderPart {
  val valueGen: Gen[String] = value.gen
}

object StringBuilderPart {
  implicit val stringBuilderPartConfigReader: ConfigReader[StringBuilderPart] =
    ConfigReader.forProduct3("prefix", "suffix", "value")(
      StringBuilderPart(_, _, _)
    )
}

private[faker] final case class DefaultCountryPart(
    prefix: Option[String],
    suffix: Option[String],
    value: Seq[address.DefaultCountry]
) extends StringGenBuilderPart {
  val valueGen: Gen[String] = Gen.oneOf(value.map(_.code))
}

object DefaultCountryPart {
  implicit val defaultCountryPartConfigReader
      : ConfigReader[DefaultCountryPart] =
    ConfigReader.forProduct3("prefix", "suffix", "value")(
      DefaultCountryPart(_, _, _)
    )
}

private[faker] final case class CountryPart(
    prefix: Option[String],
    suffix: Option[String],
    value: Seq[address.Country]
) extends StringGenBuilderPart {
  val valueGen: Gen[String] = Gen.oneOf(value.map(_.name))
}

object CountryPart {
  implicit val countryPartConfigReader: ConfigReader[CountryPart] =
    ConfigReader.forProduct3("prefix", "suffix", "value")(
      CountryPart(_, _, _)
    )
}

private[faker] final case class StringGenBuilderWeightedOptionConfig(
    parts: List[StringGenBuilderPart],
    weight: Option[Int]
) {
  val convert: StringGenBuilderWeightedOption =
    weight.fold(StringGenBuilderWeightedOption(parts))(x =>
      StringGenBuilderWeightedOption(parts, x)
    )
}

object StringGenBuilderWeightedOptionConfig {
  implicit val stringGenBuilderWeightedOptionConfig
      : ConfigReader[StringGenBuilderWeightedOptionConfig] =
    ConfigReader.forProduct2("parts", "weight")(
      StringGenBuilderWeightedOptionConfig(_, _)
    )
}

private[faker] final case class StringGenBuilderWeightedOption(
    parts: List[StringGenBuilderPart],
    weight: Int = 1
) {
  val gen: Gen[String] =
    Gen.sequence[Seq[String], String](parts.map(_.gen)).map(_.mkString)
}

object StringGenBuilderWeightedOption {
  implicit val stringGenBuilderWeightedOptionConfigReader
      : ConfigReader[StringGenBuilderWeightedOption] =
    ConfigReader[StringGenBuilderWeightedOptionConfig].map(_.convert)
}
