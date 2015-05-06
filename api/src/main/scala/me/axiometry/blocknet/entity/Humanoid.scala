package me.axiometry.blocknet.entity

import me.axiometry.blocknet.item._

trait Humanoid extends Creature {
  trait Armor {
    def helmet: Option[ItemStack]
    def chestplate: Option[ItemStack]
    def leggings: Option[ItemStack]
    def boots: Option[ItemStack]
  }
  def armor: Armor
  def heldItem: Option[ItemStack]
}