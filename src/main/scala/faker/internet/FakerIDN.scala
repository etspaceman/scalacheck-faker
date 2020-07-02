package faker.internet

import scala.util.Try

import java.net.IDN

object FakerIDN {
  def toASCII(in: String): String =
    Try(IDN.toASCII(in)).getOrElse(
      in.toList
        .flatMap(x => Try(IDN.toASCII(x.toString)).toOption.toList)
        .mkString
    )
}
