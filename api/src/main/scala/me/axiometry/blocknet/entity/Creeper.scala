package me.axiometry.blocknet.entity

trait Creeper extends Monster {
  def charged: Boolean
  def ignited: Boolean

  def charged_=(charged: Boolean)
  def ignited_=(ignited: Boolean)
}