package app.utils;

import java.net.URI;
import java.net.http.*;
import org.json.*;

import app.apiObjects.Token;
import app.errors.RequestFailedException;

public class Connector {
  String baseUrl = "https://api-sandbox.starlingbank.com";

  public JSONObject SendRequest(String endpoint) throws Exception {

    // create a client
    HttpClient client = HttpClient.newHttpClient();

    // Get token
    String accessToken = Token.RefreshToken();

    // create a request
    HttpRequest request = HttpRequest.newBuilder().uri(URI.create(baseUrl + endpoint))
        .header("Authorization", "Bearer " + accessToken).build();

    // use the client to send the request
    HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

    if (response.statusCode() == 200) {
      JSONObject JSONResponse = new JSONObject(response.body());
      return JSONResponse;
    } else {
      throw new RequestFailedException(response.body());
    }

  }
}