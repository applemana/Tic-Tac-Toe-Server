package Model.Game;

import Model.Board.Board;

public interface IGame {

  /**
   * Gets the game board.
   *
   * @return the board for the game.
   */
  Board getBoard();

  /**
   * Gets who's turn it is.
   *
   * @return String identifying who's turn it is.
   */
  String getWhosTurn();

  /**
   * Sets who's turn it is.
   *
   * @param whosTurn String indicated who's turn it is.
   */
  void setWhosTurn(String whosTurn);

  /**
   * Checks to see if someone has won the game.
   *
   * @return true if someone has won the game, false otherwise.
   */
  boolean isWin();
}
