package me.axiometry.blocknet.entity

trait Sheep extends Animal {
  sealed trait Color
  object Color {
    case object White extends Color
    case object Orange extends Color
    case object Magenta extends Color
    case object LightBlue extends Color
    case object Yellow extends Color
    case object Lime extends Color
    case object Pink extends Color
    case object Gray extends Color
    case object LightGray extends Color
    case object Cyan extends Color
    case object Purple extends Color
    case object Blue extends Color
    case object Brown extends Color
    case object Green extends Color
    case object Red extends Color
    case object Black extends Color
  }

  def sheared: Boolean
  def color: Int

  def sheared_=(sheared: Boolean)
  def color_=(color: Int)
}