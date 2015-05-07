package me.axiometry.blocknet

import me.axiometry.blocknet._

case class ServerAddress(host: String, port: Int)
trait Server {
  def address: ServerAddress

  def worlds: Set[World]
  def createBot(): Bot
}
object Server {
  val DEFAULT_PORT = 25565
}
package object blocknet {
  private[this] val AddressPattern = "([^:]+)(:([0-9]{1,5}))?".r
  implicit def string2Address(desc: String) = desc match {
    case AddressPattern(host, _, port) => ServerAddress(host, port.toInt)
    case AddressPattern(host, x, y) => println(s"$x / $y"); ServerAddress(host, 25565)
  }
}