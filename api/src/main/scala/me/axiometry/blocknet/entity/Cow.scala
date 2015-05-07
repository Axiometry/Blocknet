package me.axiometry.blocknet.entity

trait Cow extends Animal {
  override type EntityType <: Cow
}