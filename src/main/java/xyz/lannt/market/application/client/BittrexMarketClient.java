package xyz.lannt.market.application.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.util.ObjectUtils;

import com.google.gson.Gson;

import xyz.lannt.market.application.client.request.BittrexMarketRequest;
import xyz.lannt.market.application.client.request.BittrexSellingRequest;
import xyz.lannt.market.application.client.request.MarketRequest;
import xyz.lannt.market.application.client.response.bittrex.BittrexBalancesResponse;
import xyz.lannt.market.application.client.response.bittrex.BittrexMarketSummariesResponse;
import xyz.lannt.market.application.client.response.bittrex.BittrexOrderHistoryResponse;
import xyz.lannt.market.application.client.response.bittrex.BittrexSellingResponse;
import xyz.lannt.market.application.exception.BittrexClientException;
import xyz.lannt.market.utils.EncryptionUtility;

public class BittrexMarketClient implements MarketClient {

  private MarketClientSetting setting;

  public BittrexMarketClient(MarketClientSetting setting) {
    this.setting = setting;
  }

  @Autowired
  private Gson gson;

  private HttpHeaders createHeaders(String url) {
    HttpHeaders headers = new HttpHeaders();
    headers.add("apisign", EncryptionUtility.buildHmacSignature(url, setting.getSercretKey()));
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

  protected String request(String uri, BittrexMarketRequest request) {
    if (request == null) {
      request = new BittrexMarketRequest(setting.getApiKey());
    }
    String url = createUrl(uri, request);
    return request(url, createHeaders(url), HttpMethod.GET, request);
  }

  public BittrexBalancesResponse getBalances() {
    return gson.fromJson(this.request("account/getbalances", null), BittrexBalancesResponse.class);
  }

  public BittrexMarketSummariesResponse getMarketSummaries() {
    return gson.fromJson(this.request("public/getmarketsummaries", null), BittrexMarketSummariesResponse.class);
  }

  public BittrexOrderHistoryResponse getOrderHistory() {
    return gson.fromJson(this.request("account/getorderhistory", null), BittrexOrderHistoryResponse.class);
  }

  public BittrexSellingResponse sell(BittrexSellingRequest request) {
    return gson.fromJson(this.request("market/selllimit", request), BittrexSellingResponse.class);
  }
}
