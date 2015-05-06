package me.axiometry.blocknet.entity

import me.axiometry.blocknet.item.ItemStack

trait Enderman extends Monster {
  def aggravated: Boolean
  def heldItem: Option[ItemStack]
}