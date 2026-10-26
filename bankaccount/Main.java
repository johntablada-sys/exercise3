package bankaccount;

import bankaccount.exception.AccountFrozenException;
import bankaccount.exception.InsufficientFundsException;
import bankaccount.exception.InvalidAmountException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Runs example transactions against a savings account.
 */
public class Main {
  /**
   * Runs the bank account example and its transaction test cases.
   *
   * @param args command-line arguments
   */
  public static void main(String[] args)
      throws AccountFrozenException, InsufficientFundsException, InvalidAmountException {
    BankAccount acc1 = new SavingsAccount("Test0");
    System.out.println("Owner name: " + ((SavingsAccount) acc1).getOwnerName());

    System.out.println("\n--- Test case 1: Deposit test ---");
    double depositAmount = 1000.125;
    System.out.printf("Amount to be deposited: %.2f%n", depositAmount);
    acc1.deposit(depositAmount);
    System.out.printf("Amount deposited: %.2f%n", depositAmount);
    System.out.printf("Current balance: %.2f%n", acc1.getBalance());

    System.out.println("\n--- Test case 2: Withdrawal test ---");
    double withdrawalAmount = 500;
    System.out.printf("Amount to be withdrawn: %.2f%n", withdrawalAmount);
    acc1.withdraw(withdrawalAmount);
    System.out.printf("Amount withdrawn: %.2f%n", withdrawalAmount);
    System.out.printf("Current balance: %.2f%n", acc1.getBalance());

    System.out.println("\n--- Test case 3: Excessive withdrawal test ---");
    double excessiveWithdrawalAmount = 1500;
    System.out.printf("Amount to be withdrawn: %.2f%n", excessiveWithdrawalAmount);
    try {
      acc1.withdraw(excessiveWithdrawalAmount);
    } catch (InsufficientFundsException exception) {
      System.out.println("Withdrawal declined: " + exception.getMessage());
      System.out.printf("Current balance: %.2f%n", acc1.getBalance());
    }

    System.out.println("\n--- Test case 4: Negative deposit test ---");
    double negativeDepositAmount = -100;
    System.out.printf("Amount to be deposited: %.2f%n", negativeDepositAmount);
    try {
      acc1.deposit(negativeDepositAmount);
    } catch (InvalidAmountException exception) {
      System.out.println("Negative deposit rejected: " + exception.getMessage());
    }

    System.out.println("\n--- Test case 5: Negative withdrawal test ---");
    double negativeWithdrawalAmount = -100;
    System.out.printf("Amount to be withdrawn: %.2f%n", negativeWithdrawalAmount);
    try {
      acc1.withdraw(negativeWithdrawalAmount);
    } catch (InvalidAmountException exception) {
      System.out.println("Negative withdrawal rejected: " + exception.getMessage());
    }

    System.out.println("\n--- Test case 6: Freeze account test ---");
    acc1.freezeAccount();
    System.out.println("Account frozen: " + acc1.isFrozen());
    double frozenDepositAmount = 11500;
    System.out.printf("Amount to be deposited: %.2f%n", frozenDepositAmount);
    try {
      acc1.deposit(frozenDepositAmount);
    } catch (AccountFrozenException exception) {
      System.out.println("Deposit declined: " + exception.getMessage());
    }

    System.out.println("\n--- Test case 7: Withdrawal while account is frozen ---");
    double frozenWithdrawalAmount = 100;
    System.out.printf("Amount to be withdrawn: %.2f%n", frozenWithdrawalAmount);
    try {
      acc1.withdraw(frozenWithdrawalAmount);
    } catch (AccountFrozenException exception) {
      System.out.println("Withdrawal declined: " + exception.getMessage());
      System.out.printf("Current balance: %.2f%n", acc1.getBalance());
    }

    System.out.println("\n--- Test case 8: Unfreeze account test ---");
    acc1.unfreezeAccount();
    System.out.println("Account frozen: " + acc1.isFrozen());
    double unFrozenWithdrawalAmount = 100;
    System.out.printf("Amount to be withdrawn: %.2f%n", unFrozenWithdrawalAmount);
    acc1.withdraw(unFrozenWithdrawalAmount);
    System.out.printf("Amount withdrawn: %.2f%n", unFrozenWithdrawalAmount);
    System.out.printf("Current balance: %.2f%n", acc1.getBalance());

    System.out.println("\n--- Test case 9: Transaction history ---");
    System.out.println("Transaction history: " + acc1.getTransactionHistory());

    System.out.println("\n--- Test case 10: Deposit transactions sorted by amount ---");
    List<Transaction> sortedDeposits = acc1.getTransactionHistory().stream()
        .filter(transaction -> "Deposit".equals(transaction.getType()))
        .sorted((first, second) -> Double.compare(first.getAmount(), second.getAmount()))
        .collect(Collectors.toList());
    sortedDeposits.forEach(System.out::println);

    System.out.println("\n--- Test case 11: Transactions over 100 ---");
    List<Transaction> transactionsOver100 = acc1.getTransactionHistory().stream()
        .filter(transaction -> transaction.getAmount() > 100)
        .collect(Collectors.toList());
    transactionsOver100.forEach(System.out::println);

    System.out.println("\n--- Test case 12: Create bank account manager ---");
    BankAccountManager accM1 = new BankAccountManager();
    System.out.println("Created bank account manager successfully");

    System.out.println("\n--- Test case 13: Add account ---");
    int firstAccountId = accM1.addAccount(acc1);
    System.out.println("Account added successfully with ID: " + firstAccountId);

    System.out.println("\n--- Test case 14: Add savings account ---");
    int secondAccountId = accM1.addAccount(new SavingsAccount("Test1"));
    System.out.println("Account added successfully with ID: " + secondAccountId);

    System.out.println("\n--- Test case 15: List accounts ---");
    accM1.listAccounts();

    System.out.println("\n--- Exercise 3 starts here ---");

    System.out.println("\n--- Test case 16: Deposit with negative amount ---");
    System.out.printf("Amount deposited: %.2f%n", -500.0);
    try {
      acc1.deposit(-500);
    } catch (InvalidAmountException exception) {
      System.out.println(exception.getMessage());
    }

    System.out.println("\n--- Test case 17: Deposit when account is frozen ---");
    acc1.freezeAccount();
    try {
      acc1.deposit(11500);
    } catch (AccountFrozenException exception) {
      System.out.println(exception.getMessage());
    } finally {
      acc1.unfreezeAccount();
    }

    System.out.println("\n--- Test case 18: Withdraw with negative amount ---");
    System.out.printf("Amount withdrawn: %.2f%n", -100.0);
    try {
      acc1.withdraw(-100);
    } catch (InvalidAmountException exception) {
      System.out.println(exception.getMessage());
    }

    System.out.println("\n--- Test case 19: Withdraw with insufficient funds ---");
    BankAccount insufficientFundsAccount = new BankAccount();
    insufficientFundsAccount.deposit(1000.125);
    insufficientFundsAccount.withdraw(500);
    System.out.printf("Amount withdrawn: %.2f%n", 1500.0);
    try {
      insufficientFundsAccount.withdraw(1500);
    } catch (InsufficientFundsException exception) {
      System.out.println(exception.getMessage());
      System.out.printf("Current balance: %.2f%n", insufficientFundsAccount.getBalance());
    }

    System.out.println("\n--- Test case 20: Withdraw when account is frozen ---");
    acc1.freezeAccount();
    try {
      acc1.withdraw(500);
    } catch (AccountFrozenException exception) {
      System.out.println(exception.getMessage());
    } finally {
      acc1.unfreezeAccount();
    }

    System.out.println("\n--- Test case 21: Filter transactions at or above 200 ---");
    System.out.println("Filtering transactions at or above 200...");
    List<Transaction> filteredTransactions =
        accM1.filterTransactionsAtOrAbove(200, acc1.getTransactionHistory());
    System.out.println(filteredTransactions);

    System.out.println("\n--- Test case 22: Sort transactions by amount ---");
    System.out.println("Sorting transactions by amount...");
    List<Transaction> sortedTransactions =
        accM1.sortTransactionsByAmount(acc1.getTransactionHistory());
    System.out.println(sortedTransactions);
  }
}
