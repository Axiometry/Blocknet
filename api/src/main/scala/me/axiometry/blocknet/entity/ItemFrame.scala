package me.axiometry.blocknet.entity

import me.axiometry.blocknet.item.ItemStack
import me.axiometry.blocknet.block.BlockFace

trait ItemFrame extends Entity {
  def direction: BlockFace
  def item: Option[ItemStack]

  def direction_=(direction: BlockFace)
  def item_=(item: Option[ItemStack])
}