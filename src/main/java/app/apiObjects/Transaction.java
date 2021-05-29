package app.apiObjects;

public class Transaction {
  int amount;
  String transactionTime;

  public Transaction(int amount, String transactionTime) {
    this.amount = amount;
    this.transactionTime = transactionTime;
  }

  public void print() {
    System.out.println(amount + " at " + transactionTime);
  }

}
