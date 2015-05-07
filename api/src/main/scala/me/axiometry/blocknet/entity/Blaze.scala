package me.axiometry.blocknet.entity

trait Blaze extends Monster {
  override type EntityType <: Blaze

  def flaming: Boolean
  def flaming_=(flaming: Boolean): EntityType
}