package de.htwg.se.maumau.model

import scala.util.Random

abstract class AbstractTable(player: List[Player] , tableDecks: List[Deck]) {


  def checkCard(table: Table, playerNumber: Int, cardNumber: Int): Boolean = {
    val currentPlayer = table.player(playerNumber)
    ((currentPlayer.playerDeck.cards.lift(cardNumber - 1).get.symbol == tableDecks(1).cards.last.symbol) ||
      (currentPlayer.playerDeck.cards.lift(cardNumber - 1).get.color == tableDecks(1).cards.last.color) ||
      currentPlayer.playerDeck.cards.lift(cardNumber - 1).get.symbol == Symbol.Jack)
  }

  def checkDeck(table: Table, playerNumber: Int): Boolean = {
    false
  }

  def addPlayers(table: Table, name: String, playerNumber: Int): Table = {
    val emptyDeck = Deck()
    val changedDeck = tableDecks.head.throwCards(1, emptyDeck)
    val newPlayer = Player(name, changedDeck._1)
    val newDeck = changedDeck._2
    val newTable = table.copy(player = table.player.updated(playerNumber, newPlayer), tableDecks.updated(0, newDeck))
    newTable
  }

  def throwFirstCard(table: Table): Table = {
    val emptyDeck = Deck()
    val changedDeck = tableDecks.head.throwOneCard(1, emptyDeck)
    val newTable = table.copy(player = player,tableDecks.updated(1, changedDeck._1).updated(0, changedDeck._2))
    newTable
  }

  def throwCard(table: Table, playerNumber: Int, cardNumber: Int): Table = {
    val currentPlayer = table.player(playerNumber)
    val changedDeck = currentPlayer.playerDeck.throwOneCard(cardNumber, tableDecks(1))
    val newPlayer = Player(currentPlayer.name,changedDeck._2)
    val newTable = table.copy(player.updated(0, newPlayer), tableDecks.updated(1,changedDeck._1))
    newTable
  }

  def takeCard(table: Table, playerNumber: Int): Table = {
    val currentPlayer = table.player(playerNumber)
    val changedDeck = tableDecks.head.throwCards(1, currentPlayer.playerDeck)
    val newPlayer = Player(currentPlayer.name,changedDeck._1)
    val newTable = table.copy(player.updated(0, newPlayer), tableDecks.updated(0,changedDeck._2))
    newTable
  }


  override def toString(): String = {
    val table = new StringBuilder("\u001B[48;5;15m" + " tablecards: ")
    table.append(tableDecks(1).cards.last.toString)

    val hand = new StringBuilder(" playercards: ")
    hand.append(player.head.playerDeck.cards.map(Card => Card.toString).mkString(" "))
    val Statement = new StringBuilder()
    Statement.append(table + "\n\n")
    Statement.append(hand)
    Statement.toString()
  }

}