package me.axiometry.blocknet.entity

import me.axiometry.blocknet.item.ItemStack
import me.axiometry.blocknet.block.BlockFace

trait ItemFrame extends Entity {
  override type EntityType <: ItemFrame

  def direction: BlockFace
  def item: Option[ItemStack]

  def direction_=(direction: BlockFace): EntityType
  def item_=(item: Option[ItemStack]): EntityType
}