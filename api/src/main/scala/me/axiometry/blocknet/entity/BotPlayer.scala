package me.axiometry.blocknet.entity

import me.axiometry.blocknet._
import me.axiometry.blocknet.item._

trait BotPlayer extends Player {
  def bot: Bot

  def window: Window
  def gameMode: GameMode
  def hunger: Int
  def level: Int
  def experience: Int
  def inventory: Inventory

  def window_=(window: Window)
  def gameMode_=(gameMode: GameMode)
  def hunger_=(hunger: Int)
  def level_=(level: Int)
  def experience_=(experience: Int)

  def swingArm()
  def closeWindow()
}