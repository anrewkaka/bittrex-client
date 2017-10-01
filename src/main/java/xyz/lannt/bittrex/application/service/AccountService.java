package xyz.lannt.bittrex.application.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xyz.lannt.bittrex.application.client.BittrexMarketClient;
import xyz.lannt.bittrex.application.client.response.MarketResponse;

@Service
public class AccountService {

  @Autowired
  private BittrexMarketClient bittrexMarketClient;

  public MarketResponse getBalances() {
    return bittrexMarketClient.getBalances();
  }
}
