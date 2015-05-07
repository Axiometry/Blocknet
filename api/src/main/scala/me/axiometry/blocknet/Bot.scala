package me.axiometry.blocknet

import me.axiometry.blocknet.entity._
import me.axiometry.blocknet.block._
import me.axiometry.blocknet.protocol.Protocol

trait Bot extends Player {
  def player: Option[BotPlayer]
  def player_=(player: Option[BotPlayer]): Bot

  def protocol: Protocol
  def server: Server
}