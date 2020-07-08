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
    if (lang.exists(_.isEmpty)) None else lang
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
    case (_, Some(localeConf)) =>
      localeConf.optional.withFallback(defaultConfig)
    case _ => defaultConfig
  }

  def loadKey[A: ConfigReader: ClassTag](key: String): A =
    conf.at(key).loadOrThrow[A]
}

object ResourceLoader {
  val default: ResourceLoader = new ResourceLoader(SupportedLocales.default)
  val en: ResourceLoader = new ResourceLoader(SupportedLocales.en)
  val en_US: ResourceLoader = new ResourceLoader(SupportedLocales.en_US)
  val en_CA: ResourceLoader = new ResourceLoader(SupportedLocales.en_CA)
  val en_GB: ResourceLoader = new ResourceLoader(SupportedLocales.en_GB)
  val en_IND: ResourceLoader = new ResourceLoader(SupportedLocales.en_IND)

  object Implicits {
    implicit val defaultResourceLoader: ResourceLoader = default
  }
}
