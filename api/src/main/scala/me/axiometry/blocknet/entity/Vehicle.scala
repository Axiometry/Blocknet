package me.axiometry.blocknet.entity

trait Vehicle extends Entity {
  override type EntityType <: Vehicle
}
object Vehicle {
  trait Rideable extends Vehicle {
    override type EntityType <: Rideable
  }
  trait Storage extends Vehicle {
    override type EntityType <: Storage
  }
}