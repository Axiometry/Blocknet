package me.axiometry.blocknet.entity

import me.axiometry.blocknet.block.Block

trait FallingBlock extends Entity {
  override type EntityType <: FallingBlock

  def block: Block
  def block_=(block: Block): EntityType
}