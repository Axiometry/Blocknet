package me.axiometry.blocknet

import scala.collection.mutable

class ChatColorSpec extends UnitSpec {
  describe("ChatColor") {
    val (codes, colors, formats) = ("0123456789abcdefklmnor", "0123456789abcdef", "klmno")

    describe("apply") {
      it("should map to a ChatColor for every valid code") {
        for(c <- codes) {
          assert(ChatColor(c).isDefined)
          assert(ChatColor(c.toUpper).isDefined)
        }
      }
      it("should map to None for every invalid code") {
        for(c <- Char.MinValue to Char.MaxValue; if !codes.contains(c.toLower))
          assert(ChatColor(c).isEmpty)
      }
      it("should map every code to a distinct ChatColor") {
        val set = mutable.Set[ChatColor]()

        for(c <- codes; color = ChatColor(c).get) {
          assert(!set.contains(color))
          set += color
        }
      }
      it("should map color codes to ColorChatColors") {
        for(c <- colors; color = ChatColor(c).get) {
          assert(color.isInstanceOf[ColorChatColor])
          assert(!color.isInstanceOf[FormatChatColor])
          assert(!color.isInstanceOf[ResetChatColor])
        }
      }
      it("should map format codes to FormatChatColors") {
        for(c <- formats; color = ChatColor(c).get) {
          assert(!color.isInstanceOf[ColorChatColor])
          assert(color.isInstanceOf[FormatChatColor])
          assert(!color.isInstanceOf[ResetChatColor])
        }
      }
      it("should map reset code to ResetChatColor") {
        val color = ChatColor('r').get
        assert(!color.isInstanceOf[ColorChatColor])
        assert(!color.isInstanceOf[FormatChatColor])
        assert(color.isInstanceOf[ResetChatColor])
      }
    }

    describe("unapply") {
      it("should map every ChatColor to its respective code") {
        for(c <- codes; color = ChatColor(c).get) color match {
          case ChatColor(code) => assert(c == code)
          case _ => fail()
        }
      }
    }

    describe("strip") {
      it("should filter strings for color codes properly") {
        val targets = Map("$a$b$c$d$e$f$g$h$i$j$k$l$m$n$o$p$q$r$s$t$u$v$w$x$y$z" -> "$g$h$i$j$p$q$s$t$u$v$w$x$y$z",
                          "$A$B$C$D$E$F$G$H$I$J$K$L$M$N$O$P$Q$R$S$T$U$V$W$X$Y$Z" -> "$G$H$I$J$P$Q$S$T$U$V$W$X$Y$Z",
                          "$aaa$$a$aa$$$a" -> "aa$$",
                          "$$$$$$$aaaaaaa" -> "",
                          "a$" -> "a$",
                          "aaaaaaaaaaaaaa" -> "aaaaaaaaaaaaaa",
                          "$$$$$$$$$$$$$$" -> "$$$$$$$$$$$$$$")
        val prefixReplacer = (c: Char) => if(c == '$') ChatColor.codePrefix else c
        for((a, b) <- targets; original = a.map(prefixReplacer); target = b.map(prefixReplacer)) {
          info(s"Convert '$original' into '$target'")

          assert(ChatColor.strip(original) == target)
        }
      }
    }

    describe("values") {
      it("should be the set of all ChatColors") {
        val set = ChatColor.values.to[mutable.Set]

        for(c <- codes; color = ChatColor(c).get) {
          assert(set.contains(color))
          set -= color
        }

        assert(set.isEmpty)
      }
    }

    describe("colors") {
      it("should be the set of all color ChatColors") {
        val set = ChatColor.colors.to[mutable.Set]

        for(c <- colors; color = ChatColor(c).get.asInstanceOf[ColorChatColor]) {
          assert(set.contains(color))
          set -= color
        }
      }
    }

    describe("formats") {
      it("should be the set of all format ChatColors") {
        val set = ChatColor.formats.to[mutable.Set]

        for(c <- formats; color = ChatColor(c).get.asInstanceOf[FormatChatColor]) {
          assert(set.contains(color))
          set -= color
        }
      }
    }
  }
}