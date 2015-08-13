package me.axiometry.blocknet.minecraft.net

sealed trait Direction
object Direction {
  case object ToClient extends Direction
  case object ToServer extends Direction
}