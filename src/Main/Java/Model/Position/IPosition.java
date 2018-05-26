package Model.Position;

public interface IPosition {

  /**
   * Indicates if the position provided is valid or not.
   *
   * @return true if the position is valid, false otherwise.
   */
  boolean positionValid();

  /**
   * Gets the column from the position.
   *
   * @return Column.
   */
  Integer getColumn();

  /**
   * Gets the row from the position.
   *
   * @return Row.
   */
  Integer getRow();
}
