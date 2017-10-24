package xyz.lannt.market.request.bittrex;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import xyz.lannt.annotation.MarketQueryParam;

public class BittrexSellingRequest extends BittrexMarketRequest {

  @Getter @Setter
  @MarketQueryParam("quantity")
  private String quantity;

  @Getter @Setter
  @MarketQueryParam("rate")
  private String rate;

  @Getter @Setter
  @MarketQueryParam("market")
  private String market;

  @Builder
  public BittrexSellingRequest(String apiKey) {
    super(apiKey);
  }
}
