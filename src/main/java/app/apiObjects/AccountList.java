package app.apiObjects;

import org.json.*;
import java.util.ArrayList;
import app.errors.InvalidAccountException;
import app.utils.Connector;

public class AccountList {
    ArrayList<Account> accountList = new ArrayList<Account>();
    String endpoint = "/api/v2/accounts";

    public AccountList() throws Exception {
        this.get();
    }

    public ArrayList<Account> get() throws Exception {
        Connector connector = new Connector();
        JSONObject result = connector.SendRequest(endpoint);
        System.out.println(result);
        JSONArray resultList = result.getJSONArray("accounts");

        for (int i = 0; i < resultList.length(); i++) {
            JSONObject resultAccount = resultList.getJSONObject(i);
            Account account = new Account(resultAccount.getString("accountUid"),
                    resultAccount.getString("defaultCategory"));
            accountList.add(account);
        }

        return accountList;
    }

    public void print() {
        for (Account account : this.accountList) {
            System.out.println(account.accountUid);
        }
    }

    public Account getOne(String accountId) throws Exception {

        for (Account account : this.accountList) {
            if (account.accountUid.equals(accountId)) {
                return account;
            }
        }

        throw new InvalidAccountException("Not a valid account");

    }
}