package me.axiometry.blocknet.block

import scala.util.Try

import BlockFace._

import me.axiometry.blocknet._
import me.axiometry.blocknet.item.Item

abstract class Block(val id: Int, val name: String)

object Block {
  trait ToolType[T <: Item.Tool] extends Block
  trait Strength extends Block {
    def strength: Int
  }

  trait Data {
    def value: Int
  }
  trait ModelData {
    def model: Model = ModelData.defaultModel
  }
  object ModelData {
    private[Block] val defaultModel = Model(List(BoundingBox(0, 0, 0, 1, 1, 1)))

    trait Solid extends ModelData
    trait Interactible extends ModelData
  }

  trait DataType extends Block {
    type Data <: Block.Data
    def createData(): Data = createData(0).get
    def createData(value: Int): Option[Data]
  }

  sealed case class UnspecifiedData(val value: Int) extends Data
  trait UnspecifiedDataType extends DataType {
    override type Data = UnspecifiedData
    override def createData(value: Int) = Option(UnspecifiedData(value))
  }
}