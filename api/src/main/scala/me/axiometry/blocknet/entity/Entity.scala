package me.axiometry.blocknet.entity

import me.axiometry.blocknet._

trait Entity extends PreciseLocatable {
  type EntityType <: Entity

  def id: Int
  def world: World
  def x: Double
  def y: Double
  def z: Double
  def yaw: Double
  def pitch: Double
  def rider: Entity
  def riding: Entity

  def world_=(world: World): EntityType
  def x_=(x: Double): EntityType
  def y_=(y: Double): EntityType
  def z_=(z: Double): EntityType
  def yaw_=(yaw: Double): EntityType
  def pitch_=(pitch: Double): EntityType
  def rider_=(rider: Entity): EntityType
  def riding_=(riding: Entity): EntityType

  def location = Location.Precise(x, y, z)
  def boundingBox: BoundingBox

  def location_=(location: Location.Precise): EntityType =
    (((x = location.x).y = location.y).z = location.z).asInstanceOf[EntityType]
  def boundingBox_=(boundingBox: BoundingBox): EntityType
}