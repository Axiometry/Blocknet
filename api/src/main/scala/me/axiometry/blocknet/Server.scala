package me.axiometry.blocknet

import me.axiometry.blocknet._

case class ServerAddress(host: String, port: Int)
trait Server {
  val address: ServerAddress

  def worlds: Set[World]
  def createBot(): Bot
}