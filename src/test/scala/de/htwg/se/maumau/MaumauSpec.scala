package de.htwg.se.maumau

import de.htwg.se.maumau.aview.{TUI, Welcome}
import de.htwg.se.maumau.controller.Controller
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import de.htwg.se.maumau.model.{Card, Color, Deck, Symbol, Table}

import java.io.ByteArrayInputStream
import scala.util.Random

class MaumauSpec extends AnyWordSpec with Matchers {

  "Maumau" when {
    val table = Table()
    val controller = new Controller(table)
    val welcome = new Welcome(controller)
    val tui =  TUI(controller)

    "throw card " in {
      val in = new ByteArrayInputStream("q".getBytes())
      Console.withIn(in) {
        Maumau.main(Array("3", "q"))
      }
    }
    "run normal and quit" in {
      val in = new ByteArrayInputStream("q".getBytes)
      Console.withIn(in) { Maumau.main(Array()) }
    }
  }
}