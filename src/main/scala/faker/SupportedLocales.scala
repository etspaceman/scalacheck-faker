package faker

import java.util.Locale

object SupportedLocales {
  val default: Locale = Locale.getDefault()
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
}
