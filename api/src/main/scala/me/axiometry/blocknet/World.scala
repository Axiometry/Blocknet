package me.axiometry.blocknet

import me.axiometry.blocknet.entity._
import me.axiometry.blocknet.block._

trait World {
  def `type`: WorldType
  def dimension: Dimension
  def difficulty: Difficulty
  def height: Int
  def time: Long
  def age: Long

  def bots: Set[Bot]
  def entities: Set[Entity]

  def blockAt(loc: Location.Block): Block
  def chunkAt(loc: Location.Chunk): Chunk
}