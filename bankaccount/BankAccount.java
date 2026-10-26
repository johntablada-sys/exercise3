package bankaccount;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a bank account that supports balance and freeze operations.
 */
public class BankAccount {
  private BigDecimal balance = BigDecimal.ZERO;
  private final List<Transaction> transactionHistory;
  boolean isFrozen;

  public BankAccount() {
    transactionHistory = new ArrayList<>();
  }

  /**
   * Deposits a positive amount into the account.
   *
   * @param amount amount to deposit
   * @return updated account balance
   * @throws IllegalStateException if the account is frozen
   * @throws IllegalArgumentException if the amount is not positive and finite
   */
  public double deposit(double amount) {
    if (isFrozen) {
      throw new IllegalStateException("Account is FROZEN, unable to transact");
    }
    balance = balance.add(toCents(amount));
    transactionHistory.add(new Transaction("Deposit", amount));
    return getBalance();
  }

  /**
   * Withdraws a positive amount if the account is not frozen and has enough funds.
   *
   * @param amount amount to withdraw
   * @return updated account balance
   * @throws IllegalStateException if the account is frozen
   * @throws IllegalArgumentException if the amount is invalid or exceeds the balance
   */
  public double withdraw(double amount) {
    if (isFrozen) {
      throw new IllegalStateException("Account is FROZEN, unable to transact");
    }
    BigDecimal withdrawal = toCents(amount);
    if (withdrawal.compareTo(balance) > 0) {
      throw new IllegalArgumentException("Withdrawal exceeds account balance");
    }
    balance = balance.subtract(withdrawal);
    transactionHistory.add(new Transaction("Withdraw", amount));
    return getBalance();
  }

  /**
   * Gets the account's transaction history.
   *
   * @return transaction history
   */
  public List<Transaction> getTransactionHistory() {
    return transactionHistory;
  }

  /**
   * Gets the current account balance.
   *
   * @return current account balance
   */
  public double getBalance() {
    return balance.doubleValue();
  }

  private BigDecimal toCents(double amount) {
    if (!Double.isFinite(amount) || amount <= 0) {
      throw new IllegalArgumentException("Amount must be a positive finite number");
    }
    return BigDecimal.valueOf(amount).setScale(2, RoundingMode.CEILING);
  }

  /**
   * Freezes the account, preventing deposits and withdrawals.
   */
  public void freezeAccount() {
    isFrozen = true;
  }

  /**
   * Unfreezes the account, allowing deposits and withdrawals.
   */
  public void unfreezeAccount() {
    isFrozen = false;
  }

  public boolean isFrozen() {
    return isFrozen;
  }


  
}
