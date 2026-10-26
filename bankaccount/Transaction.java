package bankaccount;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a transaction made on a bank account.
 */
public class Transaction {
  private static final DateTimeFormatter TIMESTAMP_FORMAT =
      DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

  private final String type;
  private final double amount;
  private final LocalDateTime timeStamp;

  /**
   * Creates a transaction with the current local date and time.
   *
   * @param type transaction type
   * @param amount transaction amount
   */
  public Transaction(String type, double amount) {
    this.type = type;
    this.amount = amount;
    this.timeStamp = LocalDateTime.now();
  }

  /**
   * Gets the transaction type.
   *
   * @return transaction type
   */
  public String getType() {
    return type;
  }

  /**
   * Gets the transaction amount.
   *
   * @return transaction amount
   */
  public double getAmount() {
    return amount;
  }

  /**
   * Gets the date and time of the transaction.
   *
   * @return transaction date and time
   */
  public LocalDateTime getTimeStamp() {
    return timeStamp;
  }

  /**
   * Returns a formatted description of this transaction.
   *
   * @return formatted transaction details
   */
  @Override
  public String toString() {
    return String.format(
        "Transaction [type=%s, amount=%s, time_stamp=%s]",
        type,
        amount,
        timeStamp.format(TIMESTAMP_FORMAT));
  }
}
