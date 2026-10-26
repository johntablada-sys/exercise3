package bankaccount;

/**
 * Defines account management operations.
 */
public interface Bank {
  /**
   * Adds an account to the bank.
   *
   * @param account account to add
   * @return the assigned account ID
   */
  int addAccount(BankAccount account);

  /**
   * Gets an account by its ID.
   *
   * @param id account ID
   * @return the account, or {@code null} if no account has that ID
   */
  BankAccount getAccount(int id);
}
