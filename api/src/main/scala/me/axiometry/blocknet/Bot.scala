package me.axiometry.blocknet

import me.axiometry.blocknet.entity._
import me.axiometry.blocknet.block._
import me.axiometry.blocknet.protocol.Protocol

/**
 * Represents an agent with a session on a Minecraft server.
 * This class is a single player connected to a Minecraft server that is locally controlled.
 *
 * These should be created through `Server.createBot(): Bot`
 */
trait Bot extends Player {
  /**
   * Run a game update and return the result.
   *
   * @return The result of the update
   */
  def process(): Bot

  /**
   * The connection to the Minecraft server.
   */
  def protocol: Protocol

  /**
   * The Server instance to which this bot belongs.
   */
  def server: Server

  /**
   * Produce a new bot with the specified server.
   */
  def server_=(server: Server): Bot

  /**
   * The entity instance of this bot, or None if this bot has not spawned.
   */
  def player: Option[BotPlayer]

  /**
   * Produce a new bot with the specified player.
   */
  def player_=(player: Option[BotPlayer]): Bot
}