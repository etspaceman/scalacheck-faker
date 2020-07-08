package faker

import java.util.Locale

object SupportedLocales {
  val default: Locale = Locale.getDefault()
  val en: Locale = Locale.ENGLISH
  val en_US: Locale = Locale.US
  val en_CA: Locale = Locale.CANADA
  val en_GB: Locale = Locale.UK
  val en_IND: Locale = new Locale("en", "IND")
}
