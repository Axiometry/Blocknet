package me.axiometry.blocknet

import scala.annotation.tailrec

/**
 * Represents a chat color marker for use in Minecraft chat.
 */
sealed abstract class ChatColor(val code: Char) {
  private val string = new String(Array[Char](ChatColor.codePrefix, code))

  /**
   * The stringified version of the chat color with code prefix prepended.
   */
  override def toString() = string
  override def hashCode() = code
}

/**
 * Represents a chat color marker of a color code for use in Minecraft chat.
 *
 */
sealed abstract class ColorChatColor(code: Char) extends ChatColor(code)

/**
 * Represents a chat color marker of a format code for use in Minecraft chat.
 */
sealed abstract class FormatChatColor(code: Char) extends ChatColor(code)

/**
 * Represents a chat color marker of a reset code for use in Minecraft chat.
 */
sealed abstract class ResetChatColor(code: Char) extends ChatColor(code)

object ChatColor {
  /**
   * The character that prepends a code in the chat to indicate
   * the start of a chat color.
   */
  val codePrefix = '\u00A7'

  /**
   * The set of all possible chat colors.
   */
  val values: Set[ChatColor] =
    Set(Black, DarkBlue, DarkGreen, DarkAqua,
        DarkRed, DarkPurple, Gold, Gray,
        DarkGray, Blue, Green, Aqua,
        Red, LightPurple, Yellow, White,
        Obfuscated, Bold, Strikethrough,
        Underline, Italic, Reset)

  /**
   * The set of all possible chat colors that represent color codes.
   */
  val colors: Set[ColorChatColor] =
    Set(Black, DarkBlue, DarkGreen, DarkAqua,
        DarkRed, DarkPurple, Gold, Gray,
        DarkGray, Blue, Green, Aqua,
        Red, LightPurple, Yellow, White)

  /**
   * The set of all possible chat colors that represent format codes.
   */
  val formats: Set[FormatChatColor] =
    Set(Obfuscated, Bold, Strikethrough,
        Underline, Italic)

  private final val stripColorPattern = java.util.regex.Pattern.compile(s"$codePrefix[0-9A-FK-ORa-fk-or]")
  private final val codeMappings: Map[Char, ChatColor] = values.map(c => c.code.toLower -> c).toMap ++ values.map(c => c.code.toUpper -> c).toMap

  /**
   * Retrieve a chat color by code.
   *
   * @param code The code corresponding to the chat color
   * @return The color corresponding to the code
   */
  def apply(code: Char) = codeMappings.get(code)
  def unapply(color: ChatColor): Option[Char] = Some(color.code)

  /**
   * Remove all chat colors from a string.
   *
   * @param string The string to filter
   * @return A string containing no chat colors
   */
  @tailrec def strip(string: String): String = stripColorPattern.matcher(string) match {
    case matcher if matcher.find() => strip(matcher.replaceAll(""))
    case _ => string
  }

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
