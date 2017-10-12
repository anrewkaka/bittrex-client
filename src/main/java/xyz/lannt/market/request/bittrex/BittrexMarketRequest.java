package xyz.lannt.market.request.bittrex;

import lombok.Data;
import xyz.lannt.annotation.MarketQueryParam;
import xyz.lannt.market.request.MarketRequest;

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
