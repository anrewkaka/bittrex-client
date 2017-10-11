package xyz.lannt.market.domain.model;

import xyz.lannt.market.application.client.annotation.BittrexResponseName;
import xyz.lannt.market.application.client.response.bittrex.BittrexResult;

public class BittrexSelling {
  
  @BittrexResponseName("uuid")
  private String orderId;
  
  public static BittrexSelling fromResult(BittrexResult result) {
    return result.toModel(BittrexSelling.class);
  }
}
