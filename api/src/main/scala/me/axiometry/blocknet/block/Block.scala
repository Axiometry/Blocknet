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

  /*case object Air                      extends Block(0, 0) with Transparency
  case object Stone                    extends OpaqueMaterial(1)
  case object Grass                    extends OpaqueMaterial(2)
  case object Dirt                     extends OpaqueMaterial(3)
  case object Cobblestone              extends OpaqueMaterial(4)
  case object Wood                     extends OpaqueCombustibleMaterial(5) with TreeDataType
  case object Sapling                  extends Material(6) with Transparency with TreeDataType
  case object Bedrock                  extends OpaqueMaterial(7)
  case object Water                    extends Material(8) with Transparency with UnspecifiedDataType
  case object StationaryWater          extends Material(9) with Transparency with UnspecifiedDataType
  case object Lava                     extends Material(10) with Transparency with UnspecifiedDataType
  case object StationaryLava           extends Material(11) with Transparency with UnspecifiedDataType
  case object Sand                     extends OpaqueMaterial(12) with Gravity
  case object Gravel                   extends OpaqueMaterial(13) with Gravity
  case object GoldOre                  extends OpaqueMaterial(14)
  case object IronOre                  extends OpaqueMaterial(15)
  case object CoalOre                  extends OpaqueMaterial(16)
  case object Log                      extends OpaqueCombustibleMaterial(17) with TreeDataType
  case object Leaves                   extends CombustibleMaterial(18) with Solidity with TreeDataType
  case object Sponge                   extends OpaqueMaterial(19)
  case object Glass                    extends Material(20) with Solidity
  case object LapisOre                 extends OpaqueMaterial(21)
  case object LapisBlock               extends OpaqueMaterial(22)
  case object Dispenser                extends OpaqueMaterial(23) with DispenserDataType
  case object Sandstone                extends OpaqueMaterial(24) with SandstoneDataType
  case object NoteBlock                extends OpaqueMaterial(25) with Flammability
  case object BedBlock                 extends Material(26) with Solidity with Flammability with BedDataType

  case object PoweredRail              extends Material(27) with Transparency
  case object DetectorRail             extends Material(28) with Transparency
  case object PistonStickyBase         extends Material(29) with Solidity
  case object Web                      extends Material(30)
  case object LongGrass                extends CombustibleMaterial(31) with Transparency
  case object DeadBush                 extends Material(32) with Flammability with Transparency
  case object PistonBase               extends Material(33) with Solidity
  case object PistonExtension          extends Material(34) with Solidity
  case object Wool                     extends OpaqueCombustibleMaterial(35)
  case object PistonMovingPiece        extends Material(36) with Solidity
  case object YellowFlower             extends Material(37) with Burnability with Transparency
  case object RedRose                  extends Material(38) with Burnability with Transparency
  case object BrownMushroom            extends Material(39) with Transparency
  case object RedMushroom              extends Material(40) with Transparency
  case object GoldBlock                extends OpaqueMaterial(41)
  case object IronBlock                extends OpaqueMaterial(42)
  case object DoubleStep               extends OpaqueMaterial(43)
  case object Step                     extends Material(44) with Solidity
  case object Brick                    extends OpaqueMaterial(45)
  case object TNT                      extends CombustibleMaterial(46) with Solidity
  case object Bookshelf                extends OpaqueCombustibleMaterial(47)
  case object MossyCobblestone         extends OpaqueMaterial(48)
  case object Obsidian                 extends OpaqueMaterial(49)
  case object Torch                    extends Material(50) with Transparency
  case object Fire                     extends Material(51) with Transparency
  case object MobSpawner               extends OpaqueMaterial(52)
  case object WoodStairs               extends CombustibleMaterial(53) with Solidity
  case object Chest                    extends Material(54) with Flammability with Solidity
  case object RedstoneWire             extends Material(55) with Transparency
  case object DiamondOre               extends OpaqueMaterial(56)
  case object DiamondBlock             extends OpaqueMaterial(57)
  case object Workbench                extends OpaqueMaterial(58) with Flammability
  case object Crops                    extends Material(59) with Transparency
  case object Soil                     extends Material(60) with Solidity
  case object Furnace                  extends OpaqueMaterial(61)
  case object BurningFurnace           extends OpaqueMaterial(62)
  case object SignPost                 extends Material(63) with Flammability with Transparency
  case object WoodenDoor               extends Material(64) with Flammability with Solidity
  case object Ladder                   extends Material(65) with Transparency
  case object Rails                    extends Material(66) with Transparency
  case object CobblestoneStairs        extends Material(67) with Solidity
  case object WallSign                 extends Material(68) with Flammability with Transparency
  case object Lever                    extends Material(69) with Transparency
  case object StonePlate               extends Material(70) with Transparency
  case object IronDoorBlock            extends Material(71) with Solidity
  case object WoodPlate                extends Material(72) with Flammability with Transparency
  case object RedstoneOre              extends OpaqueMaterial(73)
  case object GlowingRedstoneOre       extends OpaqueMaterial(74)
  case object RedstoneTorchOn          extends Material(75) with Transparency
  case object RedstoneTorchOff         extends Material(76) with Transparency
  case object StoneButton              extends Material(77) with Transparency
  case object Snow                     extends Material(78) with Transparency
  case object Ice                      extends Material(79) with Solidity
  case object SnowBlock                extends OpaqueMaterial(80)
  case object Cactus                   extends Material(81) with Solidity
  case object Clay                     extends OpaqueMaterial(82)
  case object SugarCaneBlock           extends Material(83) with Transparency
  case object Jukebox                  extends OpaqueMaterial(84) with Flammability
  case object Fence                    extends CombustibleMaterial(85) with Solidity
  case object Pumpkin                  extends OpaqueMaterial(86)
  case object Netherrack               extends OpaqueMaterial(87)
  case object SoulSand                 extends OpaqueMaterial(88)
  case object Glowstone                extends OpaqueMaterial(89)
  case object Portal                   extends Material(90) with Transparency
  case object JackOLantern             extends OpaqueMaterial(91)
  case object CakeBlock                extends Material(92) with Solidity
  case object DiodeBlockOff            extends Material(93) with Solidity
  case object DiodeBlockOn             extends Material(94) with Solidity
  @deprecated ("Use only if necessary, replaced by StainedGlass", "0.0")
  case object LockedChest              extends Material(95) with Solidity
  case object StainedGlass             extends Material(95) with Solidity
  case object TrapDoor                 extends Material(96) with Flammability with Solidity
  case object MonsterEggs              extends OpaqueMaterial(97)
  case object SmoothBrick              extends OpaqueMaterial(98)
  case object HugeMushroom1            extends OpaqueMaterial(99) with Flammability
  case object HugeMushroom2            extends OpaqueMaterial(100) with Flammability
  case object IronFence                extends Material(101) with Solidity
  case object ThinGlass                extends Material(102) with Solidity
  case object MelonBlock               extends OpaqueMaterial(103)
  case object PumpkinStem              extends Material(104) with Transparency
  case object MelonStem                extends Material(105) with Transparency
  case object Vine                     extends CombustibleMaterial(106) with Transparency
  case object FenceGate                extends Material(107) with Flammability with Solidity
  case object BrickStairs              extends Material(108) with Solidity
  case object SmoothStairs             extends Material(109) with Solidity
  case object Mycel                    extends OpaqueMaterial(110)
  case object WaterLily                extends Material(111) with Transparency
  case object NetherBrick              extends OpaqueMaterial(112)
  case object NetherBrickFence         extends Material(113) with Solidity
  case object NetherBrickStairs        extends Material(114) with Solidity
  case object NetherWarts              extends Material(115) with Transparency
  case object EnchantmentTable         extends Material(116) with Solidity
  case object BrewingStand             extends Material(117) with Solidity
  case object Cauldron                 extends Material(118) with Solidity
  case object EnderPortal              extends Material(119) with Transparency
  case object EnderPortalFrame         extends OpaqueMaterial(120)
  case object EnderStone               extends OpaqueMaterial(121)
  case object DragonEgg                extends Material(122) with Solidity
  case object RedstoneLampOff          extends OpaqueMaterial(123)
  case object RedstoneLampOn           extends OpaqueMaterial(124)
  case object WoodDoubleStep           extends OpaqueCombustibleMaterial(125)
  case object WoodStep                 extends CombustibleMaterial(126) with Solidity
  case object Cocoa                    extends Material(127) with Transparency
  case object SandstoneStairs          extends Material(128) with Solidity
  case object EmeraldOre               extends OpaqueMaterial(129)
  case object EnderChest               extends Material(130) with Solidity
  case object TripwireHook             extends Material(131) with Transparency
  case object Tripwire                 extends Material(132) with Transparency
  case object EmeraldBlock             extends OpaqueMaterial(133)
  case object SpruceWoodStairs         extends CombustibleMaterial(134) with Solidity
  case object BirchWoodStairs          extends CombustibleMaterial(135) with Solidity
  case object JungleWoodStairs         extends CombustibleMaterial(136) with Solidity
  case object CommandBlock             extends OpaqueMaterial(137)
  case object Beacon                   extends Material(138) with Solidity
  case object CobbleWall               extends Material(139) with Solidity
  case object FlowerPot                extends Material(140) with Solidity
  case object Carrot                   extends Material(141) with Transparency
  case object Potato                   extends Material(142) with Transparency
  case object WoodButton               extends Material(143) with Transparency
  case object Head                     extends Material(144) with Solidity
  case object Anvil                    extends Material(145) with Solidity with Gravity
  case object TrappedChest             extends Material(146) with Flammability with Solidity
  case object GoldPlate                extends Material(147) with Transparency
  case object IronPlate                extends Material(148) with Transparency
  case object RedstoneComparatorOff    extends Material(149) with Solidity
  case object RedstoneComparatorOn     extends Material(150) with Solidity
  case object DaylightDetector         extends Material(151) with Flammability with Solidity
  case object RedstoneBlock            extends OpaqueMaterial(152)
  case object QuartzOre                extends OpaqueMaterial(153)
  case object Hopper                   extends Material(154) with Solidity
  case object QuartzBlock              extends OpaqueMaterial(155)
  case object QuartzStairs             extends Material(156) with Solidity
  case object ActivatorRail            extends Material(157) with Transparency
  case object Dropper                  extends OpaqueMaterial(158)
  case object StainedClay              extends OpaqueMaterial(159)
  case object StainedGlassPane         extends Material(160) with Solidity
  case object Leaves2                  extends CombustibleMaterial(161) with Solidity
  case object Log2                     extends OpaqueCombustibleMaterial(162)
  case object AcaciaStairs             extends CombustibleMaterial(163) with Solidity
  case object DarkOakStairs            extends CombustibleMaterial(164) with Solidity
  case object HayBlock                 extends OpaqueCombustibleMaterial(170)
  case object Carpet                   extends CombustibleMaterial(171) with Transparency
  case object HardClay                 extends OpaqueMaterial(172)
  case object CoalBlock                extends OpaqueCombustibleMaterial(173)
  case object PackedIce                extends OpaqueMaterial(174)
  case object DoublePlant              extends CombustibleMaterial(175) with Transparency*/

  // -------------------------------------
  /*trait TreeDataType extends DataType {
    override type Data = TreeData
    override def createData(value: Int) = Try(new TreeData(value)).toOption
  }
  case class TreeData(val species: TreeSpecies, val direction: Option[BlockFace]) extends MaterialData {
    def this(value: Int) = this(TreeSpecies byId (value & 3) get, ((value >> 2) & 3) match {
      case 0 => Some(Up)
      case 1 => Some(East)
      case 2 => Some(North)
      case 3 => None
    })
    override def value = species.id | ((direction match {
      case Some(Up) | Some(Down) => 0
      case Some(East) | Some(West) => 1
      case Some(North) | Some(South) => 2
      case None => 3
    }) << 2)
  }
  abstract class TreeSpecies(val id: Int)
  object TreeSpecies {
    val values: Set[TreeSpecies] = Set(Generic, Redwood, Birch, Jungle, Acacia, DarkOak)

    private val valuesById = values map (s => s.id -> s) toMap
    def byId(id: Int) = valuesById get id

    case object Generic extends TreeSpecies(0)
    case object Redwood extends TreeSpecies(1)
    case object Birch extends TreeSpecies(2)
    case object Jungle extends TreeSpecies(3)
    case object Acacia extends TreeSpecies(4)
    case object DarkOak extends TreeSpecies(5)
  }
  // -------------------------------------

  trait DispenserDataType extends DataType {
    override type Data = DispenserData
    override def createData(value: Int) = Try(new DispenserData(value)).toOption
  }
  case class DispenserData(val direction: BlockFace) extends MaterialData {
    def this(value: Int) = this(value match {
      case 0 => Down
      case 1 => Up
      case 2 => North
      case 3 => South
      case 4 => West
      case 5 => East
    })
    override def value = direction match {
      case Down => 0
      case Up => 1
      case North => 2
      case South => 3
      case West => 4
      case East => 5
    }
  }
  // -------------------------------------

  trait SandstoneDataType extends DataType {
    override type Data = SandstoneData
    override def createData(value: Int) = Try(new SandstoneData(value)).toOption
  }
  case class SandstoneData(val sandstone: SandstoneType) extends MaterialData {
    def this(value: Int) = this(SandstoneType byId value get)
    override def value = sandstone.id
  }
  abstract class SandstoneType(val id: Int)
  object SandstoneType {
    val values: Set[SandstoneType] = Set(Cracked, Glyphed, Smooth)

    private val valuesById = values map (s => s.id -> s) toMap
    def byId(id: Int) = valuesById get id

    case object Cracked extends SandstoneType(0)
    case object Glyphed extends SandstoneType(1)
    case object Smooth extends SandstoneType(2)
  }
  // -------------------------------------

  trait BedDataType extends DataType {
    override type Data = BedData
    override def createData(value: Int) = Try(new BedData(value)).toOption
  }
  case class BedData(val isHead: Boolean, val direction: BlockFace) extends MaterialData {
    direction match {
      case North | South | East | West =>
      case _ => throw new IllegalArgumentException()
    }
    def this(value: Int) = this((value & 8) != 0, (value & 7) match {
      case 0 => South
      case 1 => West
      case 2 => North
      case 3 => East
    })
    override def value = (if(isHead) 8 else 0) | (direction match {
      case South => 0
      case West => 1
      case North => 2
      case East => 3
      case _ => ???
    })
  }*/
  // -------------------------------------
  // -------------------------------------
  // -------------------------------------
}