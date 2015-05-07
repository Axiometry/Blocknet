package me.axiometry.blocknet

sealed abstract class Difficulty
object Difficulty {
  val values: Set[Difficulty] = Set(Peaceful, Easy, Normal, Hard)

  case object Peaceful extends Difficulty
  case object Easy extends Difficulty
  case object Normal extends Difficulty
  case object Hard extends Difficulty
}