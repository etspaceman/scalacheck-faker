package faker

import scala.reflect.ClassTag

import java.util.Locale

import pureconfig._

final class ResourceLoader(private val locale: Locale) {

  private val defaultConfig: ConfigObjectSource =
    ConfigSource.resources("default.conf")
  private val languageConfig: ConfigObjectSource =
    ConfigSource.resources(s"${locale.getLanguage}.conf")
  private val localeConfig: ConfigObjectSource =
    ConfigSource.resources(s"${locale.toString}.conf")

  // Fallback Pattern: en-US.conf -> en.conf -> default.conf
  private val conf: ConfigSource =
    localeConfig.optional
      .withFallback(languageConfig.optional)
      .withFallback(defaultConfig)

  def loadKey[A: ConfigReader: ClassTag](key: String): A =
    conf.at(key).loadOrThrow[A]
}

object ResourceLoader {
  val default: ResourceLoader = new ResourceLoader(Locale.getDefault)
  val US: ResourceLoader = new ResourceLoader(Locale.US)
  val en_CA: ResourceLoader = new ResourceLoader(Locale.CANADA)

  object Implicits {
    implicit val defaultResourceLoader: ResourceLoader = default
  }
}
