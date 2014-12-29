package me.axiometry.blocknet.block

import me.axiometry.blocknet._

trait TileEntity extends BlockLocatable {
  def world: World
}