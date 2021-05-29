package app.apiObjects;

import org.joda.time.*;
import org.joda.time.format.*;
import org.json.*;
import app.utils.*;

public class TransactionFeed {
  String accountId;
  String categoryId;
  String startDate;
  String endpoint;

  public TransactionFeed(Account account) throws Exception {
    this.accountId = account.accountUid;
    this.categoryId = account.defaultCategory;
    this.startDate = this.getStartDate();
    this.endpoint = "/api/v2/feed/account/" + accountId + "/category/" + categoryId + "?changesSince=" + this.startDate;
    this.get();
  }

  public String getStartDate() {
    DateTime now = DateTime.now(DateTimeZone.UTC);
    DateTime startDate = now.minusDays(7);

    String dateString = formatStartDate(startDate);

    return dateString;
  }

  public String formatStartDate(DateTime startDate) {
    DateTimeFormatter fmt = ISODateTimeFormat.dateTime();
    String dateString = fmt.print(startDate);

    dateString = dateString.replace(":", "%3A");

    return dateString;
  }

  public JSONObject get() throws Exception {
    Connector connector = new Connector();
    return connector.SendRequest(endpoint);
  }
}
