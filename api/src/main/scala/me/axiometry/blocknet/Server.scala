package me.axiometry.blocknet

import me.axiometry.blocknet._

final case class ServerAddress(host: String, port: Int)
trait Server {
  def address: ServerAddress

  def worlds: Set[World]
  def worlds_+=(world: World): Server
  def worlds_-=(world: World): Server
  def worlds_=(worlds: Set[World]): Server
  def replaceWorld(original: World, updated: World): Server
  def createWorld(): World

  def bots: Set[Bot]
  def bots_+=(bot: Bot): Server
  def bots_-=(bot: Bot): Server
  def bots_=(bots: Set[Bot]): Server
  def replaceBot(original: Bot, updated: Bot): Server
  def createBot(): Bot
}
object Server {
  val DEFAULT_PORT = 25565
}
package object blocknet {
  private[this] final val AddressPattern = "([^:]+)(:([0-9]{1,5}))?".r
  implicit def string2Address(desc: String) = desc match {
    case AddressPattern(host, _, port) => ServerAddress(host, port.toInt)
    case AddressPattern(host, x, y) => println(s"$x / $y"); ServerAddress(host, 25565)
  }
}