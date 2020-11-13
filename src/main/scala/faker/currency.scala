package faker

import io.estatico.newtype.macros.newtype
import io.estatico.newtype.ops.toCoercibleIdOps
import org.scalacheck.{Arbitrary, Gen}
import pureconfig.ConfigReader

object currency {
  @newtype final case class CurrencyCode private (value: String)

  object CurrencyCode {
    def currencyCodes(implicit loader: ResourceLoader): Seq[CurrencyCode] =
      loader.loadKey[Seq[CurrencyCode]]("currency.codes")

    implicit def currencyCodeArbitrary(implicit
        loader: ResourceLoader
    ): Arbitrary[CurrencyCode] =
      Arbitrary(Gen.oneOf(currencyCodes))

    implicit val currencyCodeConfigReader: ConfigReader[CurrencyCode] =
      ConfigReader[String].coerce
  }

  @newtype final case class CurrencyName private (value: String)

  object CurrencyName {
    def currencyNames(implicit loader: ResourceLoader): Seq[CurrencyName] =
      loader.loadKey[Seq[CurrencyName]]("currency.names")

    implicit def currencyNameArbitrary(implicit
        loader: ResourceLoader
    ): Arbitrary[CurrencyName] =
      Arbitrary(Gen.oneOf(currencyNames))

    implicit val currencyNameConfigReader: ConfigReader[CurrencyName] =
      ConfigReader[String].coerce
  }

  @newtype final case class CurrencySymbol private (value: String)

  object CurrencySymbol {
    def currencySymbols(implicit loader: ResourceLoader): Seq[CurrencySymbol] =
      loader.loadKey[Seq[CurrencySymbol]]("currency.symbols")

    implicit def currencySymbolArbitrary(implicit
        loader: ResourceLoader
    ): Arbitrary[CurrencySymbol] =
      Arbitrary(Gen.oneOf(currencySymbols))

    implicit val currencySymbolConfigReader: ConfigReader[CurrencySymbol] =
      ConfigReader[String].coerce
  }
}
