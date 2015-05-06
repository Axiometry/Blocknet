package me.axiometry.blocknet.entity

import me.axiometry.blocknet.block.Block

trait FallingBlock extends Entity {
  def block: Block
}