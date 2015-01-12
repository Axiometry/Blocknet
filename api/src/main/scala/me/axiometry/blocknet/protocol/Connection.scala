package me.axiometry.blocknet.protocol

import java.io._
import java.net._

import me.axiometry.blocknet.ServerAddress

trait Connection {
  type In <: InputStream
  type Out <: OutputStream

  def in: In
  def out: Out

  def connected: Boolean
  def disconnect()
}

object Connection {
  protected[this] case class SocketConnection[I <: InputStream, O <: OutputStream](val socket: Socket, val in: I, val out: O) extends Connection {
    override type In = I
    override type Out = O
    override def connected = socket.isConnected && !socket.isClosed
    override def disconnect() = socket.close
  }
  def apply[I <: InputStream, O <: OutputStream](socket: Socket, createIn: InputStream => I, createOut: OutputStream => O): Connection = {
    val conn = SocketConnection(socket, createIn(socket.getInputStream), createOut(socket.getOutputStream))
    if(!conn.connected)
      throw new IOException("Unconnected socket")
    conn
  }
  def apply[I <: InputStream, O <: OutputStream](address: ServerAddress, createIn: InputStream => I, createOut: OutputStream => O): Connection = {
    val socket = new Socket(address.host, address.port)
    apply(socket, createIn, createOut)
  }
}