package Model.Cells;

/**
 * Represents a cell that is occupied by a player.
 */
public class PlayerCell extends AbstractCell {

  /**
   * Creates a new player cell with usedOrOpen defaulted to used.
   */
  public PlayerCell() {
    this.setUsedOrOpen(USED);
  }

  /**
   * Indicates if the cell is a player cell.
   *
   * @return true always.
   */
  @Override
  public boolean isPlayerCell() {
    return true;
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
