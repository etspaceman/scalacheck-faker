package faker.company

import org.scalacheck.Arbitrary

import faker.{ResourceLoader, StringGenBuilder}

final case class CatchPhrase private (value: String) extends AnyVal

object CatchPhrase {
  implicit def catchPhraseArbitrary(implicit
      loader: ResourceLoader
  ): Arbitrary[CatchPhrase] =
    Arbitrary(
      loader
        .loadKey[StringGenBuilder]("company.catch-phrase-builder")
        .gen
        .map(CatchPhrase.apply)
    )
}
