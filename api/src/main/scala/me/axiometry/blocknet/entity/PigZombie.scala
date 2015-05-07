package me.axiometry.blocknet.entity

trait PigZombie extends Monster with Humanoid {
  override type EntityType <: PigZombie
}