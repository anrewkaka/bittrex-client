package xyz.lannt.bittrex.application.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.util.ObjectUtils;

import com.google.gson.Gson;

import xyz.lannt.bittrex.application.client.request.BittrexMarketRequest;
import xyz.lannt.bittrex.application.client.request.MarketRequest;
import xyz.lannt.bittrex.application.client.response.bittrex.BittrexBalancesResponse;
import xyz.lannt.bittrex.application.exception.BittrexClientException;
import xyz.lannt.bittrex.utils.EncryptionUtility;

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

  private String request(BittrexMarketRequest request) {
    if (request == null) {
      request = new BittrexMarketRequest(setting.getApiKey());
    }
    String url = createUrl("account/getbalances", request);
    return request(url, createHeaders(url), HttpMethod.GET, request);
  }

  public BittrexBalancesResponse getBalances() {
    return gson.fromJson(this.request(null), BittrexBalancesResponse.class);
  }

}
