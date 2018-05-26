package Model.Cells;

import java.util.Arrays;

public interface ICell {

  /**
   * Indicants if the cell has been used by the player or the computer.
   *
   * @return zero if open, one otherwise.
   */
  Integer getUsedOrOpen();

  /**
   * Indicates if the cell is open or not.
   *
   * @return true if the cell is open, false otherwise.
   */
  boolean isOpen();

  /**
   * Indicates if the cell is a player cell or not.
   *
   * @return true if the cell is a player cell, false otherwise.
   */
  boolean isPlayerCell();

  /**
   * Indicates if the cell is a computer cell or not.
   *
   * @return true if the cell is a player cell, false otherwise.
   */
  boolean isComputerCell();
}
