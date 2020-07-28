package faker

import java.util.Locale

class FakerSpec_it extends FakerSpec {
  override lazy val locale: Locale = SupportedLocales.it
}
