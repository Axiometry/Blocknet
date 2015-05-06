package me.axiometry.blocknet

sealed abstract class ChatColor(val code: Char) {
  private val string = new String(Array[Char](ChatColor.colorCode))

  override def toString() = string
  override def hashCode() = code
}
sealed abstract class ColorChatColor(code: Char) extends ChatColor(code)
sealed abstract class FormatChatColor(code: Char) extends ChatColor(code)
sealed abstract class ResetChatColor(code: Char) extends ChatColor(code)
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
  private final val codeMappings: Map[Char, ChatColor] = values.map(c => c.code -> c).toMap

  def byCode(code: Char) = codeMappings.get(code)
  def strip(string: String) = stripColorPattern.matcher(string).replaceAll("")

  case object Black extends ColorChatColor('0')
  case object DarkBlue extends ColorChatColor('1')
  case object DarkGreen extends ColorChatColor('2')
  case object DarkAqua extends ColorChatColor('3')
  case object DarkRed extends ColorChatColor('4')
  case object DarkPurple extends ColorChatColor('5')
  case object Gold extends ColorChatColor('6')
  case object Gray extends ColorChatColor('7')
  case object DarkGray extends ColorChatColor('8')
  case object Blue extends ColorChatColor('9')
  case object Green extends ColorChatColor('a')
  case object Aqua extends ColorChatColor('b')
  case object Red extends ColorChatColor('c')
  case object LightPurple extends ColorChatColor('d')
  case object Yellow extends ColorChatColor('e')
  case object White extends ColorChatColor('f')

  case object Obfuscated extends FormatChatColor('k')
  case object Bold extends FormatChatColor('l')
  case object Strikethrough extends FormatChatColor('m')
  case object Underline extends FormatChatColor('n')
  case object Italic extends FormatChatColor('o')

  case object Reset extends ResetChatColor('r')
}
