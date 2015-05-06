package me.axiometry.blocknet.item

import me.axiometry.blocknet.block.Block

abstract class Item(val id: Int, val name: String, val maxStackSize: Int) {
  def this(id: Int, name: String) = this(id, name, 64)
}


object Item {
  trait Data {
    def value: Int
  }
  trait DataType {
    type Data <: Item.Data
    def createData(): Data = createData(0).get
    def createData(value: Int): Option[Data]
  }

  trait Durability {
    def durability: Int
  }
  protected abstract class DurableItem(id: Int, name: String, maxStackSize: Int, val durability: Int) extends Item(id, name, maxStackSize) with Durability {
    def this(id: Int, name: String, durability: Int) = this(id, name, 64, durability)
  }

  trait Tool extends Item
  object Tool {
    trait Axe extends Tool
    trait Pickaxe extends Tool
    trait Shovel extends Tool
    trait Hoe extends Tool
  }

  trait BlockType {
    def block: Block
  }
  protected[this] class BlockData[T <: Block.Data](data: T) extends Data {
    override def value = data.value
  }
  type DataBlock[T <: Block.Data] = Block with Block.DataType { type Data = T }
  trait BlockDataType extends BlockType with DataType {
    protected type RawBlockData <: Block.Data
    override type Data = BlockData[RawBlockData]

    override def block: DataBlock[RawBlockData]
    override def createData(value: Int) = block.createData(value).map(new BlockData[RawBlockData](_))
  }
  trait Placeability extends BlockType


  protected abstract class BlockItem(override val block: Block, maxStackSize: Int) extends Item(block.id, block.name, maxStackSize) with BlockType {
    def this(block: Block) = this(block, 64)
  }
  protected abstract class BlockDataItem[T <: Block.Data](override val block: DataBlock[T], maxStackSize: Int) extends BlockItem(block, maxStackSize) with BlockDataType {
    def this(block: DataBlock[T]) = this(block, 64)
    protected override final type RawBlockData = T
  }

}