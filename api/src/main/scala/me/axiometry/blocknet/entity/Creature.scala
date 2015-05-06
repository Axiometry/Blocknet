package me.axiometry.blocknet.entity

import me.axiometry.blocknet.item.ItemStack

trait Creature extends Entity {
  def health: Int
  def breathTimer: Int
  def growthTimer: Int
  def potionEffectColor: Int

  def onFire: Boolean
  def crouching: Boolean
  def sprinting: Boolean
  def peformingAction: Boolean

  def headYaw: Double
}