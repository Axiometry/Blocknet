package me.axiometry.blocknet.entity

trait Creeper extends Monster {
  override type EntityType <: Creeper

  def charged: Boolean
  def ignited: Boolean

  def charged_=(charged: Boolean): EntityType
  def ignited_=(ignited: Boolean): EntityType
}