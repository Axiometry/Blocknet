package me.axiometry.blocknet

sealed abstract class Dimension
object Dimension {
  val values: Set[Dimension] = Set(Nether, Overworld, End)

  case object Nether extends Dimension
  case object Overworld extends Dimension
  case object End extends Dimension
}