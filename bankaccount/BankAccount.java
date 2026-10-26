package bankaccount;

import bankaccount.exception.AccountFrozenException;
import bankaccount.exception.InsufficientFundsException;
import bankaccount.exception.InvalidAmountException;
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
  private boolean isFrozen;

  /**
   * Creates an empty, unfrozen bank account.
   */
  public BankAccount() {
    transactionHistory = new ArrayList<>();
  }

  /**
   * Deposits a positive amount into the account.
   *
   * @param amount amount to deposit
   * @throws AccountFrozenException if the account is frozen
   * @throws InvalidAmountException if the amount is not positive and finite
   */
  public void deposit(double amount) throws AccountFrozenException, InvalidAmountException {
    if (isFrozen) {
      throw new AccountFrozenException("Account is FROZEN. Unable to transact.");
    }
    if (!Double.isFinite(amount) || amount <= 0) {
      throw new InvalidAmountException("Invalid amount. Must be greater than 0");
    }
    balance = balance.add(toCents(amount));
    transactionHistory.add(new Transaction("Deposit", amount));
  }

  /**
   * Withdraws a positive amount if the account is not frozen and has enough funds.
   *
   * @param amount amount to withdraw
   * @throws AccountFrozenException if the account is frozen
   * @throws InvalidAmountException if the amount is not positive and finite
   * @throws InsufficientFundsException if the amount exceeds the account balance
   */
  public void withdraw(double amount)
      throws AccountFrozenException, InvalidAmountException, InsufficientFundsException {
    if (isFrozen) {
      throw new AccountFrozenException("Account is FROZEN. Unable to transact.");
    }
    if (!Double.isFinite(amount) || amount <= 0) {
      throw new InvalidAmountException("Invalid amount. Must be greater than 0");
    }
    BigDecimal withdrawal = toCents(amount);
    if (withdrawal.compareTo(balance) > 0) {
      throw new InsufficientFundsException("Insufficient balance.");
    }
    balance = balance.subtract(withdrawal);
    transactionHistory.add(new Transaction("Withdraw", amount));
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

  /**
   * Checks whether the account is frozen.
   *
   * @return {@code true} if the account is frozen
   */
  public boolean isFrozen() {
    return isFrozen;
  }
}
