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
}
