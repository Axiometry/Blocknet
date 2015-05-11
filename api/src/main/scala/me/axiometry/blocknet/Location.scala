package me.axiometry.blocknet

import scala.math._

object Location {
  protected[this] trait Conversions {
    /**
     * Convert this location into a precise location.
     */
    def toPrecise(): Precise

    /**
     * Convert this location into a block location.
     */
    def toBlock(): Block

    /**
     * Convert this location into a chunk location.
     */
    def toChunk(): Chunk
  }

  protected[this] trait ExactConversions {
    /**
     * Convert this location into a precise location exactly.
     */
    def toPreciseExact(): Precise

    /**
     * Convert this location into a block location exactly.
     */
    def toBlockExact(): Block

    /**
     * Convert this location into a chunk location exactly.
     */
    def toChunkExact(): Chunk
  }

  /**
   * Represents a specific, double-precision location of in a Minecraft world.
   */
  final case class Precise(x: Double, y: Double, z: Double) extends Conversions with ExactConversions {
    if(x.isNaN || y.isNaN || z.isNaN || x.isInfinity || y.isInfinity || z.isInfinity)
      throw new IllegalArgumentException("invalid coordinates")

    def +(loc: Precise) = Precise(x + loc.x, y + loc.y, z + loc.z)
    def -(loc: Precise) = Precise(x - loc.x, y - loc.y, z - loc.z)

    /**
     * The distance between this precise location and another precise location.
     *
     * @param loc The other precise location
     */
    def distanceTo(loc: Precise) = sqrt(pow(x - loc.x, 2) + pow(y - loc.y, 2) + pow(z - loc.z, 2))

    /**
     * The squared distance between this precise location and another precise location.
     *
     * @param loc The other precise location
     */
    def distanceSquaredTo(loc: Precise) = pow(x - loc.x, 2) + pow(y - loc.y, 2) + pow(z - loc.z, 2)

    /**
     * @inheritdoc
     *
     * The precise location representation of a precise location is simply itself.
     */
    override def toPrecise() = this

    /**
     * @inheritdoc
     *
     * The block location representation of a precise location is the floor of the components.
     * The block location refers to the block which contains this precise location.
     */
    override def toBlock() = Block(x.floor.toInt, y.floor.toInt, z.floor.toInt)

    /**
     * @inheritdoc
     *
     * The chunk location representation of a precise location is the floor of the components / 16.
     * The chunk location refers to the chunk which contains this precise location.
     */
    override def toChunk() = Chunk(x.floor.toInt >> 4, y.floor.toInt >> 4, z.floor.toInt >> 4)

    /**
     * @inheritdoc
     *
     * The exact precise location representation of a precise location is simply itself.
     */
    override def toPreciseExact() = this

    /**
     * @inheritdoc
     *
     * The exact block location representation of a precise location is the floor of the components.
     * The block location refers to the block which contains this precise location. This is the
     * same as `toBlock()`
     */
    override def toBlockExact() = toBlock

    /**
     * @inheritdoc
     *
     * The exact chunk location representation of a precise location is the lfoor of the components / 16.
     * The chunk location refers to the chunk which contains this precise location. This is the
     * same as `toChunk()`
     */
    override def toChunkExact() = toChunk
  }

  /**
   * Represents a location of a block in a Minecraft world.
   */
  final case class Block(x: Integer, y: Integer, z: Integer) extends Conversions with ExactConversions {
    def +(loc: Block) = Block(x + loc.x, y + loc.y, z + loc.z)
    def -(loc: Block) = Block(x - loc.x, y - loc.y, z - loc.z)

    /**
     * The distance between this block location and another block location.
     *
     * @param loc The other block location
     */
    def distanceTo(loc: Block) = sqrt(pow(x - loc.x, 2) + pow(y - loc.y, 2) + pow(z - loc.z, 2))

    /**
     * The squared distance between this block location and another block location.
     *
     * @param loc The other block location
     */
    def distanceSquaredTo(loc: Block) = pow(x - loc.x, 2) + pow(y - loc.y, 2) + pow(z - loc.z, 2)

    /**
     * @inheritdoc
     *
     * The precise location representation of a block location is the midpoint of the block
     * to which the block location refers.
     */
    override def toPrecise() = Precise(x + 0.5, y + 0.5, z + 0.5)

    /**
     * @inheritdoc
     *
     * The block location representation of a block location is simply itself.
     */
    override def toBlock() = this

    /**
     * @inheritdoc
     *
     * The chunk location representation of a block location is the components / 16.
     * The chunk location refers to the chunk that contains this block location.
     */
    override def toChunk() = Chunk(x >> 4, y >> 4, z >> 4)

    /**
     * @inheritdoc
     *
     * The exact precise location representation of a block location is bottom corner representing
     * the integer coordinates as doubles.
     */
    override def toPreciseExact() = Precise(x.toDouble, y.toDouble, z.toDouble)

    /**
     * @inheritdoc
     *
     * The exact block location representation of a block location is simply itself.
     */
    override def toBlockExact() = this

    /**
     * @inheritdoc
     *
     * The chunk location representation of a block location is the components / 16.
     * The chunk location refers to the chunk that contains this block location. This
     * is the same as `toChunk()`
     */
    override def toChunkExact() = toChunk
  }

