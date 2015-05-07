package me.axiometry.blocknet.entity

trait Boat extends Vehicle with Vehicle.Rideable {
  override type EntityType <: Boat
}