package Model.Cells;

/**
 * Represents a cell that the computer is occupying.
 */
public class ComputerCell extends AbstractCell {
  /**
   * Creates a new computer cell with used or open set to used.
   */
  public ComputerCell() {
    this.setUsedOrOpen(USED);
  }

  /**
   * Indicates if the cell is a player cell.
   *
   * @return false always.
   */
  @Override
  public boolean isPlayerCell() {
    return false;
  }

  /**
   * Indicates if the cell is a computer cell.
   *
   * @return true always.
   */
  @Override
  public boolean isComputerCell() {
    return true;
  }
}
