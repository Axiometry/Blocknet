package me.axiometry.blocknet.entity

trait Vehicle extends Entity
object Vehicle {
  trait Rideable extends Vehicle
  trait Storage extends Vehicle
}