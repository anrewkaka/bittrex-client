package xyz.lannt.bittrex.application.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xyz.lannt.bittrex.application.client.BittrexMarketClient;
import xyz.lannt.bittrex.domain.model.OrderHistories;

@Service
public class OrderService {

  @Autowired
  private BittrexMarketClient bittrexMarketClient;

  public OrderHistories getHistory() {
    return OrderHistories.fromResponse(bittrexMarketClient.getOrderHistory());
  }
}
