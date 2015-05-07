package me.axiometry.blocknet.entity

import me.axiometry.blocknet.item.ItemStack

trait Enderman extends Monster {
  override type EntityType <: Enderman

  def aggravated: Boolean
  def heldItem: Option[ItemStack]

  def aggravated_=(aggravated: Boolean): EntityType
  def heldItem_=(heldItem: Option[ItemStack]): EntityType
}