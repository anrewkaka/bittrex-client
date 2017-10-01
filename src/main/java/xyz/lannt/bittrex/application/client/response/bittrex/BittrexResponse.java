package xyz.lannt.bittrex.application.client.response.bittrex;

import xyz.lannt.bittrex.application.client.response.MarketResponse;

public abstract class BittrexResponse implements MarketResponse {

  public Boolean success;

  public String message;

  public Object result;
}
