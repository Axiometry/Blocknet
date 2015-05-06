package me.axiometry.blocknet.entity

trait Vehicle extends Entity
object Vehicle {
  trait Rideable extends Vehicle
  trait Storage extends Vehicle

  trait Boat extends Rideable
  trait Minecart extends Rideable
  trait StorageMinecart extends Storage
  trait PoweredMinecart extends Storage
}