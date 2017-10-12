package xyz.lannt.market.request;

import lombok.Data;
import xyz.lannt.annotation.MarketQueryParam;

@Data
public class BittrexMarketRequest implements MarketRequest {

  @MarketQueryParam("apiKey")
  private String apiKey;

  @MarketQueryParam("market")
  private String market;

  @MarketQueryParam("nonce")
  private String nonce;

  public BittrexMarketRequest(String apiKey) {
    this.apiKey = apiKey;
    nonce = String.valueOf(System.currentTimeMillis());//EncryptionUtility.generateNonce();
  }
}
