package bankaccount.exception;

/**
 * Indicates that a transaction was attempted on a frozen account.
 */
public class AccountFrozenException extends Exception {
  private static final long serialVersionUID = 1L;
  private final String message;

  /**
   * Creates an exception with a detail message.
   *
   * @param message explanation of the frozen account condition
   */
  public AccountFrozenException(String message) {
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
