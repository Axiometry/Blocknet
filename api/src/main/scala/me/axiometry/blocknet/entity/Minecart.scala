package me.axiometry.blocknet.entity

trait Minecart extends Vehicle with Vehicle.Rideable {
  override type EntityType <: Minecart
}