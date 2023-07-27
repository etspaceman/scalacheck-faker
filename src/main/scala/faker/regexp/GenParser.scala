/*
 * Copyright (c) 2020 etspaceman
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of
 * this software and associated documentation files (the "Software"), to deal in
 * the Software without restriction, including without limitation the rights to
 * use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of
 * the Software, and to permit persons to whom the Software is furnished to do so,
 * subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
 * FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 * COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER
 * IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
 * CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package faker.regexp

import scala.util.parsing.combinator.{PackratParsers, RegexParsers}
import scala.util.parsing.input.CharSequenceReader

import ast._

private[faker] object GenParser extends RegexParsers with PackratParsers {

  override def skipWhitespace: Boolean = false

  private[faker] lazy val expression0: PackratParser[RegularExpression] =
    group | characterClass | term

  private[faker] lazy val expression1: PackratParser[RegularExpression] = {

    val int: Parser[Int] = "\\d+".r ^^ { _.toInt }

    // annoyingly hacky :( and it potentially breaks associativity but it's
    // nicer than having a concat rule for our purposes
    def splitLiteral(
        re: RegularExpression,
        result: RegularExpression => RegularExpression
    ): RegularExpression =
      re match {
        case Literal(s) if s.length > 1 =>
          And(Literal(s.init), result(Literal(s.last.toString)))
        case other =>
          result(other)
      }

    // quantifiers
    val optional = expression0 <~ "?" ^^ { splitLiteral(_, Optional.apply) }
    val oneOrMore = expression0 <~ "+" ^^ { splitLiteral(_, OneOrMore.apply) }
    val zeroOrMore = expression0 <~ "*" ^^ { splitLiteral(_, ZeroOrMore.apply) }

    val rangeFrom = expression0 ~ ("{" ~> int <~ ",}") ^^ { case expr ~ min =>
      splitLiteral(expr, RangeFrom(_, min))
    }

    val range = expression0 ~ ("{" ~> int ~ ("," ~> int <~ "}")) ^^ {
      case expr ~ (min ~ max) =>
        splitLiteral(expr, Range(_, min, max))
    }

    val length = expression0 ~ ("{" ~> int <~ "}") ^^ { case expr ~ l =>
      splitLiteral(expr, Length(_, l))
    }

    optional | oneOrMore | zeroOrMore | rangeFrom | range | length | expression0
  }

  // and
  private[faker] lazy val expression2: PackratParser[RegularExpression] =
    (expression2 ~ expression1) ^^ { case a ~ b => And(a, b) } | expression1

  // or
  private[faker] lazy val expression: PackratParser[RegularExpression] =
    (expression ~ ("|" ~> expression2) ^^ { case a ~ b =>
      Or(a, b)
    }) | expression2

  private[faker] lazy val group: PackratParser[RegularExpression] = {

    lazy val nonCapturingGroup =
      "(?:" ~> expression <~ ")" ^^ NonCapturingGroup.apply
    lazy val capturingGroup = "(" ~> expression <~ ")" ^^ Group.apply

    capturingGroup | nonCapturingGroup
  }

  // character classes
  private[faker] lazy val characterClass: PackratParser[RegularExpression] = {

    lazy val digitRange: Parser[CharacterClass.Term] = {
      val d: Parser[Int] = "\\d".r ^^ { _.toInt }
      (d ~ ("-" ~> d)) ^^ { case min ~ max =>
        CharacterClass.DigitRange(min, max)
      }
    }

    lazy val lowerAlphaRange: Parser[CharacterClass.Term] = {
      val c = "[a-z]".r ^^ { _.charAt(0) }
      c ~ ("-" ~> c) ^^ { case min ~ max =>
        CharacterClass.CharRange(min, max)
      }
    }

    lazy val upperAlphaRange: Parser[CharacterClass.Term] = {
      val c = "[A-Z]".r ^^ { _.charAt(0) }
      c ~ ("-" ~> c) ^^ { case min ~ max =>
        CharacterClass.CharRange(min, max)
      }
    }

    val word: Parser[CharacterClass.Term] = "\\w" ^^^ CharacterClass.WordChar
    val digit: Parser[CharacterClass.Term] = "\\d" ^^^ CharacterClass.DigitChar
    val space: Parser[CharacterClass.Term] = "\\s" ^^^ CharacterClass.SpaceChar
    val wordBoundary: Parser[CharacterClass.Term] =
      "\\b" ^^^ CharacterClass.WordBoundary

    lazy val char: Parser[CharacterClass.Term] = {
      val normalChars = "[^\\]\\\\]".r
      val meta = "\\" | "]" | "-"
      (("\\" ~> meta) | normalChars | "\\" ~> normalChars) ^^ CharacterClass.Literal.apply
    }

    lazy val characterClassTerm: Parser[CharacterClass.Term] =
      word | digit | space | wordBoundary | digitRange | lowerAlphaRange | upperAlphaRange | char

    lazy val charClass =
      ("[" ~> characterClassTerm.+ <~ "]") ^^ { CharacterClass(_: _*) }
    lazy val negatedCharClass =
      ("[^" ~> characterClassTerm.+ <~ "]") ^^ { terms =>
        Negated(CharacterClass(terms: _*))
      }

    negatedCharClass | charClass
  }

  // terminals...
  private[faker] lazy val term: PackratParser[RegularExpression] =
    char | classes | negClasses | substitution

  // default classes
  private[faker] lazy val word: Parser[RegularExpression] = "\\w" ^^^ WordChar
  private[faker] lazy val digit: Parser[RegularExpression] = "\\d" ^^^ DigitChar
  private[faker] lazy val space: Parser[RegularExpression] = "\\s" ^^^ SpaceChar
  private[faker] lazy val any: Parser[RegularExpression] = "." ^^^ AnyChar
  private[faker] lazy val wordBoundary: Parser[RegularExpression] = "\\b" ^^^ WordBoundary
  private[faker] lazy val classes: Parser[RegularExpression] =
    word | digit | space | any | wordBoundary

  private[faker] lazy val negWord: Parser[RegularExpression] = "\\W" ^^^ Negated(WordChar)
  private[faker] lazy val negDigit: Parser[RegularExpression] = "\\D" ^^^ Negated(DigitChar)
  private[faker] lazy val negSpace: Parser[RegularExpression] = "\\S" ^^^ Negated(SpaceChar)
  private[faker] lazy val negBoundary: Parser[RegularExpression] =
    "\\B" ^^^ Negated(WordBoundary)
  private[faker] lazy val negClasses: Parser[RegularExpression] =
    negWord | negDigit | negSpace | negBoundary

  private[faker] lazy val substitution: Parser[RegularExpression] = "\\" ~> "[1-9]\\d*".r ^^ {
    index =>
      Substitution(index.toInt)
  }

  private[faker] lazy val bos: Parser[RegularExpression] = "^" ^^^ BOS
  private[faker] lazy val eos: Parser[RegularExpression] = "$" ^^^ EOS

  private[faker] lazy val regularExpression: Parser[RegularExpression] =
    bos ~> expression <~ eos ^^ { expr => And(And(BOS, expr), EOS) } |
      bos ~> expression ^^ { expr => And(BOS, expr) } |
      expression <~ eos ^^ { expr => And(expr, EOS) } |
      expression

  // literals
  private[faker] lazy val char: PackratParser[Literal] = {
    val meta: Parser[String] =
      ")" | "(" | "$" | "[" | "." | "+" | "*" | "?" | "|" | "\\" | "{"
    (("\\" ~> meta) | "[^|)(.+*?{\\[$\\\\]".r).+ ^^ { strs =>
      Literal(strs.mkString(""))
    }
  }

  private[faker] def parse(string: String): RegularExpression =
    regularExpression(new PackratReader(new CharSequenceReader(string))).get
}
