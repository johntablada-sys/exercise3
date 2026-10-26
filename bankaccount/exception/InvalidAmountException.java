package bankaccount.exception;

/**
 * Indicates that a deposit or withdrawal amount is invalid.
 */
public class InvalidAmountException extends Exception {
  private static final long serialVersionUID = 1L;
  private final String message;

  /**
   * Creates an exception with a detail message.
   *
   * @param message explanation of the invalid amount
   */
  public InvalidAmountException(String message) {
    super(message);
    this.message = message;
  }

  /**
   * Gets the exception message.
   *
   * @return message passed to the constructor
   */
  @Override
  public String getMessage() {
    return message;
  }
}
