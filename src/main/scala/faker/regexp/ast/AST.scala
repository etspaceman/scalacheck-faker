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

package faker.regexp.ast

private[faker] sealed trait RegularExpression {

  def |(that: RegularExpression): RegularExpression =
    Or(this, that)

  def &(that: RegularExpression): RegularExpression =
    And(this, that)
}

private[faker] case class Literal(value: String) extends RegularExpression

private[faker] case object WordChar extends RegularExpression
private[faker] case object DigitChar extends RegularExpression
private[faker] case object SpaceChar extends RegularExpression
private[faker] case object AnyChar extends RegularExpression

private[faker] case object BOS extends RegularExpression
private[faker] case object EOS extends RegularExpression
private[faker] case object WordBoundary extends RegularExpression

private[faker] case class Group(term: RegularExpression) extends RegularExpression
private[faker] case class Substitution(index: Int) extends RegularExpression
private[faker] case class NonCapturingGroup(term: RegularExpression) extends RegularExpression

private[faker] case class Or(t1: RegularExpression, t2: RegularExpression)
    extends RegularExpression
private[faker] case class And(t1: RegularExpression, t2: RegularExpression)
    extends RegularExpression

private[faker] case class Negated(term: RegularExpression) extends RegularExpression

private[faker] sealed trait Quantified extends RegularExpression
private[faker] case class Optional(term: RegularExpression) extends Quantified
private[faker] case class ZeroOrMore(term: RegularExpression) extends Quantified
private[faker] case class OneOrMore(term: RegularExpression) extends Quantified
private[faker] case class Length(term: RegularExpression, min: Int) extends Quantified
private[faker] case class RangeFrom(term: RegularExpression, min: Int) extends Quantified
private[faker] case class Range(term: RegularExpression, min: Int, max: Int) extends Quantified

private[faker] object CharacterClass {

  private[faker] sealed trait Term

  private[faker] case class Literal(value: String) extends Term

  private[faker] case class DigitRange(min: Int, max: Int) extends Term
  private[faker] case class CharRange(min: Char, max: Char) extends Term

  private[faker] case object WordChar extends Term
  private[faker] case object DigitChar extends Term
  private[faker] case object SpaceChar extends Term
  private[faker] case object WordBoundary extends Term
}

private[faker] case class CharacterClass(terms: CharacterClass.Term*) extends RegularExpression
