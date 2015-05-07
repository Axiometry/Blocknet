package me.axiometry.blocknet

import me.axiometry.blocknet.entity._
import me.axiometry.blocknet.block._
import me.axiometry.blocknet.protocol.Protocol

trait Bot extends Player {
  override type EntityType <: Bot

  def gameMode: GameMode
  def hunger: Int
  def level: Int
  def experience: Int

  def gameMode_=(gameMode: GameMode): EntityType
  def hunger_=(hunger: Int): EntityType
  def level_=(level: Int): EntityType
  def experience_=(experience: Int): EntityType

  def protocol: Protocol

  def swingArm()
  def closeWindow()
}