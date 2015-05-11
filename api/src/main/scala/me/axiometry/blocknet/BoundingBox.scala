package me.axiometry.blocknet

import Location.{Precise => Pos}

/**
 * An axis-aligned (non-rotated) rectangular prism representing the bounds for some game object.
 * This is useful for handling the simplistic collision detection of Minecraft.
 *
 * @define $negativeSizes Note that a bounding box size along an axis will never go negative; an
 * offset that results in a negative size will be trimmed so that the min and max along that
 * axis are equal and are at the midpoint of the original axis coverage.
 */
final case class BoundingBox private (min: Pos, max: Pos) {
  /**
   * Produce a new bounding box which has been contracted by the specified amounts.
   *
   * $negativeSizes
   *
   * Negative edge sizes will be handled as an expansion.
   *
   * @param offX The amount to decrease the x-axis by
   * @param offY The amount to decrease the y-axis by
   * @param offZ the amount to decrease the z-axis by
   */
  def contract(offX: Double, offY: Double, offZ: Double) = expand(-offX, -offY, -offZ)

  /**
   * Produce a new bounding box which has been expanded by the specified amounts.
   *
   * $negativeSizes
   *
   * Negative edge sizes will be handled as a contraction.
   *
   * @param offX The amount to increase the x-axis by
   * @param offY The amount to increase the y-axis by
   * @param offZ The amount to increase the z-axis by
   */
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

  /**
   * Produce a new bounding box which has been offset by the specified amounts.
   *
   * @param offX The amount to offset the x-axis by
   * @param offY The amount to offset the y-axis by
   * @param offZ The amount to offset the z-axis by
   */
  def offset(offX: Double, offY: Double, offZ: Double): BoundingBox = offset(Pos(offX, offY, offZ))

  /**
   * Produce a new bounding box which has been offset by the specified position amounts.
   *
   * @param off The amounts to offset the x, y, and z components by
   */
  def offset(off: Pos) = off match {
    case Pos(0, 0, 0) => this
    case off => BoundingBox(min + off, max + off)
  }

  /**
   * Determine if this bounding box intersects the specified bounding box.
   *
   * Bounding boxes are considered to be intersecting if and only if the
   * line segments projected along each respective axis share a non-zero
   * distance. That is, the intersection between the two bounding boxes
   * exists and has non-zero area
   *
   * @param box The bounding box to check
   */
  def intersects(box: BoundingBox) =
    (min.x < box.max.x && max.x > box.min.x) &&
    (min.y < box.max.y && max.y > box.min.y) &&
    (min.z < box.max.z && max.z > box.min.z)

  /**
   * Determine if this bounding box contains the specified point.
   *
   * A bounding box contains a point if and only if each component
   * of the point is between the two endpoints of the bounding box
   * along the respective axis (exclusively). If the point is along
   * an face, edge, or vertex of a bounding box, it is not considered
   * to be contained.
   *
   * @param pos The point to check
   */
  def contains(pos: Pos) =
    (min.x < pos.x && max.x > pos.x) &&
    (min.y < pos.y && max.y > pos.y) &&
    (min.z < pos.z && max.z > pos.z)

  /**
   * Determine if this bounding box contains the specified bounding box.
   *
   * A bounding box is considered to be contained within another bounding box
   * if and only if the intersection of that bounding box and the other bounding box is
   * equal to that bounding box.
   *
   * @param box The bounding box to check
   */
  def contains(box: BoundingBox) =
    (min.x <= box.min.x && max.x >= box.max.x) &&
    (min.y <= box.min.y && max.y >= box.max.y) &&
    (min.z <= box.min.z && max.z >= box.max.z)
}

object BoundingBox {
  def apply(minX: Double, minY: Double, minZ: Double, maxX: Double, maxY: Double, maxZ: Double): BoundingBox =
    apply(Pos(minX, minY, minZ), Pos(maxX, maxY, maxZ))
}
