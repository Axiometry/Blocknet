package me.axiometry.blocknet.entity

import me.axiometry.blocknet.Location
import me.axiometry.blocknet.block.BlockFace

trait Painting extends Entity {
  sealed abstract class Art(name: String)
  object Art {
    case object Kebab extends Art("Kebab")
    case object Aztec extends Art("Aztec")
    case object Alban extends Art("Alban")
    case object Aztec2 extends Art("Aztec2")
    case object Bomb extends Art("Bomb")
    case object Plant extends Art("Plant")
    case object Wasteland extends Art("Wasteland")
    case object Pool extends Art("Pool")
    case object Courbet extends Art("Courbet")
    case object Sea extends Art("Sea")
    case object Sunset extends Art("Sunset")
    case object Creebet extends Art("Creebet")
    case object Wanderer extends Art("Wanderer")
    case object Graham extends Art("Graham")
    case object Match extends Art("Match")
    case object Bust extends Art("Bust")
    case object Stage extends Art("Stage")
    case object Void extends Art("Void")
    case object SkullAndRoses extends Art("SkullAndRoses")
    case object Fighters extends Art("Fighters")
    case object Pointer extends Art("Pointer")
    case object PigScene extends Art("Pigscene")
    case object BurningSkull extends Art("BurningSkull")
    case object Skeleton extends Art("Skeleton")
    case object DonkeyKong extends Art("DonkeyKong")
  }

  def art: Art
  def block: Location.Block
  def direction: BlockFace
}