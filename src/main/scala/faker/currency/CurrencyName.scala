package faker.currency

import org.scalacheck.{Arbitrary, Gen}
import pureconfig.ConfigReader
import pureconfig.generic.semiauto.deriveReader

import faker.ResourceLoader

final case class CurrencyName private (value: String) extends AnyVal

object CurrencyName {
  def currencyNames(implicit loader: ResourceLoader): Seq[CurrencyName] =
    loader.loadKey[Seq[CurrencyName]]("currency.names")

  implicit def currencyNameArbitrary(implicit
      loader: ResourceLoader
  ): Arbitrary[CurrencyName] =
    Arbitrary(Gen.oneOf(currencyNames))

  implicit val currencyNameConfigReader: ConfigReader[CurrencyName] =
    deriveReader
}
