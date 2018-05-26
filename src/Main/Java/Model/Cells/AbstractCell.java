package Model.Cells;

/**
 * Represents an abstract cell that can be open or used.
 */
public abstract class AbstractCell implements ICell {
  private Integer usedOrOpen;
  public static final Integer OPEN = 0;
  public static final Integer USED = 1;

  /**
   * Creates a new abstract cell when given a usedOrOpen value.
   *
   * @param usedOrOpen indicates whether the cell has been used (1) or is open (0).
   */
  public AbstractCell(Integer usedOrOpen) {
    this.usedOrOpen = usedOrOpen;
  }

  /**
   * Creates a new abstract cell and defaults usedOrOpen to open (0).
   */
  public AbstractCell() {
    this.usedOrOpen = OPEN;
  }

  /**
   * Indicates if the cell is used or open.
   *
   * @return true if the cell is open, false otherwise.
   */
  public boolean isOpen() {
    return this.usedOrOpen.equals(OPEN);
  }

  /**
   * Gets the used or open value.
   *
   * @return 1 if used, 0 if open.
   */
  public Integer getUsedOrOpen() {
    return usedOrOpen;
  }

  /**
   * Sets the value of the cell to used or open.
   *
   * @param usedOrOpen indicates whether the cell has been used (1) or is open (0).
   */
  void setUsedOrOpen(Integer usedOrOpen) {
    this.usedOrOpen = usedOrOpen;
  }
}
