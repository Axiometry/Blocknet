package me.axiometry.blocknet.minecraft.net

import me.axiometry.blocknet.block._
import me.axiometry.blocknet.World
import me.axiometry.blocknet.Location

trait BlockRegistry {
  def blockFor(id: Int): Block
  def blockFor(name: String): Block

  def factoryFor(block: Block): Option[BlockStateFactory]
  def factoryFor[T <: Block.Data](block: Block with Block.DataType { type Data = T }): Option[DataBlockStateFactory { type DataType = T }]
}

trait BlockStateFactory {
  def block: Block
  def createBlockState(world: World, location: Location.Precise): BlockState
}

trait DataBlockStateFactory {
  type DataType <: Block.Data
  def block: Block with Block.DataType { type Data = DataType }
  def createBlockState(world: World, location: Location.Precise, data: DataType): Block
}
