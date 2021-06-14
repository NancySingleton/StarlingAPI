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

  public static String GetToken() {
    return "eyJhbGciOiJQUzI1NiIsInppcCI6IkdaSVAifQ.H4sIAAAAAAAA_31Ty3LbMAz8lYzOQUYvW49bbv2BfgAEQjbHEqkhqaSZTv-9lEhZlpvpTbsLgLsk9DuR1iZtgpMEwaN-sw7NINWlQ3V7Iz0mr4mdO19R9YIF5g30RZFCWVQCMC9TOFV5l1d12pyp8MX8a0ra7JwXaVUWTfGaSHQrkTd11SwEEulZuR96EGx-SuFnFxnnPVcdZBl1UDblGZqaMqBzVuV5cUpTOvvZTt9YhY6uL6u6OBF02WnpKErAhmoQXd0Rn-qywcx3-FjvRGxt7CqpL5uMgU95D2Wan6FjaiA99zXX1OWYiSUw6YmXSwlO4bpaBYUjt4ZRvDwJ7mt6EqRg5WQv2Rz5QVp3YCIQwniTLQvp7iAoziFdR75X7vjTSMcvOLurNtL6JwOphPyQYsYhFHc4oKJojdAIIK2c0UM4aGGiplUvzYhOagW6h35Wwt4lez99A-Fomq3T4xaRR5Rx8MDeiLq0OE3D1x2tVSMqgY5bwQP7ERuMmrmxW4JMhns27L3b_0nBRtCmAYn9DTi-mDXHY-O_YmxlQ1fc0o3s0LvBljxc1YjXUBN-MW9SADFEAHsRyBEvMVPQ9k9wBpVF2h16Grp5uLXbS_JO7acFvB8Y8DZg2Qe_W6N0-8xBk9-DhwkrAXpZiGc2dhndy2GzH_IcqLXKMLGc3AHYoxQu1-KHfzALF737OHAxzYFb5zwy4cL8k383Yhe_mbWLYShdWcwDC4gXFml2zgecpwgn3H6Y8BmzOFyXGLQRD1aO7ObhyIZZBw70p7rzjtfXI_vxTE2ij9TcWTL-Zpel2U555Naqx81aH-951ZI_fwGQtq6t7gUAAA.M8zNEg1dTMXU9OufnYIc5V034_UlMGqk6sQujTPgkp5QteuMGNcIIgoAc1d8t0XLcljoOYIctxZacTk7h-HWmWnpR-ogiG-Ly4F0-N3X8OmvyLMuE0XZGryBwtRNkr_VqiaUDfwCoyHpuUL10VTjke2qzdBvfp7V6wcqF3Ji651HmhnXGFPlPMQNeqI-UXp0nxIhyIj3LeqTzEFAa_YuZwH2QYrxSI5VwdhQYJ_Oyk-3inVHcCGblLiURwh-FyAtJ0hPqVBTvyIrHKNyV0VXl25_Skkccna6fWlVwZ920suHa3x2QlTzXc8im1a9VKlzhOzMuTwhKxlrAFKAi-iRFrB0iUwqPS19bNZ8rtDjbAhBipTEiKbygGoCXrsNlFcNqNjX3q2iku0F2wvlpF0hNPQnhYy1bxOtY_roEQ_jh3HdoCB6Lurg9O08JrDEKernQ_VP52L59DUrZqCr_eABkckxjLkabccEqt6GttUFOIj2slgKzxFXD2vCzaxAHMl8V7s0IzO-xNAYjXRnimXbU5J2tW1XPvud3sAjw-d_2d9mXVTV7qaXWG6li5N4WJDw3AwC8bLgmvXJ9GgQX04UZb4MjTC7PlDF4S8mal7llgPjrr2sxayCAizBHpXyOqU_hRgAAnljXHLZcHXbwK3wxwJRmoVeramaIR_8d-tw4D4";
  }

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
