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

import org.scalacheck.{Arbitrary, Gen}
import pureconfig.ConfigReader

import faker.syntax.string._

object address {
  type BuildingNumber = BuildingNumber.Type
  object BuildingNumber extends Newtype[String] { self =>
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
          .map(self.apply)
      )
  }

  type City = City.Type
  object City extends Newtype[String] { self =>
    implicit def cityArbitrary(implicit
        loader: ResourceLoader
    ): Arbitrary[City] =
      Arbitrary(
        loader
          .loadKey[StringGenBuilder]("address.city-builder")
          .gen
          .map(self.apply)
      )

    implicit val cityConfigReader: ConfigReader[City] =
      ConfigReader[String].map(self.apply)

  }

  type CityPrefix = CityPrefix.Type
  object CityPrefix extends Newtype[String] { self =>

    def cityPrefixes(implicit loader: ResourceLoader): Seq[CityPrefix] =
      loader.loadKey[Seq[CityPrefix]]("address.city-prefixes")
    implicit def cityPrefixArbitrary(implicit
        loader: ResourceLoader
    ): Arbitrary[CityPrefix] =
      Arbitrary(Gen.oneOf(cityPrefixes))

    implicit val cityPrefixConfigReader: ConfigReader[CityPrefix] =
      ConfigReader[String].map(self.apply)
  }

  type CitySuffix = CitySuffix.Type
  object CitySuffix extends Newtype[String] { self =>
    def citySuffixes(implicit loader: ResourceLoader): Seq[CitySuffix] =
      loader.loadKey[Seq[CitySuffix]]("address.city-suffixes")
    implicit def citySuffixArbitrary(implicit
        loader: ResourceLoader
    ): Arbitrary[CitySuffix] =
      Arbitrary(Gen.oneOf(citySuffixes))

    implicit val citySuffixConfigReader: ConfigReader[CitySuffix] =
      ConfigReader[String].map(self.apply)
  }

  final case class Country private (code: String, name: String)

  object Country {
    def countries(implicit loader: ResourceLoader): Seq[Country] =
      loader.loadKey[Seq[Country]]("address.countries")
    implicit def countryArbitrary(implicit
        loader: ResourceLoader
    ): Arbitrary[Country] = Arbitrary(Gen.oneOf(countries))

    implicit val countryConfigReader: ConfigReader[Country] =
      ConfigReader.forProduct2("code", "name")(Country(_, _))
  }

  final case class DefaultCountry private (code: String, name: String)

  object DefaultCountry {
    def defaultCountries(implicit loader: ResourceLoader): Seq[DefaultCountry] =
      loader.loadKey[Seq[DefaultCountry]]("address.default-countries")
    implicit def defaultCountryArbitrary(implicit
        loader: ResourceLoader
    ): Arbitrary[DefaultCountry] = Arbitrary(Gen.oneOf(defaultCountries))

    implicit val defaultCountryConfigReader: ConfigReader[DefaultCountry] =
      ConfigReader.forProduct2("code", "name")(DefaultCountry(_, _))
  }

  type FullAddress = FullAddress.Type
  object FullAddress extends Newtype[String] { self =>
    implicit def fullAddressArbitrary(implicit
        loader: ResourceLoader
    ): Arbitrary[FullAddress] =
      Arbitrary(
        loader
          .loadKey[StringGenBuilder]("address.full-address-builder")
          .gen
          .map(self.apply)
      )
  }

  type Latitude = Latitude.Type

  object Latitude extends Newtype[String] { self =>
    implicit val latitudeArbitrary: Arbitrary[Latitude] = Arbitrary(
      Gen
        .choose[Double](0, 0.99)
        .map(x => "%.8g".format((x * 180) - 90))
        .map(self.apply)
    )
  }

  type Longitude = Longitude.Type
  object Longitude extends Newtype[String] { self =>
    implicit val longitudeArbitrary: Arbitrary[Longitude] = Arbitrary(
      Gen
        .choose[Double](0, 0.99)
        .map(x => "%.8g".format((x * 360) - 180))
        .map(self.apply)
    )
  }

  type PostalCode = PostalCode.Type

  object PostalCode extends Newtype[String] { self =>
    implicit def postalCodeArbitrary(implicit
        loader: ResourceLoader
    ): Arbitrary[PostalCode] =
      Arbitrary(
        loader
          .loadKey[StringGenBuilder]("address.postal-code-builder")
          .gen
          .map(self.apply)
      )
  }

  type SecondaryAddress = SecondaryAddress.Type

  object SecondaryAddress extends Newtype[String] { self =>
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
          .map(self.apply)
      )
  }

  type StreetAddress = StreetAddress.Type

  object StreetAddress extends Newtype[String] { self =>
    implicit def streetAddressArbitrary(implicit
        loader: ResourceLoader
    ): Arbitrary[StreetAddress] =
      Arbitrary(
        loader
          .loadKey[StringGenBuilder]("address.street-address-builder")
          .gen
          .map(self.apply)
      )
  }

  type StreetName = StreetName.Type

  object StreetName extends Newtype[String] { self =>
    implicit def streetNameArbitrary(implicit
        loader: ResourceLoader
    ): Arbitrary[StreetName] =
      Arbitrary(
        loader
          .loadKey[StringGenBuilder]("address.street-name-builder")
          .gen
          .map(self.apply)
      )
  }

  type StreetPrefix = StreetPrefix.Type

  object StreetPrefix extends Newtype[String] { self =>
    def streetPrefixes(implicit loader: ResourceLoader): Seq[StreetPrefix] =
      loader.loadKey[Seq[StreetPrefix]]("address.street-prefixes")
    implicit def streetPrefixArbitrary(implicit
        loader: ResourceLoader
    ): Arbitrary[StreetPrefix] =
      Arbitrary(Gen.oneOf(streetPrefixes))
    implicit val streetPrefixConfigReader: ConfigReader[StreetPrefix] =
      ConfigReader[String].map(self.apply)
  }

  type StreetSuffix = StreetSuffix.Type

  object StreetSuffix extends Newtype[String] { self =>
    def streetSuffixes(implicit loader: ResourceLoader): Seq[StreetSuffix] =
      loader.loadKey[Seq[StreetSuffix]]("address.street-suffixes")
    implicit def streetSuffixArbitrary(implicit
        loader: ResourceLoader
    ): Arbitrary[StreetSuffix] =
      Arbitrary(Gen.oneOf(streetSuffixes))
    implicit val streetSuffixConfigReader: ConfigReader[StreetSuffix] =
      ConfigReader[String].map(self.apply)
  }
}
