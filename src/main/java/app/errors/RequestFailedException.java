package app.errors;

public class RequestFailedException extends Exception {
  public RequestFailedException(String errorMessage) {
    super(errorMessage);
  }
}
