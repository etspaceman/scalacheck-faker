package faker

import java.util.Locale

import com.typesafe.config._

import scala.jdk.CollectionConverters._

private[faker] object ResourceLoader {
  private def resourceExists(path: String): Boolean =
    Option(getClass.getResourceAsStream(s"/$path")).isDefined

  private def parseResources(path: String): Config =
    if (resourceExists(path)) {
      ConfigFactory.parseResources(path)
    } else ConfigFactory.empty()

  private val locale: Locale = Locale.getDefault()
  private val defaultConfig: Config = parseResources("default.conf")
  private val languageConfig: Config =
    parseResources(s"${locale.getLanguage}.conf")
  private val localeConfig: Config = parseResources(s"${locale.toString}.conf")

  // Fallback Pattern: en-US.conf -> en.conf -> default.conf
  private val conf: Config =
    localeConfig
      .withFallback(languageConfig)
      .withFallback(defaultConfig)
      .resolve()

  def loadStringList(key: String): Seq[String] =
    conf.getStringList(key).asScala.toSeq
}
