package xyz.lannt.bittrex.application.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.util.ObjectUtils;

import com.google.gson.Gson;

import xyz.lannt.bittrex.application.client.request.BittrexMarketRequest;
import xyz.lannt.bittrex.application.client.request.MarketRequest;
import xyz.lannt.bittrex.application.client.response.MarketResponse;
import xyz.lannt.bittrex.application.client.response.bittrex.BittrexResponse;
import xyz.lannt.bittrex.application.exception.BittrexClientException;
import xyz.lannt.bittrex.utils.EncryptionUtility;

public class BittrexMarketClient implements MarketClient {

  private MarketClientSetting setting;

  public BittrexMarketClient(MarketClientSetting setting) {
    this.setting = setting;
  }

  @Autowired
  private Gson gson;

  private HttpHeaders createHeaders() {
    HttpHeaders headers = new HttpHeaders();
    headers.add("apisign", EncryptionUtility.calculateHash(setting.getSercretKey(), setting.getBaseUrl(), "HmacSHA512"));
    return headers;
  }

  private String createUrl(String uri, MarketRequest request) {
    String url = setting.getBaseUrl() + uri;

    try {
      if (!ObjectUtils.isEmpty(request)) {
        url += "?" + request.toQueryParam();
      }
    } catch (IllegalAccessException e) {
      throw new BittrexClientException(e);
    }

    return url;
  }

  public MarketResponse getBalances() {
    BittrexMarketRequest request = new BittrexMarketRequest(setting.getApiKey());
    String response = request(createUrl("account/getbalances", request), createHeaders(), HttpMethod.GET, request);
    return gson.fromJson(response, BittrexResponse.class);
  }

}
