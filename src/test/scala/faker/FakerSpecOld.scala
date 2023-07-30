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

import java.util.Locale

import org.scalacheck.Arbitrary
import org.scalacheck.Prop._

trait FakerSpecOld extends munit.ScalaCheckSuite {
  override val scalaCheckTestParameters =
    super.scalaCheckTestParameters.withMinSuccessfulTests(10).withWorkers(5)

  def locale: Locale

  def testCanGen[A: Arbitrary](locale: Locale, desc: String)(
      stringF: A => Option[String]
  ): Unit = {
    @SuppressWarnings(Array("DisableSyntax.var"))
    var hasAlerted: Boolean = false
    def maybeAlert(str: String): Unit =
      if (str.contains("Not implemented for this locale") && !hasAlerted) {
        println("Value contains an unimplemented element!")
        hasAlerted = true
      }
    property(s"$desc should generate faker data successfully for $locale") {
      forAll { (x: A) =>
        stringF(x).foreach(maybeAlert)
        true
      }
    }
  }

  implicit val loader: ResourceLoader = new ResourceLoader(locale)
  
  testCanGen[ancient.God](locale, "ancient.God")(x => Some(x.value))
  testCanGen[ancient.Primordial](locale, "ancient.Primordial")(x =>
    Some(x.value)
  )
  testCanGen[ancient.Titan](locale, "ancient.Titan")(x => Some(x.value))
  testCanGen[ancient.Hero](locale, "ancient.Hero")(x => Some(x.value))
  testCanGen[aviation.Aircraft](locale, "aviation.Aircraft")(x => Some(x.value))
  testCanGen[aviation.Airport](locale, "aviation.Airport")(x => Some(x.value))
  testCanGen[aviation.Metar](locale, "aviation.Metar")(x => Some(x.value))
  testCanGen[cat.Name](locale, "cat.Name")(x => Some(x.value))
  testCanGen[cat.Breed](locale, "cat.Breed")(x => Some(x.value))
  testCanGen[cat.Registry](locale, "cat.Registry")(x => Some(x.value))
  testCanGen[music.MusicAlbum](locale, "music.MusicAlbum")(x => Some(x.value))
  testCanGen[music.MusicalGenre](locale, "music.MusicalGenre")(x =>
    Some(x.value)
  )
  testCanGen[music.MusicalInstrument](locale, "music.MusicalInstrument")(x =>
    Some(x.value)
  )
  testCanGen[music.MusicBand](locale, "music.MusicBand")(x => Some(x.value))
  testCanGen[dragonball.DragonBallCharacter](
    locale,
    "dragonball.DragonBallCharacter"
  )(x => Some(x.value))
  testCanGen[job.JobField](locale, "job.JobField")(x => Some(x.value))
  testCanGen[job.JobSeniority](locale, "job.JobSeniority")(x => Some(x.value))
  testCanGen[job.JobPosition](locale, "job.JobPosition")(x => Some(x.value))
  testCanGen[job.JobKeySkill](locale, "job.JobKeySkill")(x => Some(x.value))
  testCanGen[job.JobEmploymentType](locale, "job.JobEmploymentType")(x =>
    Some(x.value)
  )
  testCanGen[job.JobEducationLevel](locale, "job.JobEducationLevel")(x =>
    Some(x.value)
  )
  testCanGen[job.JobTitle](locale, "job.JobTitle")(x => Some(x.value))
  testCanGen[currency.CurrencyCode](locale, "currency.CurrencyCode")(x =>
    Some(x.value)
  )
  testCanGen[currency.CurrencyName](locale, "currency.CurrencyName")(x =>
    Some(x.value)
  )
  testCanGen[currency.CurrencySymbol](locale, "currency.CurrencySymbol")(x =>
    Some(x.value)
  )
  testCanGen[yoda.YodaQuote](locale, "yoda.YodaQuote")(x => Some(x.value))
  testCanGen[programmingLanguage.ProgrammingLanguageName](
    locale,
    "programmingLanguage.ProgrammingLanguageName"
  )(x => Some(x.value))
  testCanGen[programmingLanguage.ProgrammingLanguageCreator](
    locale,
    "programmingLanguage.ProgrammingLanguageCreator"
  )(x => Some(x.value))
  testCanGen[basketball.BasketballPlayer](
    locale,
    "basketball.BasketballPlayer"
  )(x => Some(x.value))
  testCanGen[basketball.BasketballTeam](
    locale,
    "basketball.BasketballTeam"
  )(x => Some(x.value))
  testCanGen[basketball.BasketballCoach](
    locale,
    "basketball.BasketballCoach"
  )(x => Some(x.value))
  testCanGen[basketball.BasketballPosition](
    locale,
    "basketball.BasketballPosition"
  )(x => Some(x.value))
  testCanGen[color.ColorName](locale, "color.ColorName")(x => Some(x.value))
  testCanGen[app.AppName](locale, "app.AppName")(x => Some(x.value))
  testCanGen[app.AppVersion](locale, "app.AppVersion")(x => Some(x.value))
  testCanGen[app.AppAuthor](locale, "app.AppAuthor")(x => Some(x.value))
  testCanGen[artist.ArtistName](locale, "artist.ArtistName")(x => Some(x.value))
  testCanGen[aquaTeenHungerForce.Character](
    locale,
    "aquaTeenHungerForce.Character"
  )(x => Some(x.value))

  val faker: Faker = new Faker(locale)

