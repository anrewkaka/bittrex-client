package xyz.lannt.bittrex.application.client.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import xyz.lannt.bittrex.application.client.annotation.MarketQueryParam;

@Data
@Builder
@AllArgsConstructor
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
