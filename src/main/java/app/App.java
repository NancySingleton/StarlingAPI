package app;

import app.errors.InvalidAccountException;
import app.apiObjects.*;
import app.utils.*;

public class App {
  public static void main(String[] args) throws Exception {
    AccountList accountList = new AccountList();
    accountList.print();

    String inputAccount = InputReader.GetInput("Choose an account: ");

    try {
      Account chosenAccount = accountList.getOne(inputAccount);
      TransactionFeed transactionFeed = new TransactionFeed(chosenAccount);
      System.out.println(transactionFeed);
    } catch (InvalidAccountException e) {
      System.out.println("Not a valid account");
    }
  }
}