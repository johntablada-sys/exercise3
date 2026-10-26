package bankaccount;

/**
 * Defines account management operations.
 */
public interface Bank {
  int addAccount(BankAccount account);

  BankAccount getAccount(int id);
}
