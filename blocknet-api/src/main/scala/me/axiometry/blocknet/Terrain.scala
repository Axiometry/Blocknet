package me.axiometry.blocknet

/**
 * Represents a Minecraft world type.
 */
sealed abstract class Terrain

object Terrain {
  /**
   * The set of all terrains.
   */
  val values: Set[Terrain] = Set(Default, Flat, Default_1_1, LargeBiomes, Amplified)

  case object Default extends Terrain
  case object Flat extends Terrain
  case object Default_1_1 extends Terrain
  case object LargeBiomes extends Terrain
  case object Amplified extends Terrain
}