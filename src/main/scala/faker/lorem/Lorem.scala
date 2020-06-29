package faker.lorem

import scala.io.Source

private[lorem] object Lorem {
  val words: List[String] =
    Source.fromInputStream(getClass.getResourceAsStream("/lorem-words.txt")).getLines.toList
}
