package xyz.lannt.bittrex.domain.model;

import xyz.lannt.bittrex.application.client.annotation.BittrexResponseName;
import xyz.lannt.bittrex.application.client.response.bittrex.BittrexResult;

public class BittrexSelling {
  
  @BittrexResponseName("uuid")
  private String orderId;
  
  public static BittrexSelling fromResult(BittrexResult result) {
    return result.toModel(BittrexSelling.class);
  }
}
