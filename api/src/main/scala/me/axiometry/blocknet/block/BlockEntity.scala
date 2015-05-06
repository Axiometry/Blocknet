package me.axiometry.blocknet.block

import me.axiometry.blocknet._

trait BlockEntity extends BlockLocatable {
  def world: World
}