package me.axiometry.blocknet

import scala.math._

object Location {
  protected[this] trait Conversions {
    def toPrecise(): Precise
    def toBlock(): Block
    def toChunk(): Chunk
  }
  protected[this] trait ExactConversions {
    def toPreciseExact(): Precise
    def toBlockExact(): Block
    def toChunkExact(): Chunk
  }
  final case class Precise private (x: Double, y: Double, z: Double) extends Conversions with ExactConversions {
    if(x.isNaN || y.isNaN || z.isNaN || x.isInfinity || y.isInfinity || z.isInfinity)
      throw new IllegalArgumentException("invalid coordinates")

    def +(loc: Precise) = Precise(x + loc.x, y + loc.y, z + loc.z)
    def -(loc: Precise) = Precise(x - loc.x, y - loc.y, z - loc.z)

    def distanceTo(loc: Precise) = sqrt(pow(x - loc.x, 2) + pow(y - loc.y, 2) + pow(z - loc.z, 2))
    def distanceSquaredTo(loc: Precise) = pow(x - loc.x, 2) + pow(y - loc.y, 2) + pow(z - loc.z, 2)

    override def toPrecise() = this
    override def toBlock() = Block(x.toInt, y.toInt, z.toInt)
    override def toChunk() = Chunk(x.toInt >> 4, y.toInt >> 4, z.toInt >> 4)
    override def toPreciseExact() = this
    override def toBlockExact() = toBlock
    override def toChunkExact() = toChunk
  }
  final case class Block private (x: Integer, y: Integer, z: Integer) extends Conversions with ExactConversions {
    def +(loc: Block) = Block(x + loc.x, y + loc.y, z + loc.z)
    def -(loc: Block) = Block(x - loc.x, y - loc.y, z - loc.z)

    def distanceTo(loc: Block) = sqrt(pow(x - loc.x, 2) + pow(y - loc.y, 2) + pow(z - loc.z, 2))
    def distanceSquaredTo(loc: Block) = pow(x - loc.x, 2) + pow(y - loc.y, 2) + pow(z - loc.z, 2)

    override def toPrecise() = Precise(x + 0.5, y + 0.5, z + 0.5)
    override def toBlock() = this
    override def toChunk() = Chunk(x >> 4, y >> 4, z >> 4)
    override def toPreciseExact() = Precise(x.toDouble, y.toDouble, z.toDouble)
    override def toBlockExact() = this
    override def toChunkExact() = toChunk
  }
  final case class Chunk private (x: Integer, y: Integer, z: Integer) extends Conversions with ExactConversions {
    def +(loc: Chunk) = Chunk(x + loc.x, y + loc.y, z + loc.z)
    def -(loc: Chunk) = Chunk(x - loc.x, y - loc.y, z - loc.z)

    def distanceTo(loc: Chunk) = sqrt(pow(x - loc.x, 2) + pow(y - loc.y, 2) + pow(z - loc.z, 2))
    def distanceSquaredTo(loc: Chunk) = pow(x - loc.x, 2) + pow(y - loc.y, 2) + pow(z - loc.z, 2)

    override def toPrecise() = Precise((x << 4) + 8, (y << 4) + 8, (z << 4) + 8)
    override def toBlock() = Block((x << 4) + 8, (y << 4) + 8, (z << 4) + 8)
    override def toChunk() = this
    override def toPreciseExact() = Precise(x << 4, y << 4, z << 4)
    override def toBlockExact() = Block(x << 4, y << 4, z << 4)
    override def toChunkExact() = this
  }

  protected[this] trait extraImplicits {
    implicit def tuple2Precise(tuple: (Double, Double, Double)) = Precise(tuple._1, tuple._2, tuple._3)
  }
  object implicits extends extraImplicits {
    implicit def convert2Precise(loc: Conversions): Precise = loc.toPrecise
    implicit def convert2Block(loc: Conversions): Block = loc.toBlock
    implicit def convert2Chunk(loc: Conversions): Chunk = loc.toChunk
  }
  object exactImplicits extends extraImplicits {
    implicit def convert2PreciseExact(loc: ExactConversions): Precise = loc.toPreciseExact
    implicit def convert2BlockExact(loc: ExactConversions): Block = loc.toBlockExact
    implicit def convert2ChunkExact(loc: ExactConversions): Chunk = loc.toChunkExact
  }
}

trait PreciseLocatable {
  def location: Location.Precise

  implicit def convert2Location() = location
}
trait BlockLocatable {
  def location: Location.Block

  implicit def convert2Location() = location
}
trait ChunkLocatable {
  def location: Location.Chunk

  implicit def convert2Chunk() = location
}
