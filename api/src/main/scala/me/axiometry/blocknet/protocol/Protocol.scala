package me.axiometry.blocknet.protocol

import rx.lang.scala.Observable

trait Protocol {
  def state: State
  def blockRegistry: BlockRegistry

  def stream: Observable[Packet]

  def send(message: Message)
  def connected: Boolean
}