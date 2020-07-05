package faker.syntax

import org.scalacheck.Gen

object string extends FakerStringSyntax

trait FakerStringSyntax {
  implicit def toFakerStringOps(str: String): FakerStringSyntax.FakerStringOps =
    new FakerStringSyntax.FakerStringOps(str)
}

object FakerStringSyntax {
  final class FakerStringOps(private val str: String) extends AnyVal {
    def interpolatedGen: Gen[String] =
      str.foldLeft(Gen.const("")) { (gen, char) =>
        if (char == '#') gen.flatMap(x => Gen.choose(0, 9).map(y => s"$x$y"))
        else gen.map(y => y + char.toString)
      }
  }
}