  /**
   * Represents a location of a chunk (a 16x16x16 section of blocks) in a Minecraft world.
   */
  final case class Chunk(x: Integer, y: Integer, z: Integer) extends Conversions with ExactConversions {
    def +(loc: Chunk) = Chunk(x + loc.x, y + loc.y, z + loc.z)
    def -(loc: Chunk) = Chunk(x - loc.x, y - loc.y, z - loc.z)

    /**
     * The distance between this chunk location and another chunk location.
     *
     * This is calculated in terms of chunks.
     *
     * @param loc The other chunk location
     */
    def distanceTo(loc: Chunk) = sqrt(pow(x - loc.x, 2) + pow(y - loc.y, 2) + pow(z - loc.z, 2))

    /**
     * The squared distance between this chunk location and another chunk location.
     *
     * This is calculated in terms of chunks.
     *
     * @param loc The other chunk location
     */
    def distanceSquaredTo(loc: Chunk) = pow(x - loc.x, 2) + pow(y - loc.y, 2) + pow(z - loc.z, 2)

    /**
     * @inheritdoc
     *
     * The precise location representation of a chunk location is the components * 16 + 8.
     * The precise location refers to the midpoint of the chunk to which this chunk
     * location refers.
     */
    override def toPrecise() = Precise((x << 4) + 8, (y << 4) + 8, (z << 4) + 8)

    /**
     * @inheritdoc
     *
     * The block location representation of a chunk location is the components * 16 + 8.
     * The block location refers to the middle block of the chunk to which this chunk
     * location refers.
     */
    override def toBlock() = Block((x << 4) + 8, (y << 4) + 8, (z << 4) + 8)

    /**
     * @inheritdoc
     *
     * The chunk location representation of a chunk location is simply itself.
     */
    override def toChunk() = this

    /**
     * @inheritdoc
     *
     * The exact precise location representation of a chunk location is the components * 16.
     * The precise location refers to the bottom corner of the chunk.
     */
    override def toPreciseExact() = Precise(x << 4, y << 4, z << 4)

    /**
     * @inheritdoc
     *
     * The exact block location representation of a chunk location is the components * 16.
     * The block location refers to the bottom corner of the chunk.
     */
    override def toBlockExact() = Block(x << 4, y << 4, z << 4)

    /**
     * @inheritdoc
     *
     * The exact chunk location representation of a chunk location is simply itself.
     */
    override def toChunkExact() = this
  }

  protected[this] trait extraImplicits {
    implicit def tuple2Precise(tuple: (Double, Double, Double)) = Precise(tuple._1, tuple._2, tuple._3)
  }

  /**
   * Implicit conversions between the location types
   */
  object implicits extends extraImplicits {
    implicit def convert2Precise(loc: Conversions): Precise = loc.toPrecise
    implicit def convert2Block(loc: Conversions): Block = loc.toBlock
    implicit def convert2Chunk(loc: Conversions): Chunk = loc.toChunk
  }

  /**
   * Exact implicit conversions between the location types.
   */
  object exactImplicits extends extraImplicits {
    implicit def convert2PreciseExact(loc: ExactConversions): Precise = loc.toPreciseExact
    implicit def convert2BlockExact(loc: ExactConversions): Block = loc.toBlockExact
    implicit def convert2ChunkExact(loc: ExactConversions): Chunk = loc.toChunkExact
  }
}

/**
 * Represents an object that has a precise location within a Minecraft world.
 */
trait PreciseLocatable {
  def location: Location.Precise

  implicit def convert2Location() = location
}

/**
 * Represents an object that has a block location within a Minecraft world.
 */
trait BlockLocatable {
  def location: Location.Block

  implicit def convert2Location() = location
}

/**
 * Represents an object that has a chunk location within a Minecraft world.
 */
trait ChunkLocatable {
  def location: Location.Chunk

  implicit def convert2Chunk() = location
}
