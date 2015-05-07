package me.axiometry.blocknet.entity

trait Spider extends Monster {
  override type EntityType <: Spider

  def aggravated: Boolean
  def aggravated_=(aggravated: Boolean): EntityType
}