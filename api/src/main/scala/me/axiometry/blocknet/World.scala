package me.axiometry.blocknet

import me.axiometry.blocknet.entity._
import me.axiometry.blocknet.block._

trait World {
  trait BlockAccess {
    def apply(loc: Location.Block): BlockState
    def update(loc: Location.Block, block: BlockState): World
  }
  trait ChunkAccess {
    def apply(loc: Location.Chunk): Chunk
    def update(loc: Location.Chunk, chunk: Chunk): World
  }

  def terrain: WorldTerrain
  def dimension: Dimension
  def difficulty: Difficulty
  def height: Int
  def time: Long
  def age: Long

  def terrain_=(terrain: WorldTerrain): World
  def dimension_=(dimension: Dimension): World
  def difficult_=(difficulty: Difficulty): World
  def height_=(height: Int): World
  def time_=(time: Long): World
  def age_=(age: Long): World

  def entities: Set[Entity]
  def entities_+=(entity: Entity): World
  def entities_-=(entity: Entity): World
  def entities_=(entities: Set[Entity]): World
  def entityById(id: Int)
  def replaceEntity(original: Entity, updated: Entity): World
  def createEntity[Type <: Entity](id: Int, loc: Location.Precise)

  def blockAt: BlockAccess
  def chunkAt: ChunkAccess
}