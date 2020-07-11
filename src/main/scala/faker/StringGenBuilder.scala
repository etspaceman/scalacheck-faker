package faker

import scala.jdk.CollectionConverters._

import org.scalacheck.Gen
import pureconfig.ConfigReader
import pureconfig.generic.semiauto._

import faker.address.DefaultCountry
import faker.syntax.string._

private[faker] final case class StringGenBuilder(
    options: Seq[StringGenBuilderWeightedOption]
) {
  val gen: Gen[String] =
    Gen.frequency(options.map(opt => opt.weight -> opt.gen): _*)
}

object StringGenBuilder {
  implicit val stringGenBuilderConfigReader: ConfigReader[StringGenBuilder] =
    deriveReader
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
      : ConfigReader[StringGenBuilderPart] = deriveReader
}

private[faker] final case class SeqStringPart(
    prefix: Option[String],
    suffix: Option[String],
    value: Seq[String]
) extends StringGenBuilderPart {
  val valueGen: Gen[String] =
    Gen
      .sequence(value.map(_.interpolatedGen))
      .flatMap(x => Gen.oneOf(x.asScala))
}

object SeqStringPart {
  implicit val seqStringPartConfigReader: ConfigReader[SeqStringPart] =
    deriveReader
}

private[faker] final case class SeqStateZipPart(
    prefix: Option[String],
    suffix: Option[String],
    value: Seq[address.StateLike]
) extends StringGenBuilderPart {
  val valueGen: Gen[String] =
    Gen.sequence(value.map(_.postalCodeGen)).flatMap(x => Gen.oneOf(x.asScala))
}

object SeqStateZipPart {
  implicit val seqStateZipPartConfigReader: ConfigReader[SeqStateZipPart] =
    deriveReader
}

private[faker] final case class SeqStateAbbrAndZipPart(
    prefix: Option[String],
    suffix: Option[String],
    value: Seq[address.StateLike]
) extends StringGenBuilderPart {
  val valueGen: Gen[String] = for {
    state <- Gen.oneOf(value)
    zip <- state.postalCodeGen
  } yield s"${state.abbr} $zip"
}

object SeqStateAbbrAndZipPart {
  implicit val seqStateAbbrPartConfigReader
      : ConfigReader[SeqStateAbbrAndZipPart] =
    deriveReader
}

private[faker] final case class StringPart(
    prefix: Option[String],
    suffix: Option[String],
    value: String
) extends StringGenBuilderPart {
  val valueGen: Gen[String] = value.interpolatedGen
}

object StringPart {
  implicit val stringPartConfigReader: ConfigReader[StringPart] = deriveReader
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
    deriveReader
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
    deriveReader
}

private[faker] final case class DefaultCountryPart(
    prefix: Option[String],
    suffix: Option[String],
    value: Seq[DefaultCountry]
) extends StringGenBuilderPart {
  val valueGen: Gen[String] = Gen.oneOf(value.map(_.code))
}

object DefaultCountryPart {
  implicit val defaultCountryPartConfigReader
      : ConfigReader[DefaultCountryPart] =
    deriveReader
}

private[faker] final case class StringGenBuilderWeightedOption(
    parts: List[StringGenBuilderPart],
    weight: Int = 1
) {
  val gen: Gen[String] = Gen.sequence(parts.map(_.gen)).map(_.asScala.mkString)
}

object StringGenBuilderWeightedOption {
  implicit val stringGenBuilderWeightedOptionConfigReader
      : ConfigReader[StringGenBuilderWeightedOption] = deriveReader
}
