package app.utils;

import java.util.Scanner;

public class InputReader {

  public static String GetInput(String Prompt) {

    Scanner scanner = new Scanner(System.in);

    System.out.print(Prompt);
    String result = scanner.next();

    scanner.close();
    return result;
  }

}