package me.axiometry.blocknet.block

import me.axiometry.blocknet._

trait BlockState extends BlockLocatable {
  def world: World
  def chunk: Chunk

  def block: Block
  def data: Option[Block.Data]
  def light: Int
  def entity: Option[BlockEntity]

  def withBlock(block: Block): BlockState
  def withData(data: Option[Block.Data]): BlockState
  def withLight(light: Int): BlockState
  def withEntity(entity: Option[BlockEntity]): BlockState

  def createEntity[Type <: BlockEntity](): BlockEntity
}