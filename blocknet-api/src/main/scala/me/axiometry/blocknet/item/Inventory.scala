package me.axiometry.blocknet.item

trait Inventory {
  trait ItemAccess {
    def apply(slot: Int): Option[ItemStack]
    def update(slot: Int, item: Option[ItemStack])
  }

  def itemAt: ItemAccess

  def size: Int
}