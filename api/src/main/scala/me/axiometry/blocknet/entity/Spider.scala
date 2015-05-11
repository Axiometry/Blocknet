package me.axiometry.blocknet.entity

trait Spider extends Monster {
  def aggravated: Boolean
  def aggravated_=(aggravated: Boolean)
}