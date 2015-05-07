package me.axiometry.blocknet.entity

trait Horse extends Animal with Animal.Tameable with Animal.Rideable {
  override type EntityType <: Horse

  trait Breed
  object Breed {
    case object Horse extends Breed
    case object Donkey extends Breed
    case object Mule extends Breed
    case object Zombie extends Breed
    case object Skeleton extends Breed
  }

  def hasChest: Boolean
  def rearing: Boolean
  def eating: Boolean
  def breed: Breed

  def hasChest_=(hasChest: Boolean): EntityType
  def rearing_=(rearing: Boolean): EntityType
  def eating_=(eating: Boolean): EntityType
  def breed_=(breed: Breed): EntityType
}