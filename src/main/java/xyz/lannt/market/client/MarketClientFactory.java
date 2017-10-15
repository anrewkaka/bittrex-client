package xyz.lannt.market.client;

import static xyz.lannt.constant.Market.BITTREX;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import xyz.lannt.constant.Market;
import xyz.lannt.exception.MarketClientException;
import xyz.lannt.market.client.BittrexMarketClient;
import xyz.lannt.market.client.MarketClient;

@Component
public class MarketClientFactory {

  @Autowired
  private BittrexMarketClient bittrexMarketClient;

  public MarketClient getClient(Market exchange) {

    if (BITTREX.equals(exchange)) {
      return bittrexMarketClient;
    }

    throw new MarketClientException("client not found!!!");
  }
}
