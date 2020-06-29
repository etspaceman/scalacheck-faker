package faker.lorem

import faker.ResourceLoader

private[lorem] object Lorem {
  val words: Seq[String] = ResourceLoader.loadLines("lorem-words.txt")
  val characters: Set[Char] = words.mkString.toSet
}
