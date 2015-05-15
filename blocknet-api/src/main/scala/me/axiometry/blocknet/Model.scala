package me.axiometry.blocknet

final case class Model(val boxes: List[BoundingBox]) {
  def intersects(model: Model) = boxes find (box => model.boxes find (_ intersects box) isDefined) isDefined
  def contains(model: Model) = boxes find (box => model.boxes find (_ contains box) isEmpty) isEmpty
}