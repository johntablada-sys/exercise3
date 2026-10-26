package bankaccount;

import java.util.HashMap;
import java.util.Map;

/**
 * Manages bank accounts and assigns each account a unique ID.
 */
public class BankAccountManager implements Bank {
  private final Map<Integer, BankAccount> accounts;
  private int id;

  public BankAccountManager() {
    accounts = new HashMap<>();
    id = 0;
  }

  @Override
  public int addAccount(BankAccount account) {
    if (id >= 100) {
      throw new IllegalStateException("Maximum of 100 accounts reached");
    }
    int accountId = id++;
    accounts.put(accountId, account);
    return accountId;
  }

  @Override
  public BankAccount getAccount(int id) {
    return accounts.get(id);
  }

  /**
   * Displays each account's ID, owner name, and balance.
   */
  public void listAccounts() {
    if (accounts.isEmpty()) {
      System.out.println("No accounts found.");
      return;
    }

    for (int accountId = 0; accountId < id; accountId++) {
      BankAccount account = accounts.get(accountId);
      String ownerName = account instanceof SavingsAccount
          ? ((SavingsAccount) account).getOwnerName()
          : "N/A";
      System.out.printf(
          "Account ID: %d, Account Name: %s, Balance: %s%n",
          accountId,
          ownerName,
          account.getBalance());
    }
  }
}
