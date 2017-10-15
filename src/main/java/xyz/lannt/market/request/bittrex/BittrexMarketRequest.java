package xyz.lannt.market.request.bittrex;

import lombok.Data;
import lombok.NoArgsConstructor;
import xyz.lannt.annotation.MarketQueryParam;
import xyz.lannt.market.request.MarketRequest;

@Data
@NoArgsConstructor
public class BittrexMarketRequest implements MarketRequest {

  @MarketQueryParam("apiKey")
  private String apiKey;

  @MarketQueryParam("nonce")
  private String nonce;

  @MarketQueryParam("currency")
  private String currency;

  public BittrexMarketRequest(String apiKey) {
    this.apiKey = apiKey;
    nonce = String.valueOf(System.currentTimeMillis());//EncryptionUtility.generateNonce();
  }
}
