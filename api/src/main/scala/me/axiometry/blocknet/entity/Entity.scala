package me.axiometry.blocknet.entity

import me.axiometry.blocknet._

trait Entity extends WorldLocatable with PreciseLocatable {
  def id: Int
  def x: Double
  def y: Double
  def z: Double
  def yaw: Double
  def pitch: Double
  def rider: Entity
  def riding: Entity

  def x_=(x: Double)
  def y_=(y: Double)
  def z_=(z: Double)
  def yaw_=(yaw: Double)
  def pitch_=(pitch: Double)
  def rider_=(rider: Entity)
  def riding_=(riding: Entity)

  override def location = Location.Precise(x, y, z)
  def boundingBox: BoundingBox

  def location_=(location: Location.Precise) = {
    x = location.x
    y = location.y
    z = location.z
  }
  def boundingBox_=(boundingBox: BoundingBox)
}