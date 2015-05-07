package me.axiometry.blocknet.entity

import me.axiometry.blocknet._
import me.axiometry.blocknet.item.Window

trait BotPlayer extends Player {
  override type EntityType <: BotPlayer

  def bot: Bot

  def window: Window
  def gameMode: GameMode
  def hunger: Int
  def level: Int
  def experience: Int

  def window_=(window: Window): EntityType
  def gameMode_=(gameMode: GameMode): EntityType
  def hunger_=(hunger: Int): EntityType
  def level_=(level: Int): EntityType
  def experience_=(experience: Int): EntityType

  def swingArm()
  def closeWindow(): BotPlayer
}