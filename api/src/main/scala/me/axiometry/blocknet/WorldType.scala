package me.axiometry.blocknet

sealed abstract class WorldType(val name: String)
object WorldType {
  val values: Set[WorldType] = Set(Default, Flat, Default_1_1, LargeBiomes, Amplified)
  def byName(name: String): Option[WorldType] = values find (_.name == name)

  case object Default extends WorldType("default")
  case object Flat extends WorldType("flat")
  case object Default_1_1 extends WorldType("default_1_1")
  case object LargeBiomes extends WorldType("largeBiomes")
  case object Amplified extends WorldType("amplified")
}