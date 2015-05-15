package me.axiometry.blocknet

/**
 * Represents a Minecraft game mode setting.
 */
sealed abstract class GameMode

object GameMode {
  /**
   * The set of all game modes.
   */
  val values: Set[GameMode] = Set(Survival, Creative, Adventure)

  case object Survival extends GameMode
  case object Creative extends GameMode
  case object Adventure extends GameMode
}