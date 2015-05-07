package me.axiometry.blocknet.protocol

trait Message {
  def name: String
  def direction: Direction
}
object Message {
  object ToClient {

  }
  object ToServer {

  }
}