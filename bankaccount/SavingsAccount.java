package bankaccount;

/**
 * A bank account associated with an owner.
 */
public class SavingsAccount extends BankAccount {
  private final String ownerName;

  /**
   * Creates a savings account for the owner.
   *
   * @param ownerName name of the account owner
   */
  public SavingsAccount(String ownerName) {
    this.ownerName = ownerName;
  }

  /**
   * Gets the name of the account owner.
   *
   * @return account owner's name
   */
  public String getOwnerName() {
    return ownerName;
  }
}
