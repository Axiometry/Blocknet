package me.axiometry.blocknet.minecraft.net

trait Packet {
  def id: Int
  def state: State
  def direction: Direction

  def message: Message
}