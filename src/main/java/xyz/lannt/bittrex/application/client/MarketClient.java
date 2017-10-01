package xyz.lannt.bittrex.application.client;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import xyz.lannt.bittrex.application.client.request.MarketRequest;

public interface MarketClient {

  default String request(String url, HttpHeaders headers, HttpMethod method, MarketRequest request) {

    RestTemplate restTemplate = new RestTemplate();
    restTemplate.setRequestFactory(new SimpleClientHttpRequestFactory());

    ResponseEntity<String> response = restTemplate.exchange(url, method, new HttpEntity<>(headers), String.class);
    return response.getBody();
  }
}
