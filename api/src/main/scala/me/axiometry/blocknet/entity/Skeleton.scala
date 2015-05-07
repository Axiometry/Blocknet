package me.axiometry.blocknet.entity

trait Skeleton extends Monster with Humanoid {
  override type EntityType <: Skeleton
}