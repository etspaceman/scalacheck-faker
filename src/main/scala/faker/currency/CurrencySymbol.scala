package faker.currency

import faker.ResourceLoader
import org.scalacheck.{Arbitrary, Gen}
import pureconfig.ConfigReader
import pureconfig.generic.semiauto.deriveReader

final case class CurrencySymbol private (value: String) extends AnyVal

object CurrencySymbol {
  def currencySymbols(implicit loader: ResourceLoader): Seq[CurrencySymbol] =
    loader.loadKey[Seq[CurrencySymbol]]("currency.symbols")

  implicit def currencySymbolArbitrary(implicit
      loader: ResourceLoader
  ): Arbitrary[CurrencySymbol] =
    Arbitrary(Gen.oneOf(currencySymbols))

  implicit val currencySymbolConfigReader: ConfigReader[CurrencySymbol] =
    deriveReader
}
