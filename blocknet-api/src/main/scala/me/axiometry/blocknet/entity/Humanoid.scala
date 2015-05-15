package me.axiometry.blocknet.entity

import me.axiometry.blocknet.item._

trait Humanoid extends Creature {
  trait Armor {
    def helmet: Option[ItemStack]
    def chestplate: Option[ItemStack]
    def leggings: Option[ItemStack]
    def boots: Option[ItemStack]

    def helmet_=(helmet: Option[ItemStack]): Armor
    def chestplate_=(chestplate: Option[ItemStack]): Armor
    def leggings_=(leggings: Option[ItemStack]): Armor
    def boots_=(boots: Option[ItemStack]): Armor
  }

  def armor: Armor
  def heldItem: Option[ItemStack]

  def armor_=(armor: Armor)
  def heldItem_=(heldItem: Option[ItemStack])
}