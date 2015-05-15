package me.axiometry.blocknet.protocol

trait Packet {
  def id: Int
  def state: State
  def direction: Direction

  def message: Message
}