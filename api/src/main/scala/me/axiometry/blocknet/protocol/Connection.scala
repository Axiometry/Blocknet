package me.axiometry.blocknet.protocol

import java.io._
import java.net._

import me.axiometry.blocknet.ServerAddress

trait Connection[I <: InputStream, O <: OutputStream] {
  def in: I
  def out: O

  def connected: Boolean
  def disconnect()
}

object Connection {
  protected[this] case class SocketConnection[I <: InputStream, O <: OutputStream](socket: Socket, in: I, out: O) extends Connection[I, O] {
    override def connected = socket.isConnected && !socket.isClosed
    override def disconnect() = socket.close
  }
  def apply[I <: InputStream, O <: OutputStream](socket: Socket, createIn: InputStream => I, createOut: OutputStream => O): Connection[I, O] = {
    val conn = SocketConnection(socket, createIn(socket.getInputStream), createOut(socket.getOutputStream))
    if(!conn.connected)
      throw new IOException("Unconnected socket")
    conn
  }
  def apply[I <: InputStream, O <: OutputStream](address: ServerAddress, createIn: InputStream => I, createOut: OutputStream => O): Connection[I, O] = {
    val socket = new Socket(address.host, address.port)
    apply(socket, createIn, createOut)
  }
}