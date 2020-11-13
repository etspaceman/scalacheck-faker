package faker

import io.estatico.newtype.macros.newtype
import io.estatico.newtype.ops._
import org.scalacheck.{Arbitrary, Gen}
import pureconfig.ConfigReader
import pureconfig.generic.semiauto._

import faker.syntax.string._

object address {
  @newtype final case class BuildingNumber private (value: String)

  object BuildingNumber {
    implicit def buildingNumberArbitrary(implicit
        loader: ResourceLoader
    ): Arbitrary[BuildingNumber] =
      Arbitrary(
        Gen
          .sequence[Seq[String], String](
            loader
              .loadKey[Seq[String]]("address.building-numbers")
              .map(_.interpolatedGen)
          )
          .flatMap(x => Gen.oneOf(x))
      ).coerce
  }

  @newtype final case class City private (value: String)

  object City {
    implicit def cityArbitrary(implicit
        loader: ResourceLoader
    ): Arbitrary[City] =
      Arbitrary(
        loader
          .loadKey[StringGenBuilder]("address.city-builder")
          .gen
      ).coerce

    implicit val cityConfigReader: ConfigReader[City] =
      ConfigReader[String].coerce

  }

  @newtype final case class CityPrefix private (value: String)

  object CityPrefix {
    def cityPrefixes(implicit loader: ResourceLoader): Seq[CityPrefix] =
      loader.loadKey[Seq[CityPrefix]]("address.city-prefixes")
    implicit def cityPrefixArbitrary(implicit
        loader: ResourceLoader
    ): Arbitrary[CityPrefix] =
      Arbitrary(Gen.oneOf(cityPrefixes))

    implicit val cityPrefixConfigReader: ConfigReader[CityPrefix] =
      ConfigReader[String].coerce
  }

  @newtype final case class CitySuffix private (value: String)

  object CitySuffix {
    def citySuffixes(implicit loader: ResourceLoader): Seq[CitySuffix] =
      loader.loadKey[Seq[CitySuffix]]("address.city-suffixes")
    implicit def citySuffixArbitrary(implicit
        loader: ResourceLoader
    ): Arbitrary[CitySuffix] =
      Arbitrary(Gen.oneOf(citySuffixes))

    implicit val citySuffixConfigReader: ConfigReader[CitySuffix] =
      ConfigReader[String].coerce
  }

  final case class Country private (code: String, name: String)

  object Country {
    def countries(implicit loader: ResourceLoader): Seq[Country] =
      loader.loadKey[Seq[Country]]("address.countries")
    implicit def countryArbitrary(implicit
        loader: ResourceLoader
    ): Arbitrary[Country] = Arbitrary(Gen.oneOf(countries))

    implicit val countryConfigReader: ConfigReader[Country] = deriveReader
  }

  final case class DefaultCountry private (code: String, name: String)

  object DefaultCountry {
    def defaultCountries(implicit loader: ResourceLoader): Seq[DefaultCountry] =
      loader.loadKey[Seq[DefaultCountry]]("address.default-countries")
    implicit def defaultCountryArbitrary(implicit
        loader: ResourceLoader
    ): Arbitrary[DefaultCountry] = Arbitrary(Gen.oneOf(defaultCountries))

    implicit val defaultCountryConfigReader: ConfigReader[DefaultCountry] =
      deriveReader
  }

  @newtype final case class FullAddress private (value: String)

  object FullAddress {
    implicit def fullAddressArbitrary(implicit
        loader: ResourceLoader
    ): Arbitrary[FullAddress] =
      Arbitrary(
        loader
          .loadKey[StringGenBuilder]("address.full-address-builder")
          .gen
      ).coerce
  }

  @newtype final case class Latitude private (value: String)

  object Latitude {
    implicit val latitudeArbitrary: Arbitrary[Latitude] = Arbitrary(
      Gen
        .choose[Double](0, 0.99)
        .map(x => "%.8g".format((x * 180) - 90))
    ).coerce
  }

  @newtype final case class Longitude private (value: String)

  object Longitude {
    implicit val longitudeArbitrary: Arbitrary[Longitude] = Arbitrary(
      Gen
        .choose[Double](0, 0.99)
        .map(x => "%.8g".format((x * 360) - 180))
    ).coerce
  }

  @newtype final case class PostalCode private (value: String)

  object PostalCode {
    implicit def postalCodeArbitrary(implicit
        loader: ResourceLoader
    ): Arbitrary[PostalCode] =
      Arbitrary(
        loader
          .loadKey[StringGenBuilder]("address.postal-code-builder")
          .gen
      ).coerce
  }

  @newtype final case class SecondaryAddress private (value: String)

  object SecondaryAddress {
    implicit def secondaryAddressArbitrary(implicit
        loader: ResourceLoader
    ): Arbitrary[SecondaryAddress] =
      Arbitrary(
        Gen
          .sequence[Seq[String], String](
            loader
              .loadKey[Seq[String]]("address.secondary-addresses")
              .map(_.interpolatedGen)
          )
          .flatMap(x => Gen.oneOf(x))
          .coerce
      )
  }

  @newtype final case class StreetAddress private (value: String)

  object StreetAddress {
    implicit def streetAddressArbitrary(implicit
        loader: ResourceLoader
    ): Arbitrary[StreetAddress] =
      Arbitrary(
        loader
          .loadKey[StringGenBuilder]("address.street-address-builder")
          .gen
      ).coerce
  }

  @newtype final case class StreetName private (value: String)

  object StreetName {
    implicit def streetNameArbitrary(implicit
        loader: ResourceLoader
    ): Arbitrary[StreetName] =
      Arbitrary(
        loader
          .loadKey[StringGenBuilder]("address.street-name-builder")
          .gen
      ).coerce
  }

  @newtype final case class StreetPrefix private (value: String)

  object StreetPrefix {
    def streetPrefixes(implicit loader: ResourceLoader): Seq[StreetPrefix] =
      loader.loadKey[Seq[StreetPrefix]]("address.street-prefixes")
    implicit def streetPrefixArbitrary(implicit
        loader: ResourceLoader
    ): Arbitrary[StreetPrefix] =
      Arbitrary(Gen.oneOf(streetPrefixes))
    implicit val streetPrefixConfigReader: ConfigReader[StreetPrefix] =
      ConfigReader[String].coerce
  }

  @newtype final case class StreetSuffix private (value: String)

  object StreetSuffix {
    def streetSuffixes(implicit loader: ResourceLoader): Seq[StreetSuffix] =
      loader.loadKey[Seq[StreetSuffix]]("address.street-suffixes")
    implicit def streetSuffixArbitrary(implicit
        loader: ResourceLoader
    ): Arbitrary[StreetSuffix] =
      Arbitrary(Gen.oneOf(streetSuffixes))
    implicit val streetSuffixConfigReader: ConfigReader[StreetSuffix] =
      ConfigReader[String].coerce
  }
}
