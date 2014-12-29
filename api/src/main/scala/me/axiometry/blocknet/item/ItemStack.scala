package me.axiometry.blocknet.item

trait ItemStack {
  def item: Item
  def damage: Int
  def size: Int

  def resize(size: Int): ItemStack
}