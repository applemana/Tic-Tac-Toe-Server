package Model.Position;

import static Model.Board.Board.NUMBER_OF_COLUMNS;
import static Model.Board.Board.NUMBER_OF_ROWS;

/**
 * Represents a location given a column and a row.
 */
public class Position implements IPosition {
  private Integer row;
  private Integer column;

  /**
   * Creates a new position given a column and a row.
   *
   * @param column the horizontal location.
   * @param row    the vertical location
   */
  public Position(Integer row, Integer column) {
    this.row = row;
    this.column = column;
  }

  /**
   * Indicates if the position provided is valid or not.
   *
   * @return true if the position is valid, false otherwise.
   */
  public boolean positionValid() {
    return this.column >= 0 && this.column < NUMBER_OF_COLUMNS
        && this.row >= 0 && this.row < NUMBER_OF_ROWS;
  }

  /**
   * Gets the column from the position.
   *
   * @return Column.
   */
  public Integer getColumn() {
    return column;
  }

  /**
   * Gets the row from the position.
   *
   * @return Row.
   */
  public Integer getRow() {
    return row;
  }
}
