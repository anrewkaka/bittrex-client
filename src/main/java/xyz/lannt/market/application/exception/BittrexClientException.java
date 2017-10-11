package xyz.lannt.market.application.exception;

public class BittrexClientException extends RuntimeException {

  private static final long serialVersionUID = 5180693992102811811L;

  public BittrexClientException() {
    super();
  }

  public BittrexClientException(String message) {
    super(message);
  }

  public BittrexClientException(Throwable cause) {
    super(cause);
  }
}
