package crypt;

public class RunOutOfHashException extends Exception {
  public RunOutOfHashException(String message) {
    super(message);
  }
}