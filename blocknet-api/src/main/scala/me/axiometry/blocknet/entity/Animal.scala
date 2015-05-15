package me.axiometry.blocknet.entity

trait Animal extends Creature
object Animal {
  trait Tameable extends Animal {
    def ownerName: Option[String]
    def sitting: Boolean
    def tame: Boolean

    def ownerName_=(ownerName: Option[String])
    def sitting_=(sitting: Boolean)
  }
  trait Rideable extends Animal {
    def saddled: Boolean

    def saddled_=(saddled: Boolean)
  }
}
