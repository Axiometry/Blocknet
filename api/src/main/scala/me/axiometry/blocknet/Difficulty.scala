package me.axiometry.blocknet

/**
 * Represents a Minecraft difficulty setting.
 */
sealed abstract class Difficulty

object Difficulty {
  /**
   * The set of all difficulties.
   */
  val values: Set[Difficulty] = Set(Peaceful, Easy, Normal, Hard)

  case object Peaceful extends Difficulty
  case object Easy extends Difficulty
  case object Normal extends Difficulty
  case object Hard extends Difficulty
}