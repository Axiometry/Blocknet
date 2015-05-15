package me.axiometry.blocknet.protocol

sealed trait Direction
object Direction {
  case object ToClient extends Direction
  case object ToServer extends Direction
}