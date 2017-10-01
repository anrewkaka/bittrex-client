package xyz.lannt.bittrex.application.client.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import xyz.lannt.bittrex.application.client.annotation.MarketQueryParam;
import xyz.lannt.bittrex.utils.EncryptionUtility;

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
    nonce = EncryptionUtility.generateNonce();
  }
}
