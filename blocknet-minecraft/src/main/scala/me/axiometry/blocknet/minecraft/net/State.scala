package me.axiometry.blocknet.minecraft.net

trait State {
  def name: String
  def protocol: Protocol
  def messages: Set[Message]
}