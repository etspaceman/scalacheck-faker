package faker

import scala.jdk.CollectionConverters._

import java.util.Locale

import com.typesafe.config._

final class ResourceLoader(private val locale: Locale) {

  private val defaultConfig: Config =
    ConfigFactory.parseResources("default.conf")
  private val languageConfig: Config =
    ConfigFactory.parseResources(s"${locale.getLanguage}.conf")
  private val localeConfig: Config =
    ConfigFactory.parseResources(s"${locale.toString}.conf")

  // Fallback Pattern: en-US.conf -> en.conf -> default.conf
  private val conf: Config =
    localeConfig
      .withFallback(languageConfig)
      .withFallback(defaultConfig)
      .resolve()

  def loadStringList(key: String): Seq[String] =
    conf.getStringList(key).asScala.toSeq
}

object ResourceLoader {
  val default: ResourceLoader = new ResourceLoader(Locale.getDefault)

  object Implicits {
    implicit val defaultResourceLoader: ResourceLoader = default
  }
}
