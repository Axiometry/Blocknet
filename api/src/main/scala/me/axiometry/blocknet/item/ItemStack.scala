package me.axiometry.blocknet.item

trait ItemStack {
  def item: Item
  def data: Int
  def size: Int

  def withItem(item: Item): ItemStack
  def withData(data: Int): ItemStack
  def withSize(size: Int): ItemStack
}