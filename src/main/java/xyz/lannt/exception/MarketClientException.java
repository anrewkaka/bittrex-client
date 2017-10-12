package xyz.lannt.exception;

public class MarketClientException extends RuntimeException {

  private static final long serialVersionUID = 5180693992102811811L;

  public MarketClientException() {
    super();
  }

  public MarketClientException(String message) {
    super(message);
  }

  public MarketClientException(Throwable cause) {
    super(cause);
  }
}
