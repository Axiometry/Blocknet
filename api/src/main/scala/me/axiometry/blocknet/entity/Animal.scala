package me.axiometry.blocknet.entity

trait Animal extends Creature {
  override type EntityType <: Animal
}
object Animal {
  trait Tameable extends Animal {
    override type EntityType <: Tameable

    def ownerName: Option[String]
    def sitting: Boolean
    def tame: Boolean

    def ownerName_=(ownerName: Option[String]): EntityType
    def sitting_=(sitting: Boolean): EntityType
  }
  trait Rideable extends Animal {
    override type EntityType <: Rideable

    def saddled: Boolean

    def saddled_=(saddled: Boolean): EntityType
  }
}
