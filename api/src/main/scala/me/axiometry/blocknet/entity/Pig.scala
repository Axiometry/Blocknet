package me.axiometry.blocknet.entity

trait Pig extends Animal with Animal.Rideable {
  override type EntityType <: Pig
}