package me.axiometry.blocknet

import me.axiometry.blocknet.entity._
import me.axiometry.blocknet.block._

/**
 * Represents a Minecraft world.
 */
trait World {
  trait BlockAccess {
    /**
     * Retrieve the block state at the specified block location.
     */
    def apply(loc: Location.Block): BlockState

    /**
     * Produce a new world with the specified block state.
     */
    def update(loc: Location.Block, block: BlockState): World
  }
  trait ChunkAccess {
    /**
     * Retrieve the chunk at the specified chunk location.
     */
    def apply(loc: Location.Chunk): Chunk

    /**
     * Produce a new world with the specified chunk.
     */
    def update(loc: Location.Chunk, chunk: Chunk): World
  }

  /**
   * The terrain of the world.
   */
  def terrain: Terrain

  /**
   * Produces a new world with the specified terrain.
   */
  def terrain_=(terrain: Terrain): World

  /**
   * The dimension of the world.
   */
  def dimension: Dimension

  /**
   * Produces a new world with the specified dimension.
   */
  def dimension_=(dimension: Dimension): World

  /**
   * The difficulty of the world.
   */
  def difficulty: Difficulty

  /**
   * Produces a new world with the specified difficulty.
   */
  def difficult_=(difficulty: Difficulty): World

  /**
   * The height of the world.
   */
  def height: Int

  /**
   * Produces a new world with the specified height.
   */
  def height_=(height: Int): World

  /**
   * The time of day of the world.
   */
  def time: Long

  /**
   * Produces a new world with the specified time of day.
   */
  def time_=(time: Long): World

  /**
   * The age of the world in ticks.
   */
  def age: Long

  /**
   * Produces a new world with the specified age.
   */
  def age_=(age: Long): World

  /**
   * The set of all entities in the world.
   */
  def entities: Set[Entity]

  /**
   * Produces a new world with the specified entity added.
   */
  def entities_+=(entity: Entity): World

  /**
   * Produces a new world with the specified entity removed.
   */
  def entities_-=(entity: Entity): World

  /**
   * Produces a new world with the specified set of entities.
   */
  def entities_=(entities: Set[Entity]): World

  /**
   * Retrieves an entity of the specified ID, or None if not found.
   *
   * @param id The entity's ID
   */
  def entityById(id: Int): Option[Entity]

  /**
   * Produces a new world with the specified entity replaced.
   */
  def replaceEntity(original: Entity, updated: Entity): World

  /**
   * Creates an entity belonging to this world of the specified type with the specified ID and location.
   *
   * @param Type The entity's type
   * @param id The entity's ID
   * @param loc The entity's location
   */
  def createEntity[Type <: Entity](id: Int, loc: Location.Precise)

  /**
   * Returns a BlockAccess instance to get and update blocks in this world.
   */
  def blockAt: BlockAccess

  /**
   * Returns a ChunkAccess instance to get and update blocks in this world.
   */
  def chunkAt: ChunkAccess
}