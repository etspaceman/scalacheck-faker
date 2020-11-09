package faker.currency

import org.scalacheck.{Arbitrary, Gen}
import pureconfig.ConfigReader
import pureconfig.generic.semiauto.deriveReader

import faker.ResourceLoader

final case class CurrencyCode private (value: String) extends AnyVal

object CurrencyCode {
  def currencyCodes(implicit loader: ResourceLoader): Seq[CurrencyCode] =
    loader.loadKey[Seq[CurrencyCode]]("currency.codes")

  implicit def currencyCodeArbitrary(implicit
      loader: ResourceLoader
  ): Arbitrary[CurrencyCode] =
    Arbitrary(Gen.oneOf(currencyCodes))

  implicit val currencyCodeConfigReader: ConfigReader[CurrencyCode] =
    deriveReader
}
