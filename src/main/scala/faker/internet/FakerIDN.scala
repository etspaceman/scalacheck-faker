package faker.internet

import java.net.IDN

import scala.util.Try

object FakerIDN {
  def toASCII(in: String): String =
    Try(IDN.toASCII(in)).getOrElse(
      in.toList
        .flatMap(x => Try(IDN.toASCII(x.toString)).toOption.toList)
        .mkString
    )
}
