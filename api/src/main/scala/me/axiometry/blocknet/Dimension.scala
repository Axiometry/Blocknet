package me.axiometry.blocknet

sealed abstract class Dimension(val id: Int)
object Dimension {
  val values: Set[Dimension] = Set(Nether, Overworld, End)
  def byId(id: Int): Option[Dimension] = values find (_.id == id)

  case object Nether extends Dimension(-1)
  case object Overworld extends Dimension(0)
  case object End extends Dimension(1)
}