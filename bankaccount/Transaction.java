package bankaccount;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a transaction made on a bank account.
 */
public class Transaction {
  private static final DateTimeFormatter TIMESTAMP_FORMAT =
      DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

  String type;
  double amount;
  LocalDateTime timeStamp;

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

  @Override
  public String toString() {
    return String.format(
        "Transaction{type='%s', amount=%s, time_stamp=%s}",
        type,
        amount,
        timeStamp.format(TIMESTAMP_FORMAT));
  }
}
