package Model.Board;

import Model.Cells.AbstractCell;
import Model.Cells.ICell;
import Model.Position.Position;
import View.Console;

public interface IBoard {
  /**
   * Prints the board.
   *
   * @param display the console that the commands and board will print to.
   */
  void printBoard(Console display);

  /**
   * Gets the cell at position
   *
   * @param position the row and column of the cell to be returned.
   *
   * @return Cell at position.
   */
  ICell getCellAtPosition(Position position);

  /**
   * Sets the cell at position.
   *
   * @param cell     The cell type that will be set at position.
   * @param position The row and column of the cell to be set.
   */
  void setCellAtPosition(AbstractCell cell, Position position);

  /**
   * Indicates if there are any open cells remaining.
   * @return True if there are open cells remaining, false otherwise.
   */
  Boolean isFull();

  /**
   * Indicates if the computer or player has filled a row with their cells.
   *
   * @param whosTurn A string that indicates who's turn it is.
   *
   * @return True if the computer or player has filled a row with their cells.
   */
  Boolean checkRows(String whosTurn);

  /**
   * Indicates if the computer or player has filled a column with their cells.
   *
   * @param whosTurn A string that indicates who's turn it is.
   *
   * @return True if the computer or player has filled a column with their cells.
   */
  Boolean checkColumns(String whosTurn);

  /**
   * Indicates if the computer or player has filled a diagonal with their cells.
   *
   * @param whosTurn A string that indicates who's turn it is.
   *
   * @return True if the computer or player has filled a diagonal with their cells.
   */
  Boolean checkDiagonal(String whosTurn);
}
