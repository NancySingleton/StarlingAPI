package app.utils;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import org.json.*;

public class Connector {
  String baseUrl = "https://api-sandbox.starlingbank.com";
  String accessToken = "eyJhbGciOiJQUzI1NiIsInppcCI6IkdaSVAifQ.H4sIAAAAAAAA_31Ty5KbMBD8lS3OO1u8DIbb3vID-YBhNNgqg0RJYjdbqfx7BBLGOFu5uOjumVaPNP6dSGuTNsFJguBRv1mHZpDq0qG6vZEek9fEzp2vqHvBAvMG-qJIoSxqAZiXKZzqvMvrc9pUVPhi_jUlbVbleVGVWdG8JhJdIPK6rhcCifSs3A89CDY_pfDeRcZ5z3UHWUYdlE1ZQXOmDKjKau90SlOqvLfTN1aho8lSrE41AnNNvgMLaJafoqt8cYeUN8J3-LHeidja0NWV1JdNxsCnvIcyzSvomBpIq_7MZ-pyzJYuS3ri5VJCUriuUUHhyK1hFC9PgvuangQpWDnZSzZHfpDWHZgIhDA-ZMtCujsIinNI15HvlTv-NNLxC87uqo20_slAKiE_pJhxCMUdDqgoRiM0AkgrZ_QQDlqYqGnVSzOik1qB7qGflbB3yd5P30A4mmbr9LiNyCPKaDywD6IuLU7T8HVHa9WISqDjVvDA3mKDUTM3dssgk-GeDfvs9n9SiBG0aUBifwOOL2ad47HxXzG2sqErbtON7NCnwZY8XNWI16Em_GLepADiEAHsRSBHvMSZgrZ_gjOoLNKe0NPQzcOt3V6Sd2o_LeD9wIA3g2Uf_G6N0u2egya_Bw8OKwF6WYhnNnYZ3cthix_mOVBrlWFiObkDsEcpXK7FD_9gFi56z3Hg4jQHbvV5ZMKF-Sf_zmIXv_HaxWBKVxbzwALihUWanfMDzlOEE25_mPAZZ3G4LjFoIx6iHNktw5ENXgcO9Ke6847X1yP78UxNoo_U3Fky_maXpdlOeeTWqsfNWh_vedWSP38BudOYLe4FAAA.nb15QWCGouWtTJZLG5bFsMk-BE-JmtbDA0Nm0CgM0fegtlTYV7BwOwlOEDt-1mnaNLbC7B7OhUDafuQSdXspiU6_3B58HsxWOZ5SuoZICcVNxGjTQ21MnljFssPWKhlvsL3QqMT9hLviey0_asiYkBLIetT3caKeAlMGCa7VmWB1PDYo5_AtAWQ1pMCw-YpRNh1RYwOGOKeV8joXWrw625p6cMwr7NniuX7CW3QjN868-0lREnWJY72lBZvW0tV_HpDU8Ur32EdhEAKoR6_9iITD9J4A1r4r4wESKJGSstVn8tSjWxalB82k4WZm7CsCf-hO9i37TpKS5jwQakr0ngVppzgXwy8suXjIU2cg67_35e_XWXkPrDe5HfKK6WjV-ph0r5D_OHh_KaiIoc15ihcwdeLJ0ARFAq7dS1uYFGvOKnC3o_JpPU6w36ERBh41pcybkxtAb2uUPryHIM8hIfKxqmaCdv-FcYj04b_1ANPT0aj9FbZRVmAXUHsSnjWdJpRKPIRCcpthlyC2dL3QEm4edjuVPQjwa22AKis_ZqbF6jeIkKrxA_TXR3jTbi0NJ0JUq3TFSvNvkknUAdW9cVDEvsv63ubF3RDSUAIb8KAPHMbUTR3QnQrDya3qY-glXzldiqFRx08UdCUHQ0QI95NE8AHajl9a3DKUjN80AlA";

  public JSONObject SendRequest(String endpoint) throws Exception {

    // create a client
    HttpClient client = HttpClient.newHttpClient();

    // create a request
    HttpRequest request = HttpRequest.newBuilder().uri(URI.create(baseUrl + endpoint))
        .header("Authorization", "Bearer " + accessToken).build();

    // use the client to send the request
    HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

    JSONObject JSONResponse = new JSONObject(response.body());

    return JSONResponse;

  }
}