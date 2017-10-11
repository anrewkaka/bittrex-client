package xyz.lannt.market.application.client.response.bittrex;

import java.util.List;

import com.google.gson.internal.LinkedTreeMap;

import lombok.AllArgsConstructor;
import lombok.Getter;
import xyz.lannt.market.application.client.response.MarketResponse;

@AllArgsConstructor
public class BittrexResponse implements MarketResponse {

  @Getter
  private Boolean success;

  @Getter
  private String message;

  private List<LinkedTreeMap<String, Object>> result;

  public BittrexResults getResult() {
    return new BittrexResults(result);
  }
}
