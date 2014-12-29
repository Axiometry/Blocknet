package me.axiometry.blocknet.block

import me.axiometry.blocknet._

trait Block extends BlockLocatable {
  def world: World
  def chunk: Chunk

  def id: Int
  def meta: Int
  def light: Int
  def tileEntity: TileEntity

  def id_=(id: Int)
  def meta_=(meta: Int)
  def light_=(light: Int)
  def tileEntity_=(tileEntity: TileEntity)
}