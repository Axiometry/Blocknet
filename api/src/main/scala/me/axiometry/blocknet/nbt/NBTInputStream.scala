package me.axiometry.blocknet.nbt

import java.io._

class NBTInputStream(in: InputStream) extends DataInputStream(in) {
  private def invalid() = throw new IOException("invalid NBT tag")

  def readNBTTag(): NBT.Tag = readNBTTag(true) match {
    case Some(tag) => tag
    case None => invalid
  }

  private def readNBTTag(withName: Boolean): Option[NBT.Tag] = {
    readByte match {
      case 0 => None
      case id => NBT.Tag.Type.byId get id match {
        case Some(t) => Some(readNBTTag(t, if(withName) Some(readUTF) else None))
        case None => invalid
      }
    }
  }

  private def readNBTTag(t: NBT.Tag.Type, name: Option[String]): NBT.Tag = {
    import NBT.Tag; import NBT.Tag.Type
    t match {
      case Type.Byte      => new Tag.Byte(name, readByte)
      case Type.Short     => new Tag.Short(name, readShort)
      case Type.Int       => new Tag.Int(name, readInt)
      case Type.Long      => new Tag.Long(name, readLong)
      case Type.Float     => new Tag.Float(name, readFloat)
      case Type.Double    => new Tag.Double(name, readDouble)
      case Type.ByteArray => new Tag.ByteArray(name, Seq.fill(readInt)(readByte))
      case Type.IntArray  => new Tag.IntArray(name, Seq.fill(readInt)(readInt))
      case Type.String    => new Tag.String(name, readUTF)

      case Type.List =>
        val listType = Type.byId get (readByte) match {
          case Some(t) => t
          case None => invalid
        }
        val tags = Seq.fill(readInt)(readNBTTag(listType, None))
        new Tag.List(name, listType, tags: _*)
      case Type.Compound =>
        val tags = scala.collection.mutable.ListBuffer[Tag]()
        while(readNBTTag(true) match {
          case Some(tag) => tags += tag; true
          case None => false
        }) {}
        new Tag.Compound(name, tags.toList: _*)
    }
  }
}
