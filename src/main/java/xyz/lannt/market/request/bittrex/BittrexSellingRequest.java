package xyz.lannt.market.request.bittrex;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
public class BittrexSellingRequest extends BittrexMarketRequest {

  @Getter @Setter
  private String quantity;

  @Getter @Setter
  private String rate;

  @Builder
  public BittrexSellingRequest(String apiKey, String currency, String quantity, String rate) {
    super(apiKey);
    setCurrency(currency);
    this.quantity = quantity;
    this.rate = rate;
  }
}
