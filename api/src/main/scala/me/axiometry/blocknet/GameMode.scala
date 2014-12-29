package me.axiometry.blocknet

sealed abstract class GameMode(val id: Int, val name: String)
object GameMode {
  val values: Set[GameMode] = Set(Survival, Creative, Adventure)
  def byId(id: Int): Option[GameMode] = values find (_.id == id)
  def byName(name: String): Option[GameMode] = values find (_.name == name)

  case object Survival extends GameMode(0, "survival")
  case object Creative extends GameMode(1, "creative")
  case object Adventure extends GameMode(2, "adventure")
}