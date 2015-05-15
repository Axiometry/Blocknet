package me.axiometry.blocknet.block

sealed abstract class BlockOffset(val offsetX: Int, val offsetY: Int, val offsetZ: Int)
sealed abstract class BlockFace(offsetX: Int, offsetY: Int, offsetZ: Int) extends BlockOffset(offsetX, offsetY, offsetZ)
sealed abstract class BlockEdge(face1: BlockFace, face2: BlockFace) extends BlockOffset(face1.offsetX + face2.offsetX, face1.offsetY + face2.offsetY, face1.offsetZ + face2.offsetZ)
sealed abstract class BlockVertex(face: BlockFace, edge: BlockEdge) extends BlockOffset(face.offsetX + edge.offsetX, face.offsetY + edge.offsetY, face.offsetZ + edge.offsetZ)
object BlockOffset {
  val values: Set[BlockOffset] = BlockFace.values ++ BlockEdge.values ++ BlockVertex.values
}
object BlockFace {
  val values: Set[BlockFace] = Set(North, South, East, West, Up, Down)
  case object North extends BlockFace(0, 0, -1)
  case object South extends BlockFace(0, 0, 1)
  case object East extends BlockFace(1, 0, 0)
  case object West extends BlockFace(-1, 0, 0)
  case object Up extends BlockFace(0, 1, 0)
  case object Down extends BlockFace(0, -1, 0)
}
object BlockEdge {
  import BlockFace._
  val values: Set[BlockEdge] = Set(NorthEast, NorthWest, SouthEast, SouthWest,
                                   UpperNorth, UpperSouth, UpperEast, UpperWest,
                                   LowerNorth, LowerSouth, LowerEast, LowerWest)
  case object NorthEast extends BlockEdge(North, East)
  case object NorthWest extends BlockEdge(North, West)
  case object SouthEast extends BlockEdge(South, East)
  case object SouthWest extends BlockEdge(South, West)
  case object UpperNorth extends BlockEdge(Up, North)
  case object UpperSouth extends BlockEdge(Up, South)
  case object UpperEast extends BlockEdge(Up, East)
  case object UpperWest extends BlockEdge(Up, West)
  case object LowerNorth extends BlockEdge(Down, North)
  case object LowerSouth extends BlockEdge(Down, South)
  case object LowerEast extends BlockEdge(Down, East)
  case object LowerWest extends BlockEdge(Down, West)
}
object BlockVertex {
  import BlockFace._; import BlockEdge._
  val values: Set[BlockVertex] = Set(UpperNorthEast, UpperNorthWest, UpperSouthEast, UpperSouthWest,
                                     LowerNorthEast, LowerNorthWest, LowerSouthEast, LowerSouthWest)
  case object UpperNorthEast extends BlockVertex(Up, NorthEast)
  case object UpperNorthWest extends BlockVertex(Up, NorthWest)
  case object UpperSouthEast extends BlockVertex(Up, SouthEast)
  case object UpperSouthWest extends BlockVertex(Up, SouthWest)
  case object LowerNorthEast extends BlockVertex(Down, NorthEast)
  case object LowerNorthWest extends BlockVertex(Down, NorthWest)
  case object LowerSouthEast extends BlockVertex(Down, SouthEast)
  case object LowerSouthWest extends BlockVertex(Down, SouthWest)
}