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

  def health_=(health: Int)
  def breathTimer_=(breathTimer: Int)
  def growthTimer_=(growthTimer: Int)
  def potionEffectColor_=(potionEffectColor: Int)

  def onFire_=(onFire: Boolean)
  def crouching_=(crouching: Boolean)
  def sprinting_=(sprinting: Boolean)
  def performingAction_=(performingAction: Boolean)

  def headYaw_=(headYaw: Double)
}