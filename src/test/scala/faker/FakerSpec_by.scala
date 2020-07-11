package faker

import java.util.Locale

class FakerSpec_by extends FakerSpec {
  override lazy val locale: Locale = SupportedLocales.by
}
