package bankaccount;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Manages bank accounts and assigns each account a unique ID.
 */
public class BankAccountManager implements Bank {
  private final Map<Integer, BankAccount> accounts;
  private int id;

  /**
   * Creates an account manager with no accounts.
   */
  public BankAccountManager() {
    accounts = new HashMap<>();
    id = 0;
  }

  /**
   * {@inheritDoc}
   *
   * @throws IllegalStateException if the maximum number of accounts is reached
   */
  @Override
  public int addAccount(BankAccount account) {
    if (id >= 100) {
      throw new IllegalStateException("Maximum of 100 accounts reached");
    }
    int accountId = id++;
    accounts.put(accountId, account);
    return accountId;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public BankAccount getAccount(int id) {
    return accounts.get(id);
  }

  /**
   * Filters transactions whose amounts are greater than or equal to the given amount.
   *
   * @param amount minimum transaction amount, inclusive
   * @param txList transactions to filter
   * @return matching transactions
   */
  public List<Transaction> filterTransactionsAtOrAbove(
      double amount, List<Transaction> txList) {
    return txList.stream()
        .filter(transaction -> transaction.getAmount() >= amount)
        .collect(Collectors.toList());
  }

  /**
   * Sorts transactions by amount in ascending order.
   *
   * @param txList transactions to sort
   * @return a new list containing the transactions in ascending amount order
   */
  public List<Transaction> sortTransactionsByAmount(List<Transaction> txList) {
    return txList.stream()
        .sorted((first, second) -> Double.compare(first.getAmount(), second.getAmount()))
        .collect(Collectors.toList());
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
