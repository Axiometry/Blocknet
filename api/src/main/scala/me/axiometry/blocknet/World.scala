package me.axiometry.blocknet

import me.axiometry.blocknet.entity._
import me.axiometry.blocknet.block._
import me.axiometry.blocknet.item._

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
     * Set the block state at the specified block location.
     */
    def update(loc: Location.Block, block: BlockState)
  }
  trait ChunkAccess {
    /**
     * Retrieve the chunk at the specified chunk location.
     */
    def apply(loc: Location.Chunk): Chunk

    /**
     * Set the chunk at the specified chunk location.
     */
    def update(loc: Location.Chunk, chunk: Chunk)
  }

  /**
   * The terrain of the world.
   */
  def terrain: Terrain

  /**
   * Set the terrain of the world.
   */
  def terrain_=(terrain: Terrain)

  /**
   * The dimension of the world.
   */
  def dimension: Dimension

  /**
   * Set the dimension of the world.
   */
  def dimension_=(dimension: Dimension)

  /**
   * The difficulty of the world.
   */
  def difficulty: Difficulty

  /**
   * Set the difficulty of the world.
   */
  def difficult_=(difficulty: Difficulty)

  /**
   * The height of the world.
   */
  def height: Int

  /**
   * Set the height of the world.
   */
  def height_=(height: Int)

  /**
   * The time of day of the world.
   */
  def time: Long

  /**
   * Set the time of day of the world.
   */
  def time_=(time: Long)

  /**
   * The age of the world in ticks.
   */
  def age: Long

  /**
   * Set the age of the world in ticks.
   */
  def age_=(age: Long)

  /**
   * The set of all entities in the world.
   */
  def entities: Set[Entity]

  /**
   * Add the specified entity to the world. The entity must have been created through `createEntity(...)`.
   */
  def entities_+=(entity: Entity)

  /**
   * Remove the specified entity from the world. The entity must have been created through `createEntity(...)`.
   */
  def entities_-=(entity: Entity)

  /**
   * Replace the set of entities in the world. Each entity must have been created through `createEntity(...)`.
   */
  def entities_=(entities: Set[Entity])

  /**
   * Retrieve an entity of the specified ID, or None if not found.
   *
   * @param id The entity's ID
   */
  def entityById(id: Int): Option[Entity]

  /**
   * Create an entity belonging to this world of the specified type with the specified ID and location.
   *
   * @param Type The entity's type
   * @param id The entity's ID
   * @param loc The entity's location
   */
  def createEntity[Type <: Entity](id: Int, loc: Location.Precise): Type

  /**
   * Create an item stack with the specified information.
   *
   * @param item The item of the stack
   * @param data The data value of the stack
   * @param size The size of the stack
   */
  def createItemStack(item: Item, data: Int = 0, size: Int = 1): ItemStack

  /**
   * Returns a BlockAccess instance to get and update blocks in this world.
   */
  def blockAt: BlockAccess

  /**
   * Returns a ChunkAccess instance to get and update blocks in this world.
   */
  def chunkAt: ChunkAccess
}