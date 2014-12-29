package me.axiometry.blocknet.entity

import me.axiometry.blocknet._

trait Entity extends PreciseLocatable {
  def x: Double
  def y: Double
  def z: Double
  def yaw: Double
  def pitch: Double
  def rider: Entity
  def riding: Entity
  def dead: Boolean

  def x_=(x: Double)
  def y_=(y: Double)
  def z_=(z: Double)
  def yaw_=(yaw: Double)
  def pitch_=(pitch: Double)
  def rider_=(rider: Entity)
  def riding_=(riding: Entity)
  def dead_=(dead: Boolean)

  def face(loc: Location.Precise)
  def face(loc: Location.Block, face: Int)

  def location = Location.Precise(x, y, z)
  def location_=(loc: Location.Precise) {
    x = loc.x
    y = loc.y
    z = loc.z
  }
}