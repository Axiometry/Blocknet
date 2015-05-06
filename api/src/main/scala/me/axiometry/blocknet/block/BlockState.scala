package me.axiometry.blocknet.block

import me.axiometry.blocknet._

trait BlockState extends BlockLocatable {
  def world: World
  def chunk: Chunk

  def block: Block
  def data: Option[Block.Data]
  def light: Int
  def entity: Option[BlockEntity]

  def block_=(block: Block): BlockState
  def data_=(data: Option[Block.Data]): BlockState
  def light_=(light: Int): BlockState
  def entity_=(entity: Option[BlockEntity]): BlockState
}