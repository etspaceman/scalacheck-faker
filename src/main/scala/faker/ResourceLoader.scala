package faker

import scala.reflect.ClassTag

import java.util.Locale

import pureconfig._

final class ResourceLoader(private[faker] val locale: Locale) {
  private val country: Option[String] = {
    val cty = Option(locale.getCountry)
    if (cty.exists(_.isEmpty)) None else cty
  }
  private val language: Option[String] = {
    val lang = Option(locale.getLanguage)
    if (lang.exists(_.isEmpty)) None
    // 2020-07-18: Special case for hebrew as the Locale class overrides
    // this input to "iw" (even though it claims to prefer "he"). This should
    // allow the ResourceLoader to handle both cases.
    else if (lang.contains("iw")) Some("he")
    else lang
  }

  private val defaultConfig: ConfigObjectSource =
    ConfigSource.resources("default.conf")
  private val languageConfig: Option[ConfigObjectSource] =
    language.map(x => ConfigSource.resources(s"$x.conf"))
  private val localeConfig: Option[ConfigObjectSource] = for {
    lang <- language
    cty <- country
  } yield ConfigSource.resources(s"${lang}_$cty.conf")

  // Fallback Pattern: en-US.conf -> en.conf -> default.conf
  private val conf: ConfigSource = (languageConfig, localeConfig) match {
    case (Some(langConf), Some(localeConf)) =>
      localeConf.optional
        .withFallback(langConf.optional)
        .withFallback(defaultConfig)
    case (Some(langConf), _) => langConf.optional.withFallback(defaultConfig)
    // $COVERAGE-OFF$
    case (_, Some(localeConf)) =>
      localeConf.optional.withFallback(defaultConfig)
    case _ => defaultConfig
    // $COVERAGE-ON$
  }

  def loadKey[A: ConfigReader: ClassTag](key: String): A =
    conf.at(key).loadOrThrow[A]
}

object ResourceLoader {
  // $COVERAGE-OFF$
  val default: ResourceLoader = new ResourceLoader(SupportedLocales.default)

  val ar: ResourceLoader = new ResourceLoader(SupportedLocales.ar)
  val bg: ResourceLoader = new ResourceLoader(SupportedLocales.bg)
  val by: ResourceLoader = new ResourceLoader(SupportedLocales.by)
  val ca: ResourceLoader = new ResourceLoader(SupportedLocales.ca)
  val ca_CAT: ResourceLoader = new ResourceLoader(SupportedLocales.ca_CAT)
  val cs_CZ: ResourceLoader = new ResourceLoader(SupportedLocales.cs_CZ)
  val da_DK: ResourceLoader = new ResourceLoader(SupportedLocales.da_DK)
  val de: ResourceLoader = new ResourceLoader(SupportedLocales.de)
  val de_AT: ResourceLoader = new ResourceLoader(SupportedLocales.de_AT)
  val de_CH: ResourceLoader = new ResourceLoader(SupportedLocales.de_CH)
  val ee: ResourceLoader = new ResourceLoader(SupportedLocales.ee)
  val en: ResourceLoader = new ResourceLoader(SupportedLocales.en)
  val en_US: ResourceLoader = new ResourceLoader(SupportedLocales.en_US)
  val en_CA: ResourceLoader = new ResourceLoader(SupportedLocales.en_CA)
  val en_GB: ResourceLoader = new ResourceLoader(SupportedLocales.en_GB)
  val en_IND: ResourceLoader = new ResourceLoader(SupportedLocales.en_IND)
  val en_AU: ResourceLoader = new ResourceLoader(SupportedLocales.en_AU)
  val en_MS: ResourceLoader = new ResourceLoader(SupportedLocales.en_MS)
  val en_NEP: ResourceLoader = new ResourceLoader(SupportedLocales.en_NEP)
  val en_NG: ResourceLoader = new ResourceLoader(SupportedLocales.en_NG)
  val en_NZ: ResourceLoader = new ResourceLoader(SupportedLocales.en_NZ)
  val en_PAK: ResourceLoader = new ResourceLoader(SupportedLocales.en_PAK)
  val en_SG: ResourceLoader = new ResourceLoader(SupportedLocales.en_SG)
  val en_UG: ResourceLoader = new ResourceLoader(SupportedLocales.en_UG)
  val en_ZA: ResourceLoader = new ResourceLoader(SupportedLocales.en_ZA)
  val es: ResourceLoader = new ResourceLoader(SupportedLocales.es)
  val es_MX: ResourceLoader = new ResourceLoader(SupportedLocales.es_MX)
  val fa: ResourceLoader = new ResourceLoader(SupportedLocales.fa)
  val fi_FI: ResourceLoader = new ResourceLoader(SupportedLocales.fi_FI)
  val fr: ResourceLoader = new ResourceLoader(SupportedLocales.fr)
  val fr_CA: ResourceLoader = new ResourceLoader(SupportedLocales.fr_CA)
  val fr_CH: ResourceLoader = new ResourceLoader(SupportedLocales.fr_CH)
  val he: ResourceLoader = new ResourceLoader(SupportedLocales.he)
  val hu: ResourceLoader = new ResourceLoader(SupportedLocales.hu)

  object Implicits {
    implicit val defaultResourceLoader: ResourceLoader = default
  }
  // $COVERAGE-ON$
}
