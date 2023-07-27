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

package faker.regexp.data

private[faker] sealed abstract class Group[A] {

  protected def compliment: Group[A]

  protected def intersect(that: Group[A]): Group[A]

  protected def ++(that: Group[A]): Group[A]

  protected def --(that: Group[A]): Group[A]
}

private[faker] object Group {

  private[faker] final case class Inclusion[A](values: Set[A])
      extends Group[A] {

    override protected lazy val compliment: Group[A] = Exclusion(values)

    override protected def intersect(that: Group[A]): Group[A] =
      that match {
        case Inclusion(other) => Inclusion(values.intersect(other))
        case Exclusion(other) => Inclusion(values -- other)
      }

    override protected def ++(that: Group[A]): Group[A] =
      that match {
        case Inclusion(other) => Inclusion(values ++ other)
        case Exclusion(other) => Exclusion(other -- values)
      }

    override protected def --(that: Group[A]): Group[A] =
      that match {
        case Inclusion(other) => Inclusion(values -- other)
        case Exclusion(other) => Inclusion(values.intersect(other))
      }
  }

  private[faker] final case class Exclusion[A](values: Set[A])
      extends Group[A] {

    override protected lazy val compliment: Group[A] = Inclusion(values)

    override protected def intersect(that: Group[A]): Group[A] =
      that match {
        case Inclusion(other) => Inclusion(values -- other)
        case Exclusion(other) => Exclusion(values ++ other)
      }

    override protected def ++(that: Group[A]): Group[A] =
      that match {
        case Inclusion(other) => Exclusion(values -- other)
        case Exclusion(other) => Exclusion(values.intersect(other))
      }

    override protected def --(that: Group[A]): Group[A] =
      that match {
        case Exclusion(other) => Exclusion(other -- values)
        case Inclusion(other) => Exclusion(values ++ other)
      }
  }
}
