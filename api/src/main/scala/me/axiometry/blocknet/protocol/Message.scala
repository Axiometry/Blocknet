package me.axiometry.blocknet.protocol

trait Message {
  def name: String
  def direction: Direction
}