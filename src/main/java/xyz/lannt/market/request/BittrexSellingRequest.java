package xyz.lannt.market.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

public class BittrexSellingRequest extends BittrexMarketRequest {

  @Getter @Setter
  private String quantity;

  @Getter @Setter
  private String rate;

  @Builder
  public BittrexSellingRequest(String apiKey, String market, String quantity, String rate) {
    super(apiKey);
    setMarket(market);
    this.quantity = quantity;
    this.rate = rate;
  }
}
