package xyz.lannt.market.client;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import xyz.lannt.exception.BittrexClientException;
import xyz.lannt.market.request.MarketRequest;
import xyz.lannt.market.response.MarketResponse;

public interface MarketClient {

  default String request(String url, HttpHeaders headers, HttpMethod method, MarketRequest request) {

    RestTemplate restTemplate = new RestTemplate();
    restTemplate.setRequestFactory(new SimpleClientHttpRequestFactory());

    ResponseEntity<String> response = restTemplate.exchange(url, method, new HttpEntity<>(headers), String.class);
    if (response.getStatusCode() != HttpStatus.OK) {
      throw new BittrexClientException();
    }

    return response.getBody();
  }

  public String request(String uri, MarketRequest request);

  public MarketResponse getBalances();

  public MarketResponse getMarketSummaries();

  public MarketResponse getOrderHistory();

  public MarketResponse sell(MarketRequest request);

  public MarketResponse buy(MarketRequest request);
}
