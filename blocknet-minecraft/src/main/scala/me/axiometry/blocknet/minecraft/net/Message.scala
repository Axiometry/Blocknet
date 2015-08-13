package me.axiometry.blocknet.minecraft.net

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