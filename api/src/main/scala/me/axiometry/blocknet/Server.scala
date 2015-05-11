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
   * Produce a new server with specified world added.
   */
  def worlds_+=(world: World): Server

  /**
   * Produce a new server with the specified world removed.
   */
  def worlds_-=(world: World): Server

  /**
   * Produce a new server with the specified set of worlds.
   */
  def worlds_=(worlds: Set[World]): Server

  /**
   * Produce a new server with the specified world replaced.
   */
  def replaceWorld(original: World, updated: World): Server

  /**
   * Create a new World instance belonging to this server.
   */
  def createWorld(): World

  /**
   * The set of all the bots belonging to this server.
   */
  def bots: Set[Bot]

  /**
   * Produce a new server with the specified bot added.
   */
  def bots_+=(bot: Bot): Server

  /**
   * Produce a new server with the specified bot removed.
   */
  def bots_-=(bot: Bot): Server

  /**
   * Produce a new server with the specified set of bots.
   */
  def bots_=(bots: Set[Bot]): Server

  /**
   * Produce a new server with the specified bot replaced.
   */
  def replaceBot(original: Bot, updated: Bot): Server

  /**
   * Create a new Bot instance belonging to this server.
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