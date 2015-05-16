package me.axiometry.blocknet.nbt

import java.io.{InputStream, OutputStream}

object NBT {
  type Name = Option[String]
  sealed abstract class Tag(val `type`: Tag.Type, val name: Name)

  object Tag {
    case class Byte             (override val name: Name, val value: scala.Byte)                extends Tag(Type.Byte,      name)
    case class Short            (override val name: Name, val value: scala.Short)               extends Tag(Type.Short,     name)
    case class Int              (override val name: Name, val value: scala.Int)                 extends Tag(Type.Int,       name)
    case class Long             (override val name: Name, val value: scala.Long)                extends Tag(Type.Long,      name)
    case class Float            (override val name: Name, val value: scala.Float)               extends Tag(Type.Float,     name)
    case class Double           (override val name: Name, val value: scala.Double)              extends Tag(Type.Double,    name)
    case class ByteArray        (override val name: Name, val value: Seq[scala.Byte])           extends Tag(Type.ByteArray, name)
    case class IntArray         (override val name: Name, val value: Seq[scala.Int])            extends Tag(Type.IntArray,  name)
    case class String           (override val name: Name, val value: java.lang.String)          extends Tag(Type.String,    name)
    case class List[T <: Tag]   (override val name: Name, val listType: Type, val elements: T*) extends Tag(Type.List,      name)
    case class Compound         (override val name: Name, val tags: Tag*)                       extends Tag(Type.Compound,  name)

    sealed abstract class Type(val id: scala.Int)
    object Type {
      val values: Set[Type] = Set(Byte, Short, Int, Long, Float, Double, ByteArray, String, List, Compound, IntArray)
      val byId: Map[scala.Int, Type] = values map (t => t.id -> t) toMap

      case object Byte      extends Type(1)
      case object Short     extends Type(2)
      case object Int       extends Type(3)
      case object Long      extends Type(4)
      case object Float     extends Type(5)
      case object Double    extends Type(6)
      case object ByteArray extends Type(7)
      case object String    extends Type(8)
      case object List      extends Type(9)
      case object Compound  extends Type(10)
      case object IntArray  extends Type(11)
    }
  }
}

object tester extends App {
  import java.io._
  import java.util.zip._
  import NBT.Tag

  println("Loading...")
  val tag = {
    val file = new File("~/Downloads/bigtest(1).nbt")
    val input = new NBTInputStream(new GZIPInputStream(new FileInputStream(file)))
    input.readNBTTag()
  }
  println("Completed!")
  println(toString(tag))

  println("Writing...")
  val outFile = new File("~/Downloads/bigtest2.nbt")
  val output = new NBTOutputStream(new GZIPOutputStream(new FileOutputStream(outFile)))
  output.writeNBTTag(tag)
  output.flush()
  output.close()
  println("Completed!")

  println("Reloading...")
  val newTag = {
    val input = new NBTInputStream(new GZIPInputStream(new FileInputStream(outFile)))
    input.readNBTTag()
  }
  println("Completed!")
  println(toString(newTag))


  def nameToString(name: NBT.Name) = name match {
    case Some(name) => s"('$name')"
    case None => ""
  }
  def toString(tag: Tag): String = tag match {
    case Tag.Byte(name, value) => s"Byte${nameToString(name)}: $value"
    case Tag.Short(name, value) => s"Short${nameToString(name)}: $value"
    case Tag.Int(name, value) => s"Int${nameToString(name)}: $value"
    case Tag.Long(name, value) => s"Long${nameToString(name)}: $value"
    case Tag.Float(name, value) => s"Float${nameToString(name)}: $value"
    case Tag.Double(name, value) => s"Double${nameToString(name)}: $value"
    case Tag.ByteArray(name, value) => s"ByteArray${nameToString(name)}: [${if(value.length < 100) value mkString "," else value.length + " bytes"}]"
    case Tag.IntArray(name, value) => s"ByteArray${nameToString(name)}: [${if(value.length < 100) value mkString "," else value.length + " ints"}]"
    case Tag.String(name, value) => s"String${nameToString(name)}: '$value'"
    case Tag.List(name, t, elems @ _*) => s"List${nameToString(name)}: ${elems.length} entries {\n  ${elems flatMap (toString(_).split("\n")) mkString "\n  "}\n}"
    case Tag.Compound(name, tags @ _*) => s"Compound${nameToString(name)}: ${tags.length} entries {\n  ${tags flatMap (toString(_).split("\n")) mkString "\n  "}\n}"
  }
}