package app.apiObjects;

public class Account {
  public String accountUid;
  public String defaultCategory;

  public Account(String accountUid, String defaultCategory) {
    this.accountUid = accountUid;
    this.defaultCategory = defaultCategory;
  }
}
