package Model.Exceptions;

/**
 * Represents an illegal operation on a List.
 */
public class IllegalOperationException extends RuntimeException {

  public IllegalOperationException(String msg) {
    super(msg);
  }
}
