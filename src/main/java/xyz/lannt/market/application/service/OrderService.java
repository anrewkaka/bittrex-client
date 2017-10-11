package xyz.lannt.market.application.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xyz.lannt.market.application.client.BittrexMarketClient;
import xyz.lannt.market.domain.model.OrderHistories;

@Service
public class OrderService {

  @Autowired
  private BittrexMarketClient bittrexMarketClient;

  public OrderHistories getHistory() {
    return OrderHistories.fromResponse(bittrexMarketClient.getOrderHistory());
  }
}
