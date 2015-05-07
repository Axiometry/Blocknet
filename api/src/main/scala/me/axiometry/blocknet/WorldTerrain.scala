package me.axiometry.blocknet

sealed abstract class WorldTerrain(val name: String)
object WorldTerrain {
  val values: Set[WorldTerrain] = Set(Default, Flat, Default_1_1, LargeBiomes, Amplified)
  def byName(name: String): Option[WorldTerrain] = values find (_.name == name)

  case object Default extends WorldTerrain("default")
  case object Flat extends WorldTerrain("flat")
  case object Default_1_1 extends WorldTerrain("default_1_1")
  case object LargeBiomes extends WorldTerrain("largeBiomes")
  case object Amplified extends WorldTerrain("amplified")
}