  test(s"musicAlbum should return successfully for locale $locale") {
    val res = faker.musicAlbum()
    assert(res.nonEmpty, res)
  }
  test(s"musicalGenre should return successfully for locale $locale") {
    val res = faker.musicalGenre()
    assert(res.nonEmpty, res)
  }
  test(s"musicalInstrument should return successfully for locale $locale") {
    val res = faker.musicalInstrument()
    assert(res.nonEmpty, res)
  }
  test(s"musicBand should return successfully for locale $locale") {
    val res = faker.musicBand()
    assert(res.nonEmpty, res)
  }
  test(s"dragonballCharacter should return successfully for locale $locale") {
    val res = faker.dragonballCharacter()
    assert(res.nonEmpty, res)
  }
  test(s"jobField should return successfully for locale $locale") {
    val res = faker.jobField()
    assert(res.nonEmpty, res)
  }
  test(s"jobSeniority should return successfully for locale $locale") {
    val res = faker.jobSeniority()
    assert(res.nonEmpty, res)
  }
  test(s"jobPosition should return successfully for locale $locale") {
    val res = faker.jobPosition()
    assert(res.nonEmpty, res)
  }
  test(s"jobKeySkill should return successfully for locale $locale") {
    val res = faker.jobKeySkill()
    assert(res.nonEmpty, res)
  }
  test(s"jobEmploymentType should return successfully for locale $locale") {
    val res = faker.jobEmploymentType()
    assert(res.nonEmpty, res)
  }
  test(s"jobEducationLevel should return successfully for locale $locale") {
    val res = faker.jobEducationLevel()
    assert(res.nonEmpty, res)
  }
  test(s"jobTitle should return successfully for locale $locale") {
    val res = faker.jobTitle()
    assert(res.nonEmpty, res)
  }
  test(s"currencyCode should return successfully for locale $locale") {
    val res = faker.currencyCode()
    assert(res.nonEmpty, res)
  }
  test(s"currencyName should return successfully for locale $locale") {
    val res = faker.currencyName()
    assert(res.nonEmpty, res)
  }
  test(s"currencySymbol should return successfully for locale $locale") {
    val res = faker.currencySymbol()
    assert(res.nonEmpty, res)
  }
  test(s"god should return successfully for locale $locale") {
    val res = faker.god()
    assert(res.nonEmpty, res)
  }
  test(s"primordial should return successfully for locale $locale") {
    val res = faker.primordial()
    assert(res.nonEmpty, res)
  }
  test(s"titan should return successfully for locale $locale") {
    val res = faker.titan()
    assert(res.nonEmpty, res)
  }
  test(s"hero should return successfully for locale $locale") {
    val res = faker.hero()
    assert(res.nonEmpty, res)
  }
  test(s"aircraft should return successfully for locale $locale") {
    val res = faker.aircraft()
    assert(res.nonEmpty, res)
  }
  test(s"airport should return successfully for locale $locale") {
    val res = faker.airport()
    assert(res.nonEmpty, res)
  }
  test(s"metar should return successfully for locale $locale") {
    val res = faker.metar()
    assert(res.nonEmpty, res)
  }
  test(s"name should return successfully for locale $locale") {
    val res = faker.catName()
    assert(res.nonEmpty, res)
  }
  test(s"breed should return successfully for locale $locale") {
    val res = faker.catBreed()
    assert(res.nonEmpty, res)
  }
  test(s"registry should return successfully for locale $locale") {
    val res = faker.catRegistry()
    assert(res.nonEmpty, res)
  }
  test(s"yodaQuote should return successfully for locale $locale") {
    val res = faker.yodaQuote()
    assert(res.nonEmpty, res)
  }
  test(
    s"programmingLanguageName should return successfully for locale $locale"
  ) {
    val res = faker.programmingLanguageName()
    assert(res.nonEmpty, res)
  }
  test(
    s"programmingLanguageCreator should return successfully for locale $locale"
  ) {
    val res = faker.programmingLanguageCreator()
    assert(res.nonEmpty, res)
  }
  test(
    s"basketballPlayer should return successfully for locale $locale"
  ) {
    val res = faker.basketballPlayer()
    assert(res.nonEmpty, res)
  }
  test(
    s"basketballTeam should return successfully for locale $locale"
  ) {
    val res = faker.basketballTeam()
    assert(res.nonEmpty, res)
  }
  test(
    s"basketballCoach should return successfully for locale $locale"
  ) {
    val res = faker.basketballCoach()
    assert(res.nonEmpty, res)
  }
  test(
    s"basketballPosition should return successfully for locale $locale"
  ) {
    val res = faker.basketballPosition()
    assert(res.nonEmpty, res)
  }
  test(
    s"colorName should return successfully for locale $locale"
  ) {
    val res = faker.colorName()
    assert(res.nonEmpty, res)
  }
  test(
    s"appName should return successfully for locale $locale"
  ) {
    val res = faker.appName()
    assert(res.nonEmpty, res)
  }
  test(
    s"appVersion should return successfully for locale $locale"
  ) {
    val res = faker.appVersion()
    assert(res.nonEmpty, res)
  }
  test(
    s"appAuthor should return successfully for locale $locale"
  ) {
    val res = faker.appAuthor()
    assert(res.nonEmpty, res)
  }
  test(
    s"artistName should return successfully for locale $locale"
  ) {
    val res = faker.artistName()
    assert(res.nonEmpty, res)
  }
  test(
    s"aquaTeenHungerForceCharacter should return successfully for locale $locale"
  ) {
    val res = faker.aquaTeenHungerForceCharacter()
    assert(res.nonEmpty, res)
  }
}
