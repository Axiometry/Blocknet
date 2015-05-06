package me.axiometry.blocknet.item

trait Window extends Inventory {
  sealed trait Button
  object Button {
    case object Left extends Button
    case object Right extends Button
  }

  def select(slot: Int, shift: Boolean = false, button: Button = Button.Left)
  def dropSelected(fullStack: Boolean = false)
  def selected: Option[ItemStack]
}