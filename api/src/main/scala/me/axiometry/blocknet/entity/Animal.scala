package me.axiometry.blocknet.entity

trait Animal extends Creature
object Animal {
  trait Tameable extends Animal {
    def ownerName: Option[String]
    def sitting: Boolean
    def tame: Boolean
  }
  trait Rideable extends Animal {
    def saddled: Boolean
  }
}
