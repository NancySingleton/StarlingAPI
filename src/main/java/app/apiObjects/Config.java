package app.apiObjects;

// import org.json.*;

import java.io.FileReader;
// import java.io.FileWriter;
// import java.io.IOException;
import org.json.simple.parser.JSONParser;
// import org.json.simple.parser.ParseException;

public class Config {

  public static String GetAccessToken() throws Exception {

    JSONParser jsonParser = new JSONParser();
    FileReader reader = new FileReader("src\\main\\java\\app\\config.json");
    Object obj = jsonParser.parse(reader);
    System.out.println(obj);

    return "token";
  }

}
