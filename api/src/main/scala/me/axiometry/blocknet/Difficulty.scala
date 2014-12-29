package me.axiometry.blocknet

sealed abstract class Difficulty(val id: Int)
object Difficulty {
  val values: Set[Difficulty] = Set(Peaceful, Easy, Normal, Hard)
  def byId(id: Int): Option[Difficulty] = values find (_.id == id)

  case object Peaceful extends Difficulty(0)
  case object Easy extends Difficulty(1)
  case object Normal extends Difficulty(2)
  case object Hard extends Difficulty(3)
}