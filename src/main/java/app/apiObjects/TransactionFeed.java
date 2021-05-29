package app.apiObjects;

import java.util.ArrayList;
import org.joda.time.*;
import org.joda.time.format.*;
import org.json.*;
import app.utils.*;

public class TransactionFeed {
  String accountId;
  String categoryId;
  String startDate;
  String endpoint;
  ArrayList<Transaction> transactionList = new ArrayList<Transaction>();

  public TransactionFeed(Account account) throws Exception {
    this.accountId = account.accountUid;
    this.categoryId = account.defaultCategory;
    this.startDate = this.getStartDate();
    this.endpoint = "/api/v2/feed/account/" + accountId + "/category/" + categoryId + "?changesSince=" + this.startDate;
    this.build();
  }

  private void build() throws Exception {
    Connector connector = new Connector();
    JSONObject result = connector.SendRequest(endpoint);
    JSONArray resultList = result.getJSONArray("feedItems");

    for (int i = 0; i < resultList.length(); i++) {
      JSONObject resultTransaction = resultList.getJSONObject(i);
      Transaction transaction = new Transaction(resultTransaction.getJSONObject("amount").getInt("minorUnits"),
          resultTransaction.getString("transactionTime"));
      transactionList.add(transaction);
    }
  }

  public void print() {
    for (Transaction transaction : transactionList) {
      transaction.print();
    }
  }

  private String getStartDate() {
    DateTime now = DateTime.now(DateTimeZone.UTC);
    DateTime startDate = now.minusDays(7);

    String dateString = formatStartDate(startDate);

    return dateString;
  }

  private String formatStartDate(DateTime startDate) {
    DateTimeFormatter fmt = ISODateTimeFormat.dateTime();
    String dateString = fmt.print(startDate);

    dateString = dateString.replace(":", "%3A");

    return dateString;
  }
}
