package me.axiometry.blocknet.protocol

import me.axiometry.blocknet.Server
import me.axiometry.blocknet.Bot

trait Protocol {
  def versionNames: Set[String]

  def state: State
  def blockRegistry: BlockRegistry

  def process(server: Server, bot: Bot): Server

  def send(message: Message)
  def connected: Boolean
}