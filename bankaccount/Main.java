package bankaccount;

/**
 * Runs example transactions against a savings account.
 */
public class Main {
  /**
   * Runs the bank account example and its transaction test cases.
   *
   * @param args command-line arguments
   */
  public static void main(String[] args) {
    BankAccount acc1 = new SavingsAccount("Test0");
    System.out.println("Owner name: " + ((SavingsAccount) acc1).getOwnerName());

    System.out.println("\n--- Deposit test ---");
    double depositAmount = 1000.125;
    System.out.printf("Amount to be deposited: %.2f%n", depositAmount);
    double currentBalance = acc1.deposit(depositAmount);
    System.out.printf("Amount deposited: %.2f%n", depositAmount);
    System.out.printf("Current balance: %.2f%n", currentBalance);

    System.out.println("\n--- Withdrawal test ---");
    double withdrawalAmount = 500;
    System.out.printf("Amount to be withdrawn: %.2f%n", withdrawalAmount);
    currentBalance = acc1.withdraw(withdrawalAmount);
    System.out.printf("Amount withdrawn: %.2f%n", withdrawalAmount);
    System.out.printf("Current balance: %.2f%n", currentBalance);

    System.out.println("\n--- Excessive withdrawal test ---");
    double excessiveWithdrawalAmount = 1500;
    System.out.printf("Amount to be withdrawn: %.2f%n", excessiveWithdrawalAmount);
    try {
      acc1.withdraw(excessiveWithdrawalAmount);
    } catch (IllegalArgumentException exception) {
      System.out.println("Withdrawal declined: " + exception.getMessage());
      System.out.printf("Current balance: %.2f%n", acc1.getBalance());
    }

    System.out.println("\n--- Negative deposit test ---");
    double negativeDepositAmount = -100;
    System.out.printf("Amount to be deposited: %.2f%n", negativeDepositAmount);
    try {
      acc1.deposit(negativeDepositAmount);
    } catch (IllegalArgumentException exception) {
      System.out.println("Negative deposit rejected: " + exception.getMessage());
    }

    System.out.println("\n--- Negative withdrawal test ---");
    double negativeWithdrawalAmount = -100;
    System.out.printf("Amount to be withdrawn: %.2f%n", negativeWithdrawalAmount);
    try {
      acc1.withdraw(negativeWithdrawalAmount);
    } catch (IllegalArgumentException exception) {
      System.out.println("Negative withdrawal rejected: " + exception.getMessage());
    }

    System.out.println("\n--- Freeze account test ---");
    acc1.freezeAccount();
    System.out.println("Account frozen: " + ((SavingsAccount) acc1).isFrozen());

    double frozenDepositAmount = 11500;
    System.out.printf("Amount to be deposited: %.2f%n", frozenDepositAmount);
    try {
      acc1.deposit(frozenDepositAmount);
    } catch (IllegalStateException exception) {
      System.out.println("Deposit declined: " + exception.getMessage());
    }

    System.out.println("\n--- Withdrawal while account is frozen test ---");
    double frozenWithdrawalAmount = 100;
    System.out.printf("Amount to be withdrawn: %.2f%n", frozenWithdrawalAmount);
    try {
      acc1.withdraw(frozenWithdrawalAmount);
    } catch (IllegalStateException exception) {
      System.out.println("Withdrawal declined: " + exception.getMessage());
      System.out.printf("Current balance: %.2f%n", acc1.getBalance());
    }

    System.out.println("\n--- Unfreeze account test ---");
    acc1.unfreezeAccount();
    System.out.println("Account frozen: " + ((SavingsAccount) acc1).isFrozen());

    double unFrozenWithdrawalAmount = 100;
    System.out.printf("Amount to be withdrawn: %.2f%n", unFrozenWithdrawalAmount);
    currentBalance = acc1.withdraw(unFrozenWithdrawalAmount);
    System.out.printf("Amount withdrawn: %.2f%n", unFrozenWithdrawalAmount);
    System.out.printf("Current balance: %.2f%n", currentBalance);

    System.out.println("\n--- Test case 1: Transaction history ---");
    System.out.println("Transaction history: " + acc1.getTransactionHistory());

    System.out.println("\n--- Test case 2: Create bank account manager ---");
    BankAccountManager accM1 = new BankAccountManager();
    System.out.println("\n Created bank account manager successfully");
    
    System.out.println("\n--- Test case 3: Add account ---");
    int firstAccountId = accM1.addAccount(acc1);
    System.out.println("Account added successfully with ID: " + firstAccountId);

    System.out.println("\n--- Test case 4: Add savings account ---");
    int secondAccountId = accM1.addAccount(new SavingsAccount("Test1"));
    System.out.println("Account added successfully with ID: " + secondAccountId);

    System.out.println("\n--- Test case 5: List accounts ---");
    accM1.listAccounts();
  }
}
