package me.axiometry.blocknet.entity

import me.axiometry.blocknet._

trait Entity extends PreciseLocatable {
  def id: Int
  def x: Double
  def y: Double
  def z: Double
  def yaw: Double
  def pitch: Double
  def rider: Entity
  def riding: Entity
  def dead: Boolean

  def location = Location.Precise(x, y, z)
  def boundingBox: BoundingBox
}