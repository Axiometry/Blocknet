package me.axiometry.blocknet.block

import me.axiometry.blocknet._

trait Chunk extends ChunkLocatable {
  def world: World

  def blockAt(loc: Location.Block): Block
}