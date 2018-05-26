package Model.Cells;

/**
 * Represents a cell that is unoccupied.
 */
public class OpenCell extends AbstractCell {
  /**
   * Creates a new open cell with usedOrOpen defaulted to open.
   */
  public OpenCell() {
    this.setUsedOrOpen(OPEN);
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
   * @return false always.
   */
  @Override
  public boolean isComputerCell() {
    return false;
  }
}
