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

object SupportedLocales {
  val default: Locale = Locale.getDefault()
  val ar: Locale = new Locale("ar")
  val bg: Locale = new Locale("bg")
  val by: Locale = new Locale("by")
  val ca: Locale = new Locale("ca")
  val ca_CAT: Locale = new Locale("ca", "CAT")
  val cs_CZ: Locale = new Locale("cs", "CZ")
  val da_DK: Locale = new Locale("da", "DK")
  val de: Locale = Locale.GERMAN
  val de_AT: Locale = new Locale("de", "AT")
  val de_CH: Locale = new Locale("de", "CH")
  val ee: Locale = new Locale("ee")
  val en: Locale = Locale.ENGLISH
  val en_US: Locale = Locale.US
  val en_CA: Locale = Locale.CANADA
  val en_GB: Locale = Locale.UK
  val en_IND: Locale = new Locale("en", "IND")
  val en_AU: Locale = new Locale("en", "AU")
  val en_MS: Locale = new Locale("en", "MS")
  val en_NEP: Locale = new Locale("en", "NEP")
  val en_NG: Locale = new Locale("en", "NG")
  val en_NZ: Locale = new Locale("en", "NZ")
  val en_PAK: Locale = new Locale("en", "PAK")
  val en_SG: Locale = new Locale("en", "SG")
  val en_UG: Locale = new Locale("en", "UG")
  val en_ZA: Locale = new Locale("en", "ZA")
  val es: Locale = new Locale("es")
  val es_MX: Locale = new Locale("es", "MX")
  val fa: Locale = new Locale("fa")
  val fi_FI: Locale = new Locale("fi", "FI")
  val fr: Locale = new Locale("fr")
  val fr_CA: Locale = new Locale("fr", "CA")
  val fr_CH: Locale = new Locale("fr", "CH")
  val he: Locale = new Locale("he")
  val hu: Locale = new Locale("hu")
  val hy: Locale = new Locale("hy")
  val id: Locale = new Locale("id")
  val in_ID: Locale = new Locale("in_ID")
  val it: Locale = Locale.ITALIAN
  val ja: Locale = Locale.JAPANESE
  val ko: Locale = Locale.KOREAN
  val lv: Locale = new Locale("lv")
  val nb_NO: Locale = new Locale("nb", "NO")
  val nl: Locale = new Locale("nl")
  val pl: Locale = new Locale("pl")
  val pt: Locale = new Locale("pt")
  val pt_BR: Locale = new Locale("pt", "BR")
  val ru: Locale = new Locale("ru")
  val sk: Locale = new Locale("sk")
  val sv: Locale = new Locale("sv")
  val th: Locale = new Locale("th")
  val tr: Locale = new Locale("tr")
  val uk: Locale = new Locale("uk")
  val vi: Locale = new Locale("vi")
  val zh_CN: Locale = Locale.CHINA
  val zh_TW: Locale = Locale.TAIWAN

  val all = List(
    default,
    ar,
    bg,
    by,
    ca,
    ca_CAT,
    cs_CZ,
    da_DK,
    de,
    de_AT,
    de_CH,
    ee,
    en,
    en_US,
    en_CA,
    en_GB,
    en_IND,
    en_AU,
    en_MS,
    en_NEP,
    en_NG,
    en_NZ,
    en_PAK,
    en_SG,
    en_UG,
    en_ZA,
    es,
    es_MX,
    fa,
    fi_FI,
    fr,
    fr_CA,
    fr_CH,
    he,
    hu,
    hy,
    id,
    in_ID,
    it,
    ja,
    ko,
    lv,
    nb_NO,
    nl,
    pl,
    pt,
    pt_BR,
    ru,
    sk,
    sv,
    th,
    tr,
    uk,
    vi,
    zh_CN,
    zh_TW
  )
}
