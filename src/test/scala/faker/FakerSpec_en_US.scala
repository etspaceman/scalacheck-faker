package faker
import java.util.Locale

class FakerSpec_en_US extends FakerSpec {
  override lazy val locale: Locale = SupportedLocales.en_US
}
