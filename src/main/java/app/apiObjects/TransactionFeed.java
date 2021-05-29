package app.apiObjects;

import org.json.*;
import app.utils.*;

public class TransactionFeed {
  String accountId;
  String categoryId;
  String endpoint;

  public TransactionFeed(String accountId, String categoryId) throws Exception {
    this.accountId = accountId;
    this.categoryId = categoryId;
    this.endpoint = "/api/v2/feed/account/" + accountId + "/category/" + categoryId + "?changesSince="
        + "2020-01-01T12%3A34%3A56.000Z";
    this.get();
  }

  public JSONObject get() throws Exception {
    Connector connector = new Connector();
    return connector.SendRequest(endpoint);
  }
}
