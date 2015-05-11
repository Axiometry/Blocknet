package me.axiometry.blocknet

import me.axiometry.blocknet._

/**
 * Represents the IP address of a Minecraft server.
 */
final case class ServerAddress(host: String, port: Int)

/**
 * Represents the known information about a Minecraft server
 * and the collection of bots active on the server.
 *
 * This entire hierarchy is immutable, with the exception of Protocol.
 */
trait Server {
  /**
   * The address of this server.
   */
  def address: ServerAddress

  /**
   * The set of all the worlds belonging to this server.
   */
  def worlds: Set[World]

  /**
   * Add the specified world. The world must have been created through `createWorld()`.
   */
  def worlds_+=(world: World)

  /**
   * Remove the specified world. The world must have been created through `createWorld()`.
   */
  def worlds_-=(world: World)

  /**
   * Replace the set of worlds with the specified set. Each world must have been created by `createWorld()`.
   */
  def worlds_=(worlds: Set[World])

  /**
   * Create a new World instance.
   */
  def createWorld(): World

  /**
   * The set of all the bots belonging to this server.
   */
  def bots: Set[Bot]

  /**
   * Add the specified bot. The bot must have been created through `createBot()`.
   */
  def bots_+=(bot: Bot)

  /**
   * Remove the specified bot. The bot must have been created through `createBot()`.
   */
  def bots_-=(bot: Bot)

  /**
   * Replace the set of bots with the specified set. Each bot must have been created by `createBot()`
   */
  def bots_=(bots: Set[Bot])

  /**
   * Create a new Bot instance.
   *
   * @throws IOException if there was a failure attempting to connect to the server.
   */
  def createBot(): Bot
}
object Server {
  val DEFAULT_PORT = 25565
}
package object blocknet {
  private[this] final val AddressPattern = "([^:]+)(:([0-9]{1,5}))?".r
  implicit def string2Address(desc: String) = desc match {
    case AddressPattern(host, _, port) => ServerAddress(host, port.toInt)
    case AddressPattern(host) => ServerAddress(host, 25565)
  }
}