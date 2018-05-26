package View;

import Model.Board.Board;
import Model.Position.Position;

public interface IConsole {
  /**
   * Print the board to the terminal.
   *
   * @param board the Tic-Tac-Toe board to be printed.
   */
  void printBoard(Board board);

  /**
   * Indicates the type of cell that is printing.
   *
   * @param board    the Tic-Tac-Toe board to be printed.
   * @param position the row and column of the cell to be printed.
   * @return A string specific to the cell type that occupies position on the board.
   */
  String getCellString(Board board, Position position);

  /**
   * Prints a message when the player wins.
   */
  void playerWon();

  /**
   * Prints a message when the computer wins.
   */
  void computerWon();

  /**
   * Prints a message when the player and computer tie.
   */
  public void tie();

  /**
   * Prints a message to the player asking for the row they would like to play at, takes the input,
   * validates the input. If the input is invalid, asked the user to type a valid input.
   */
  int getRowFromPlayer();

  /**
   * Prints a message to the player asking for the column they would like to play at, takes the
   * input, validates the input. If the input is invalid, asked the user to type a valid input.
   */
  int getColumnFromPlayer();

  /**
   * Indicates to the player that the position they entered is not open.
   */
  void positionTaken();

  /**
   * Prints the given string.
   *
   * @param string A string to be printed.
   */
  void printJSON(String string);

  /**
   * Indicates to the player that the computer will play first.
   */
  void computerFirst();
}
