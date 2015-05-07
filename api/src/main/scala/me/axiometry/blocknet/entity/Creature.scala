package me.axiometry.blocknet.entity

import me.axiometry.blocknet.item.ItemStack

trait Creature extends Entity {
  override type EntityType <: Creature

  def health: Int
  def breathTimer: Int
  def growthTimer: Int
  def potionEffectColor: Int

  def onFire: Boolean
  def crouching: Boolean
  def sprinting: Boolean
  def peformingAction: Boolean

  def headYaw: Double

  def health_=(health: Int): EntityType
  def breathTimer_=(breathTimer: Int): EntityType
  def growthTimer_=(growthTimer: Int): EntityType
  def potionEffectColor_=(potionEffectColor: Int): EntityType

  def onFire_=(onFire: Boolean): EntityType
  def crouching_=(crouching: Boolean): EntityType
  def sprinting_=(sprinting: Boolean): EntityType
  def performingAction_=(performingAction: Boolean): EntityType

  def headYaw_=(headYaw: Double): EntityType
}