package me.axiometry.blocknet

sealed abstract class GameMode
object GameMode {
  val values: Set[GameMode] = Set(Survival, Creative, Adventure)

  case object Survival extends GameMode
  case object Creative extends GameMode
  case object Adventure extends GameMode
}