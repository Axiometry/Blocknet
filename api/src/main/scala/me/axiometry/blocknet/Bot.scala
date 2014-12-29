package me.axiometry.blocknet

import me.axiometry.blocknet.entity._
import me.axiometry.blocknet.block._

trait Bot extends Entity {
  def world: World
  def gameMode: GameMode
  def hunger: Int
  def level: Int
  def experience: Int
  def totalExperience: Int
  def experienceToNextLevel: Int
  def sneaking: Boolean
  def sprinting: Boolean


  def swingArm()
  def closeWindow()
}