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

package faker

import org.scalacheck.{Arbitrary, Gen}
import pureconfig.ConfigReader

object basketball {
  type BasketballTeam = BasketballTeam.Type

  object BasketballTeam extends Newtype[String] { self =>
    def basketballTeams(implicit loader: ResourceLoader): Seq[BasketballTeam] =
      loader.loadKey[Seq[BasketballTeam]]("basketball.teams")

    implicit def basketballTeamArbitrary(implicit
        loader: ResourceLoader
    ): Arbitrary[BasketballTeam] =
      Arbitrary(Gen.oneOf(basketballTeams))

    implicit val basketballTeamConfigReader: ConfigReader[BasketballTeam] =
      ConfigReader[String].map(self.apply)
  }

  type BasketballPlayer = BasketballPlayer.Type

  object BasketballPlayer extends Newtype[String] { self =>
    def basketballPlayers(implicit
        loader: ResourceLoader
    ): Seq[BasketballPlayer] =
      loader.loadKey[Seq[BasketballPlayer]]("basketball.players")

    implicit def basketballPlayerArbitrary(implicit
        loader: ResourceLoader
    ): Arbitrary[BasketballPlayer] =
      Arbitrary(Gen.oneOf(basketballPlayers))

    implicit val basketballPlayerConfigReader: ConfigReader[BasketballPlayer] =
      ConfigReader[String].map(self.apply)
  }

  type BasketballCoach = BasketballCoach.Type

  object BasketballCoach extends Newtype[String] { self =>
    def basketballCoachs(implicit
        loader: ResourceLoader
    ): Seq[BasketballCoach] =
      loader.loadKey[Seq[BasketballCoach]]("basketball.coaches")

    implicit def basketballCoachArbitrary(implicit
        loader: ResourceLoader
    ): Arbitrary[BasketballCoach] =
      Arbitrary(Gen.oneOf(basketballCoachs))

    implicit val basketballCoachConfigReader: ConfigReader[BasketballCoach] =
      ConfigReader[String].map(self.apply)
  }

  type BasketballPosition = BasketballPosition.Type

  object BasketballPosition extends Newtype[String] { self =>
    def basketballPositions(implicit
        loader: ResourceLoader
    ): Seq[BasketballPosition] =
      loader.loadKey[Seq[BasketballPosition]]("basketball.positions")

    implicit def basketballPositionArbitrary(implicit
        loader: ResourceLoader
    ): Arbitrary[BasketballPosition] =
      Arbitrary(Gen.oneOf(basketballPositions))

    implicit val basketballPositionConfigReader
        : ConfigReader[BasketballPosition] =
      ConfigReader[String].map(self.apply)
  }
}
