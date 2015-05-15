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
   * The connection to the Minecraft server.
   */
  def protocol: Protocol

  /**
   * The Server instance to which this bot belongs.
   */
  def server: Server

  /**
   * The entity instance of this bot, or None if this bot has not spawned.
   */
  def player: Option[BotPlayer]

  /**
   * Set the entity instance of this bot. Some if spawned, None otherwise.
   */
  def player_=(player: Option[BotPlayer])
}