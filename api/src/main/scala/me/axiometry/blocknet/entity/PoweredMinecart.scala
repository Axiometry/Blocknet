package me.axiometry.blocknet.entity

trait PoweredMinecart extends Vehicle with Vehicle.Storage {
  override type EntityType <: PoweredMinecart
}