package Model.Game;

import java.util.Random;

import Model.Board.Board;

/**
 * Represents a game within Tic-Tac-Toe.
 */
public class Game implements IGame {
  private Board board;
  private String whosTurn;
  public static final String PLAYERS_TURN = "Player's Turn";
  public static final String COMPUTERS_TURN = "Computer's Turn";

  /**
   * Creates a new game and determines, randomly, which player will start the game.
   */
  public Game() {
    this.board = new Board();
    this.whoWillStart();
  }

  /**
   * Randomly determines who will play first.
   */
  private void whoWillStart() {
    Random random = new Random();

    if (random.nextInt(2) == 1) {
      this.whosTurn = COMPUTERS_TURN;
    } else {
      this.whosTurn = PLAYERS_TURN;
    }
  }

  /**
   * Gets the game board.
   *
   * @return the board for the game.
   */
  public Board getBoard() {
    return board;
  }

  /**
   * Gets who's turn it is.
   *
   * @return String identifying who's turn it is.
   */
  public String getWhosTurn() {
    return whosTurn;
  }

  /**
   * Sets who's turn it is.
   *
   * @param whosTurn String indicated who's turn it is.
   */
  public void setWhosTurn(String whosTurn) {
    this.whosTurn = whosTurn;
  }

  /**
   * Checks to see if someone has won the game.
   *
   * @return true if someone has won the game, false otherwise.
   */
  public boolean isWin() {
    if (this.whosTurn.equals(PLAYERS_TURN)) {
      return this.board.checkRows(PLAYERS_TURN)
          || this.board.checkColumns(PLAYERS_TURN)
          || this.board.checkDiagonal(PLAYERS_TURN);
    } else {
      return this.board.checkRows(COMPUTERS_TURN)
          || this.board.checkColumns(COMPUTERS_TURN)
          || this.board.checkDiagonal(COMPUTERS_TURN);
    }
  }
}
