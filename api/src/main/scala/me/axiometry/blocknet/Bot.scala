package me.axiometry.blocknet

import me.axiometry.blocknet.entity._
import me.axiometry.blocknet.block._
import me.axiometry.blocknet.protocol.Protocol

trait Bot extends Player {
  def world: World
  def gameMode: GameMode
  def hunger: Int
  def level: Int
  def experience: Int
  def totalExperience: Int
  def experienceToNextLevel: Int
  def sneaking: Boolean
  def sprinting: Boolean

  def protocol: Protocol

  def swingArm()
  def closeWindow()
}