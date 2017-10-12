package xyz.lannt.market.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.util.ObjectUtils;

import com.google.gson.Gson;

import xyz.lannt.exception.BittrexClientException;
import xyz.lannt.market.request.BittrexMarketRequest;
import xyz.lannt.market.request.MarketRequest;
import xyz.lannt.market.response.MarketResponse;
import xyz.lannt.market.response.bittrex.BittrexBalancesResponse;
import xyz.lannt.market.response.bittrex.BittrexBuyingResponse;
import xyz.lannt.market.response.bittrex.BittrexMarketSummariesResponse;
import xyz.lannt.market.response.bittrex.BittrexOrderHistoryResponse;
import xyz.lannt.market.response.bittrex.BittrexSellingResponse;
import xyz.lannt.utils.EncryptionUtility;

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

  @Override
  public String request(String uri, MarketRequest request) {
    if (request == null) {
      request = new BittrexMarketRequest(setting.getApiKey());
    }
    String url = createUrl(uri, request);
    return request(url, createHeaders(url), HttpMethod.GET, request);
  }

  @Override
  public MarketResponse getBalances() {
    return gson.fromJson(this.request("account/getbalances", null), BittrexBalancesResponse.class);
  }

  @Override
  public MarketResponse getMarketSummaries() {
    return gson.fromJson(this.request("public/getmarketsummaries", null), BittrexMarketSummariesResponse.class);
  }

  @Override
  public MarketResponse getOrderHistory() {
    return gson.fromJson(this.request("account/getorderhistory", null), BittrexOrderHistoryResponse.class);
  }

  @Override
  public MarketResponse sell(MarketRequest request) {
    return gson.fromJson(this.request("market/selllimit", request), BittrexSellingResponse.class);
  }

  @Override
  public MarketResponse buy(MarketRequest request) {
    return gson.fromJson(this.request("market/buylimit", request), BittrexBuyingResponse.class);
  }
}
