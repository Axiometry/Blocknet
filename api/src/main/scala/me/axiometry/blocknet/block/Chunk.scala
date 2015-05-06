package me.axiometry.blocknet.block

import me.axiometry.blocknet._

trait Chunk extends ChunkLocatable {
  trait BlockAccess {
    def apply(loc: Location.Block): BlockState
    def update(loc: Location.Block, block: BlockState): Chunk
  }

  def world: World

  def blockAt: BlockAccess
}