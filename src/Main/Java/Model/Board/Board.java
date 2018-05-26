package Model.Board;

import Model.Cells.AbstractCell;
import Model.Cells.ComputerCell;
import Model.Cells.ICell;
import Model.Cells.OpenCell;
import Model.Cells.PlayerCell;
import Model.Exceptions.IllegalOperationException;
import Model.Position.Position;
import View.Console;

import static Model.Game.Game.PLAYERS_TURN;

/**
 * Represent the Tic-Tac-Toe board.
 */
public class Board implements IBoard {
  private ICell[][] cells;
  public static final Integer NUMBER_OF_COLUMNS = 3;
  public static final Integer NUMBER_OF_ROWS = 3;

  /**
   * Creates a new board.
   */
  public Board() {
    this.cells = new ICell[NUMBER_OF_ROWS][NUMBER_OF_COLUMNS];

    for (int i = 0; i < NUMBER_OF_ROWS; i++) {
      for (int j = 0; j < NUMBER_OF_COLUMNS; j++) {
        this.cells[i][j] = new OpenCell();
      }
    }
  }

  /**
   * Prints the board.
   *
   * @param display the console that the commands and board will print to.
   */
  public void printBoard(Console display) {
    display.printBoard(this);
  }

  /**
   * Gets the cell at position
   *
   * @param position the row and column of the cell to be returned.
   *
   * @return Cell at position.
   */
  public ICell getCellAtPosition(Position position) {
    if (position.positionValid()) {
      return cells[position.getRow()][position.getColumn()];
    }
    throw new IllegalOperationException("The position provided was not valid.");
  }

  /**
   * Sets the cell at position.
   *
   * @param cell     The cell type that will be set at position.
   * @param position The row and column of the cell to be set.
   */
  public void setCellAtPosition(AbstractCell cell, Position position) {
    if (position.positionValid()) {

      if (cell.isOpen()) {
        this.cells[position.getRow()][position.getColumn()] = new OpenCell();
      } else if (cell.isPlayerCell()) {
        this.cells[position.getRow()][position.getColumn()] = new PlayerCell();
      } else {
        this.cells[position.getRow()][position.getColumn()] = new ComputerCell();
      }
    } else {
      throw new IllegalOperationException("The position provided was not valid.");
    }
  }

  /**
   * Indicates if there are any open cells remaining.
   * @return True if there are open cells remaining, false otherwise.
   */
  public Boolean isFull() {
    for (int i = 0; i < NUMBER_OF_ROWS; i++) {
      for (int j = 0; j < NUMBER_OF_COLUMNS; j++) {
        if (this.getCellAtPosition(new Position(j,i)).isOpen()) {
          return false;
        }
      }
    }
    return true;
  }

  /**
   * Indicates if the computer or player has filled a row with their cells.
   *
   * @param whosTurn A string that indicates who's turn it is.
   *
   * @return True if the computer or player has filled a row with their cells.
   */
  public Boolean checkRows(String whosTurn) {
    if (whosTurn.equals(PLAYERS_TURN)) {
      for (int i = 0; i < NUMBER_OF_ROWS; i++) {
        if (this.getCellAtPosition(new Position(i, 0)).isPlayerCell()
            && this.getCellAtPosition(new Position(i, 1)).isPlayerCell()
            && this.getCellAtPosition(new Position(i, 2)).isPlayerCell()) {
          return true;
        }
      }
    } else {
      for (int i = 0; i < NUMBER_OF_ROWS; i++) {
        if (this.getCellAtPosition(new Position(i, 0)).isComputerCell()
            && this.getCellAtPosition(new Position(i, 1)).isComputerCell()
            && this.getCellAtPosition(new Position(i, 2)).isComputerCell()) {
          return true;
        }
      }
    }
    return false;
  }

  /**
   * Indicates if the computer or player has filled a column with their cells.
   *
   * @param whosTurn A string that indicates who's turn it is.
   *
   * @return True if the computer or player has filled a column with their cells.
   */
  public Boolean checkColumns(String whosTurn) {
    if (whosTurn.equals(PLAYERS_TURN)) {
      for (int i = 0; i < NUMBER_OF_COLUMNS; i++) {
        if (this.getCellAtPosition(new Position(0, i)).isPlayerCell()
            && this.getCellAtPosition(new Position(1, i)).isPlayerCell()
            && this.getCellAtPosition(new Position(2, i)).isPlayerCell()) {
          return true;
        }
      }
    } else {
      for (int i = 0; i < NUMBER_OF_COLUMNS; i++) {
        if (this.getCellAtPosition(new Position(0, i)).isComputerCell()
            && this.getCellAtPosition(new Position(1, i)).isComputerCell()
            && this.getCellAtPosition(new Position(2, i)).isComputerCell()) {
          return true;
        }
      }
    }
    return false;
  }

  /**
   * Indicates if the computer or player has filled a diagonal with their cells.
   *
   * @param whosTurn A string that indicates who's turn it is.
   *
   * @return True if the computer or player has filled a diagonal with their cells.
   */
  public Boolean checkDiagonal(String whosTurn) {
    return checkDownAndRight(whosTurn) || checkUpAndRight(whosTurn);
  }

  /**
   * Indicates if the computer or player has filled positions [0,0], [1,1], [2,2]
   *
   * @param whosTurn A string that indicates who's turn it is.
   *
   * @return True if the computer or player has filled positions [0,0], [1,1], [2,2] with their cells.
   */
  private Boolean checkDownAndRight(String whosTurn) {
    if (whosTurn.equals(PLAYERS_TURN)) {
      for (int i = 0; i < NUMBER_OF_ROWS; i++) {
        if (!this.getCellAtPosition(new Position(i,i)).isPlayerCell()) {
          return false;
        }
      }
    } else {
      for (int i = 0; i < NUMBER_OF_ROWS; i++) {
        if (!this.getCellAtPosition(new Position(i, i)).isComputerCell()) {
          return false;
        }
      }
    }
    return true;
  }
  /**
   * Indicates if the computer or player has filled positions [0,2], [1,1], [2,0]
   *
   * @param whosTurn A string that indicates who's turn it is.
   *
   * @return True if the computer or player has filled positions [0,2], [1,1], [2,0] with their cells.
   */
  private boolean checkUpAndRight(String whosTurn) {
    int j = NUMBER_OF_ROWS - 1;
    if (whosTurn.equals(PLAYERS_TURN)) {
      for (int i = 0; i < NUMBER_OF_COLUMNS; i++) {
        if (!this.getCellAtPosition(new Position(i, j)).isPlayerCell()) {
          return false;
        }
        j--;
      }
    } else {
      for (int i = 0; i < NUMBER_OF_COLUMNS; i++) {
        if (!this.getCellAtPosition(new Position(i, j)).isComputerCell()) {
          return false;
        }
        j--;
      }
    }
    return true;
  }
}
