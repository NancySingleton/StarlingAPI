package app.apiObjects;

import java.net.URI;
import java.net.URLEncoder;
import java.net.http.*;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.stream.Collectors;

import org.json.*;

import app.errors.RequestFailedException;

public class Token {
  static String baseUrl = "https://api-sandbox.starlingbank.com";
  static String endpoint = "/oauth/access-token";
  static String refreshToken = "yANuelsr1HCubgo7YfmsmAwRF3J6pwPhlJ3YAg4EvIBTOkOkVwRtJxs8koGyugrB";
  static String clientId = "4AyyfYrHeAgNffRCfWLS";
  static String clientSecret = "dvp6FBZWo2mIOnlafrjJLXEw0sGnCINS6D3GW53E";

  public static String RefreshToken() throws Exception {
    // create a client
    HttpClient client = HttpClient.newHttpClient();

    HashMap<String, String> requestBodyValues = new HashMap<String, String>() {
      {
        put("refresh_token", refreshToken);
        put("client_id", clientId);
        put("client_secret", clientSecret);
        put("grant_type", "refresh_token");
      }
    };

    String form = requestBodyValues.keySet().stream()
        .map(key -> key + "=" + URLEncoder.encode(requestBodyValues.get(key), StandardCharsets.UTF_8))
        .collect(Collectors.joining("&"));

    HttpRequest request = HttpRequest.newBuilder().uri(URI.create(baseUrl + endpoint))
        .header("Content-Type", "application/x-www-form-urlencoded").POST(HttpRequest.BodyPublishers.ofString(form))
        .build();

    HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

    if (response.statusCode() == 200) {
      JSONObject JSONResponse = new JSONObject(response.body());
      String AccessToken = JSONResponse.getString("access_token");
      return AccessToken;
    } else {
      throw new RequestFailedException(response.body());
    }

  }

}
