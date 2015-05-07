package me.axiometry.blocknet.entity

trait StorageMinecart extends Vehicle with Vehicle.Storage {
  override type EntityType <: StorageMinecart
}