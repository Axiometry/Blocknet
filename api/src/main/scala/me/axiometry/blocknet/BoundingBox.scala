package me.axiometry.blocknet

import Location.{Precise => Pos}

final case class BoundingBox private (min: Pos, max: Pos) {
  def contract(offX: Double, offY: Double, offZ: Double) = expand(-offX, -offY, -offZ)
  def expand(offX: Double, offY: Double, offZ: Double) = {
    def expand(min: Double, max: Double, off: Double) = (min - off, max + off) match {
      case (min, max) if min > max => ((min + max) / 2.0, (min + max) / 2.0)
      case (min, max) => (min, max)
    }
    val (minX, maxX) = expand(min.x, max.x, offX)
    val (minY, maxY) = expand(min.y, max.y, offY)
    val (minZ, maxZ) = expand(min.z, max.z, offZ)
    BoundingBox(Pos(minX, minY, minZ), Pos(maxX, maxY, maxZ))
  }

  def offset(offX: Double, offY: Double, offZ: Double): BoundingBox = offset(Pos(offX, offY, offZ))
  def offset(off: Pos) = off match {
    case Pos(0, 0, 0) => this
    case off => BoundingBox(min + off, max + off)
  }

  def intersects(box: BoundingBox) =
    (min.x < box.max.x && max.x > box.min.x) &&
    (min.y < box.max.y && max.y > box.min.y) &&
    (min.z < box.max.z && max.z > box.min.z)

  def contains(pos: Pos) =
    (min.x < pos.x && max.x > pos.x) &&
    (min.y < pos.y && max.y > pos.y) &&
    (min.z < pos.z && max.z > pos.z)

  def contains(box: BoundingBox) =
    (min.x <= box.min.x && max.x >= box.max.x) &&
    (min.y <= box.min.y && max.y >= box.max.y) &&
    (min.z <= box.min.z && max.z >= box.max.z)
}

object BoundingBox {
  def apply(minX: Double, minY: Double, minZ: Double, maxX: Double, maxY: Double, maxZ: Double): BoundingBox =
    apply(Pos(minX, minY, minZ), Pos(maxX, maxY, maxZ))
}
