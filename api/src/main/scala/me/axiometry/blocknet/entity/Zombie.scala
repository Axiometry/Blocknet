package me.axiometry.blocknet.entity

trait Zombie extends Monster with Humanoid {
  override type EntityType <: Zombie
}