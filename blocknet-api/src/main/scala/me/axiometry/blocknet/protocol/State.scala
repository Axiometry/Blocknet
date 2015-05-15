package me.axiometry.blocknet.protocol

trait State {
  def name: String
  def protocol: Protocol
  def messages: Set[Message]
}