package faker

import scala.io.Source

private[faker] object ResourceLoader {
  def loadLines(file: String): Seq[String] =
    Source.fromInputStream(getClass.getResourceAsStream(s"/${file}")).getLines.toSeq
}
