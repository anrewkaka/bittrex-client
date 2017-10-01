package xyz.lannt.bittrex.application.client.response.bittrex;

import lombok.AllArgsConstructor;
import xyz.lannt.bittrex.application.client.response.MarketResponse;

@AllArgsConstructor
public class BittrexResponse implements MarketResponse {

  public Boolean success;

  public String message;

  public Object result;
}
