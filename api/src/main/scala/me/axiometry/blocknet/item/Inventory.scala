package me.axiometry.blocknet.item

trait Inventory {
  def itemAt(slot: Int): Option[ItemStack]

  def size: Int
}