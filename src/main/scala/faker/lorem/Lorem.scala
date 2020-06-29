package faker.lorem

import scala.io.Source

private[lorem] object Lorem {
  val words: Seq[String] =
    Source.fromInputStream(getClass.getResourceAsStream("/lorem-words.txt")).getLines.toSeq

  val characters: Set[Char] = words.mkString.toSet
}
