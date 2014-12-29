package me.axiometry.blocknet

sealed abstract class ChatColor(val code: Char, val id: Int) {
  private val string = new String(Array[Char](ChatColor.colorCode, code))

  override def toString() = string
  override def hashCode() = id
}
sealed abstract class ColorChatColor(code: Char, id: Int) extends ChatColor(code, id)
sealed abstract class FormatChatColor(code: Char, id: Int) extends ChatColor(code, id)
sealed abstract class ResetChatColor(code: Char, id: Int) extends ChatColor(code, id)
object ChatColor {
  val colorCode = '\u00A7'

  val values: Set[ChatColor] =
    Set(Black, DarkBlue, DarkGreen, DarkAqua,
        DarkRed, DarkPurple, Gold, Gray,
        DarkGray, Blue, Green, Aqua,
        Red, LightPurple, Yellow, White,
        Obfuscated, Bold, Strikethrough,
        Underline, Italic, Reset)
  val colors: Set[ColorChatColor] =
    Set(Black, DarkBlue, DarkGreen, DarkAqua,
        DarkRed, DarkPurple, Gold, Gray,
        DarkGray, Blue, Green, Aqua,
        Red, LightPurple, Yellow, White)
  val formats: Set[FormatChatColor] =
    Set(Obfuscated, Bold, Strikethrough,
        Underline, Italic)

  private final val stripColorPattern = java.util.regex.Pattern.compile(s"$colorCode[0-9A-FK-ORa-fk-or]")
  private final val codeMappings: Map[Char, ChatColor] = values map (c => c.code -> c) toMap
  private final val idMappings: Map[Int, ChatColor] = values map (c => c.id -> c) toMap

  def byCode(code: Char) = codeMappings get code
  def byId(id: Int) = idMappings get id
  def strip(string: String) = stripColorPattern matcher string replaceAll ""

  object Black extends ColorChatColor('0', 0x00)
  object DarkBlue extends ColorChatColor('1', 0x01)
  object DarkGreen extends ColorChatColor('2', 0x02)
  object DarkAqua extends ColorChatColor('3', 0x03)
  object DarkRed extends ColorChatColor('4', 0x04)
  object DarkPurple extends ColorChatColor('5', 0x05)
  object Gold extends ColorChatColor('6', 0x06)
  object Gray extends ColorChatColor('7', 0x07)
  object DarkGray extends ColorChatColor('8', 0x08)
  object Blue extends ColorChatColor('9', 0x09)
  object Green extends ColorChatColor('a', 0x0A)
  object Aqua extends ColorChatColor('b', 0x0B)
  object Red extends ColorChatColor('c', 0x0C)
  object LightPurple extends ColorChatColor('d', 0x0D)
  object Yellow extends ColorChatColor('e', 0x0E)
  object White extends ColorChatColor('f', 0x0F)

  object Obfuscated extends FormatChatColor('k', 0x10)
  object Bold extends FormatChatColor('l', 0x11)
  object Strikethrough extends FormatChatColor('m', 0x12)
  object Underline extends FormatChatColor('n', 0x13)
  object Italic extends FormatChatColor('o', 0x14)

  object Reset extends ResetChatColor('r', 0x15)
}
