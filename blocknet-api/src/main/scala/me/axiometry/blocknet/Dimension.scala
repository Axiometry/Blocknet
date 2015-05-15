package me.axiometry.blocknet

/**
 * Represents a Minecraft world dimension.
 */
sealed abstract class Dimension

object Dimension {
  /**
   * The set of all dimensions.
   */
  val values: Set[Dimension] = Set(Nether, Overworld, End)

  case object Nether extends Dimension
  case object Overworld extends Dimension
  case object End extends Dimension
}