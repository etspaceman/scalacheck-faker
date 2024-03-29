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

import scala.collection.concurrent
import scala.reflect.ClassTag

import java.util.Locale

import pureconfig._

final class ResourceLoader(private[faker] val locale: Locale) {
  private[faker] val cache: concurrent.Map[String, Any] =
    concurrent.TrieMap.empty

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
    case (_, Some(localeConf)) =>
      localeConf.optional.withFallback(defaultConfig)
    case _ => defaultConfig
  }

  @SuppressWarnings(Array("scalafix:DisableSyntax.asInstanceOf"))
  def loadKey[A: ClassTag](key: String)(implicit
      CR: ConfigReader[A]
  ): A =
    cache.getOrElseUpdate(key, conf.at(key).loadOrThrow[A]).asInstanceOf[A]
}

object ResourceLoader {
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
  val hy: ResourceLoader = new ResourceLoader(SupportedLocales.hy)
  val id: ResourceLoader = new ResourceLoader(SupportedLocales.id)
  val in_ID: ResourceLoader = new ResourceLoader(SupportedLocales.in_ID)
  val it: ResourceLoader = new ResourceLoader(SupportedLocales.it)
  val ja: ResourceLoader = new ResourceLoader(SupportedLocales.ja)
  val ko: ResourceLoader = new ResourceLoader(SupportedLocales.ko)
  val lv: ResourceLoader = new ResourceLoader(SupportedLocales.lv)
  val nb_NO: ResourceLoader = new ResourceLoader(SupportedLocales.nb_NO)
  val nl: ResourceLoader = new ResourceLoader(SupportedLocales.nl)
  val pl: ResourceLoader = new ResourceLoader(SupportedLocales.pl)
  val pt: ResourceLoader = new ResourceLoader(SupportedLocales.pt)
  val pt_BR: ResourceLoader = new ResourceLoader(SupportedLocales.pt_BR)
  val ru: ResourceLoader = new ResourceLoader(SupportedLocales.ru)
  val sk: ResourceLoader = new ResourceLoader(SupportedLocales.sk)
  val sv: ResourceLoader = new ResourceLoader(SupportedLocales.sv)
  val th: ResourceLoader = new ResourceLoader(SupportedLocales.th)
  val tr: ResourceLoader = new ResourceLoader(SupportedLocales.tr)
  val uk: ResourceLoader = new ResourceLoader(SupportedLocales.uk)
  val vi: ResourceLoader = new ResourceLoader(SupportedLocales.vi)
  val zh_CN: ResourceLoader = new ResourceLoader(SupportedLocales.zh_CN)
  val zh_TW: ResourceLoader = new ResourceLoader(SupportedLocales.zh_TW)

  object Implicits {
    implicit val defaultResourceLoader: ResourceLoader = default
  }
}
